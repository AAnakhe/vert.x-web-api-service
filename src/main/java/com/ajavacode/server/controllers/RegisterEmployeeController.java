package com.ajavacode.server.controllers;

import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.api.service.RouteToEBServiceHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



@Slf4j
@Controller
@RequiredArgsConstructor
public class RegisterEmployeeController {

    private final Vertx vertx;

    @Autowired
    public RegisterEmployeeController(Router router, EventBus eventBus, Vertx vertx) {
        this.vertx = vertx;
        router.post("/register")
                .handler(this::customizeRequest)
                .handler(RouteToEBServiceHandler.build(eventBus, "save.employee", "saveEmployee"
                ).extraPayloadMapper(rc -> {
                    JsonObject jsonObject = rc.body().asJsonObject();
                    log.info("extra payload {}", jsonObject);
                    jsonObject.put("timestamp", System.currentTimeMillis());
                    return jsonObject;
                }));
    }

    private void customizeRequest(RoutingContext rc) {
        rc.next();
    }
}