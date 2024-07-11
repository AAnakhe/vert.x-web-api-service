package com.ajavacode.server.config;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CSPHandler;
import io.vertx.ext.web.handler.HSTSHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

    @Bean
    @Autowired
    public Router router(Vertx vertx) {
        Router router = Router.router(vertx);
        router.post().handler(BodyHandler.create());
        router.route().handler(HSTSHandler.create());
        router.route().handler(CSPHandler.create());
        return router;
    }

}
