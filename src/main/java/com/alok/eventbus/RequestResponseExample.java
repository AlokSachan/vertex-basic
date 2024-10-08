package com.alok.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestResponseExample {

  public static void main(String[] args) {
    var vertex = Vertx.vertx();
    vertex.deployVerticle(new RequestVerticle());
    vertex.deployVerticle(new ResponseVerticle());

  }

  static class RequestVerticle extends AbstractVerticle {

    public static final String MY_REQUEST_ADDRESS = "my.request.address";
    private static final Logger LOG = LoggerFactory.getLogger(RequestVerticle.class.getName());

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      var eventBus = vertx.eventBus();
      String message = "Hello World!";
      LOG.debug("Sending: {}", message);
      eventBus.request(MY_REQUEST_ADDRESS, message, reply -> {
        LOG.debug("Response: {}", reply.result().body());
      });
    }
  }

  static class ResponseVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(ResponseVerticle.class.getName());

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus().consumer(RequestVerticle.MY_REQUEST_ADDRESS, message -> {
        LOG.debug("Received Message: {}", message.body());
        message.reply("Received your message. Thanks! ");
      });
    }
  }
}
