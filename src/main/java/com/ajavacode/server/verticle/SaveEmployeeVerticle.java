package com.ajavacode.server.verticle;

import io.vertx.core.*;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.api.service.ServiceResponse;
import io.vertx.ext.web.api.service.WebApiServiceGen;
import io.vertx.serviceproxy.ServiceBinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class SaveEmployeeVerticle extends AbstractVerticle {


    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer("save.employee", this::saveEmployee);
    }

    private void saveEmployee(Message<Object> message) {
        JsonObject jsonObject = (JsonObject) message.body();
        performTask(jsonObject, resultHandler -> {
            if (resultHandler.succeeded()) {
                log.info("Saved employee: {}", jsonObject);
                JsonObject entries = resultHandler.result().toJson();
                message.reply(entries);
            } else {
                log.error("Failed to save employee: {}", jsonObject, resultHandler.cause());
            }
        });
    }

    private void performTask(JsonObject body, Handler<AsyncResult<ServiceResponse>> resultHandler) {
        //Perform your task and send the result
        JsonObject bodyValue = (JsonObject) body.getValue("context");
        JsonObject jsonObject = bodyValue.getJsonObject("extra");
        resultHandler.handle(
                Future.succeededFuture(
                        ServiceResponse.completedWithJson(jsonObject)
                )
        );
    }
}
