package com.ajavacode.server.controllers;


import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

@Controller
public class LoginController implements Function<RoutingContext, Future<Void>> {

    @Autowired
    public LoginController(Router  router) {
        router.get("/login").respond(this);
    }

    @Override
    public Future<Void> apply(RoutingContext routingContext) {
        return routingContext
                .response()
                .setStatusCode(200)
                .putHeader("content-type","application/json")
                .end(new JsonObject().encode());
    }

}
