package com.plateer.lotteonDisplay.config;

import com.plateer.lotteonDisplay.model.cart.Cart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Configuration
@EnableR2dbcAuditing
public class AuditConfig {

    @Bean
    public ReactiveAuditorAware<String> myAuditorProvider() {
        return new BasicAuditor();
    }
    public static class BasicAuditor implements ReactiveAuditorAware<String> {

        @Override
        public Mono<String> getCurrentAuditor() {
            //시큐리티 추가 시, SecurityContextHolder에서 user 정보를 가져오는 코드 추가 필요!
            return Mono.just("yum");
        }
    }
}
