package com.alok.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PointToPointExample {

  public static void main(String[] args) {
    var vertex = Vertx.vertx();
    vertex.deployVerticle(new Sender());
    vertex.deployVerticle(new Receiver());
  }

  static class Sender extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.setPeriodic(1000, id -> {
        vertx.eventBus()
          .send(Sender.class.getName(), "Sending message...");
      });

    }
  }

  static class Receiver extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(PointToPointExample.Receiver.class.getName());

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus().consumer(Sender.class.getName(), message -> {
        LOG.debug("Message received {}", message.body());
      });
    }
  }
}
