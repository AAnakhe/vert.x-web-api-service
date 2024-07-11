package com.ajavacode.server.verticle;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.tracing.TracingPolicy;
import io.vertx.ext.web.Router;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WebServerVerticle extends AbstractVerticle {

    private final Router router;
    private final int port;
    private final String host;

    @Autowired
    public WebServerVerticle(Router router,
                             @Value("${jevisonProps.serverConfig.port}") int port,
                             @Value("${jevisonProps.serverConfig.host}") String host) {
        this.router = router;
        this.port = port;
        this.host = host;
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        startServer(startPromise);
    }

    private void startServer(Promise<Void> startPromise) {
        HttpServerOptions httpServerOptions = new HttpServerOptions();
        httpServerOptions.setPort(port);
        httpServerOptions.setHost(host);
        httpServerOptions.setTracingPolicy(TracingPolicy.ALWAYS);
        vertx.createHttpServer(httpServerOptions)
                .requestHandler(router)
                .listen(port, host, ar -> {
                    if (ar.succeeded()) {
                        startPromise.complete();
                        log.info("Server started on port {}", port);
                    } else {
                        startPromise.fail(ar.cause());
                        log.error("Error starting server", ar.cause());
                    }
                });
    }
}
