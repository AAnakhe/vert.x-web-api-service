package com.ajavacode.server.config;


import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class VertxConfig {

    @Bean
    public Vertx vertx() {
        VertxOptions vertxOptions = new VertxOptions();
        vertxOptions.setWorkerPoolSize(150);
        return Vertx.builder()
                .with(vertxOptions)
                .build();
    }

    @Bean
    @Autowired
    public EventBus eventBus(Vertx vertx) {
        return vertx.eventBus();
    }

}
