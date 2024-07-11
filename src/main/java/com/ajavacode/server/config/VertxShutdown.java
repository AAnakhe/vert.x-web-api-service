package com.ajavacode.server.config;

import io.vertx.core.Vertx;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class VertxShutdown implements DisposableBean {

    private final Vertx vertx;

    @Override
    public void destroy() throws Exception {
        vertx.close()
                .onComplete(ar -> {
                    if (ar.succeeded()) {
                        log.info("Vertx shutdown completed");
                    } else {
                        log.error("Vertx shutdown failed", ar.cause());
                    }
                });
    }

}
