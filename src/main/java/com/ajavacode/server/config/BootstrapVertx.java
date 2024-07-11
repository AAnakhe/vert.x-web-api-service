package com.ajavacode.server.config;

import com.ajavacode.server.verticle.SaveEmployeeVerticle;
import com.ajavacode.server.verticle.WebServerVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class BootstrapVertx implements CommandLineRunner {

    private final Vertx vertx;
    private final WebServerVerticle webServerVerticle;
    private final SaveEmployeeVerticle saveEmployeeVerticle;

    @Override
    public void run(String... args) throws Exception {

        Future<String> startWebServerFuture = Future.future(promise -> vertx.deployVerticle(webServerVerticle, promise));
        Future.all(List.of(startWebServerFuture))
                .onComplete(ar -> {
                    if (ar.succeeded()) {
                        log.info("Verticle deployed successfully");
                    } else {
                        log.error("Verticle deployment failed", ar.cause());
                    }
                });
        Future<String> startSaveEmployeeFuture = Future.future(promise -> vertx.deployVerticle(saveEmployeeVerticle, promise));
        Future.all(List.of(startSaveEmployeeFuture))
                .onComplete(ar -> {
                    if (ar.succeeded()) {
                        log.info("Save Employee Verticle deployed successfully");
                    } else {
                        log.error("Save Employee Verticle deployment failed", ar.cause());
                    }
                });
    }

}
