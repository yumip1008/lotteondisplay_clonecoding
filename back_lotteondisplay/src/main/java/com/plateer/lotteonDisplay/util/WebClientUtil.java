package com.plateer.lotteonDisplay.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebClientUtil {

    private final WebClient webClient;

    public <T> Mono<T> getByParamsGetRequest(
            String uri,
            MultiValueMap multiValueMap,
            Class<T> finalType)
    {

        Mono<T> result = webClient.mutate().baseUrl(uri).build()
                .get()
                .uri(uriBuilder -> uriBuilder.queryParams(multiValueMap).build())
                .retrieve()
                .bodyToMono(finalType);

        return result;
    }

    public  <T> Mono<T> getByFluxBodyPostRequest(
            String uri,
            Object body,
            Class<T> finalType
    ){
        Mono<T> result = webClient.mutate().build()
                .post()
                .uri(uri)
                .body(Flux.just(body), body.getClass())
                .retrieve()
                .bodyToMono(finalType);

        return result;
    }

    public <T, S> Mono<T> getByFluxBodyPostRequest(
            String uri,
            Flux<S> body,
            Class<S> bodyType,
            Class<T> finalType
    ){
        Mono<T> result = webClient.mutate().build()
                .post()
                .uri(uri)
                .body(body, bodyType)
                .retrieve()
                .bodyToMono(finalType);
        return result;
    }

}