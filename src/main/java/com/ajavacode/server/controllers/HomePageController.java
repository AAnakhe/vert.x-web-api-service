package com.ajavacode.server.controllers;


import io.vertx.core.Handler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HomePageController implements Handler<RoutingContext> {

    @Autowired
    public HomePageController(Router router) {
        router.get("/")
                .handler(this);
    }

    @Override
    public void handle(RoutingContext rc) {
        rc.response()
                .setStatusCode(200)
                .putHeader("content-type", "text/html")
                .sendFile("html/index.html");
    }

}
