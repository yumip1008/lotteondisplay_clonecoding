package com.plateer.lotteonDisplay.config;

import com.fasterxml.jackson.databind.JsonNode;
import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.LoggingCodecSupport;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Configuration
@Slf4j
public class WebClientConfig {

    @Bean
    public WebClient webClient() {

        //Connection Pool Timeout 설정
        ConnectionProvider provider =
                ConnectionProvider.builder("custom")
                        .maxConnections(50)
                        .maxIdleTime(Duration.ofSeconds(20))    //최대 Idle 상태 유지 시간 설정
                        .maxLifeTime(Duration.ofSeconds(60))    //Live connection이 유지되는 최대 시간 설정
                        .pendingAcquireTimeout(Duration.ofSeconds(60))  //pending 작업을 대기하는 최대시간 설정
                        .evictInBackground(Duration.ofSeconds(120))     //connection pool에서 제거할 수 있는 연결을 정기적으로 확인하는 시간 설정
                        .build();

        //Connection Timeout 설정
        HttpClient httpClient =  HttpClient.create(provider)
                .secure(
                        ThrowingConsumer.unchecked(
                                sslContextSpec -> sslContextSpec.sslContext(
                                        SslContextBuilder
                                                .forClient()
                                                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                                                .build()
                                )
                        )
                )
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                .doOnConnected(conn -> {
                    conn.addHandlerLast(new ReadTimeoutHandler(180));
                    conn.addHandlerLast(new WriteTimeoutHandler(180));
                })
                .responseTimeout(Duration.ofSeconds(1));

        //MaxInMemorySize 설정
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs()
                        .maxInMemorySize(1024*1024*50))
                .build();

        //Logging 설정 - Request, Response 정보 log
        exchangeStrategies.messageWriters().stream()
                .filter(LoggingCodecSupport.class::isInstance)
                .forEach(writer -> ((LoggingCodecSupport)writer).setEnableLoggingRequestDetails(true));

        //Request 정보 log
        ExchangeFilterFunction clientRequestLog = ExchangeFilterFunction.ofRequestProcessor(
                clientRequest -> {
                    log.debug("Request : {} {}", clientRequest.method(), clientRequest.url());
                    clientRequest.headers().forEach((name,values)-> values.forEach(value -> log.debug("{} : {}", name, value)));
                    return Mono.just(clientRequest);
                }
        );

        //Response Header 정보 log
        ExchangeFilterFunction clientResponseLog = ExchangeFilterFunction.ofResponseProcessor(
                clientResponse -> {
                    clientResponse.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> log.debug("{} : {}", name, value)));
                    return Mono.just(clientResponse);
                }
        );



        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(exchangeStrategies)
                .filter(clientRequestLog)
                .filter(clientResponseLog)
                .build();
    }

}
