package com.ajavacode.server.errorfilter;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.springframework.stereotype.Component;

@Component
public class Error404Filter implements Handler<RoutingContext> {

    public Error404Filter(Router router) {
        router.errorHandler(404, this);
    }

    @Override
    public void handle(RoutingContext rc) {
        rc.response().setStatusCode(404)
                .putHeader("content-type", "application/json")
                .end(new JsonObject()
                        .put("message", "Not Found")
                        .toString());
    }

}
