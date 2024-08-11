package com.alok.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class PublisherSubscriberExample {
  public static void main(String[] args) {
    var vertex = Vertx.vertx();
    vertex.deployVerticle(new Publisher());
    vertex.deployVerticle(new Subscriber1());
    //vertex.deployVerticle(new Subscriber2(), new DeploymentOptions().setInstances(2));
    vertex.deployVerticle(Subscriber2.class.getName(), new DeploymentOptions().setInstances(2));
  }

  static class Publisher extends AbstractVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(PublisherSubscriberExample.Publisher.class.getName());

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.setPeriodic(Duration.ofSeconds(10).toMillis(), id -> {
        vertx.eventBus()
          .publish(Publisher.class.getName(), "A message from everyone");
      });

    }
  }
  static class Subscriber1 extends AbstractVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(PublisherSubscriberExample.Subscriber1.class.getName());
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
        vertx.eventBus()
          .consumer(Publisher.class.getName(), message -> {
            LOG.debug(" {}:  Message Consumed on Subscriber1", message.body());
          });
    }
  }
  public static class Subscriber2 extends AbstractVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(PublisherSubscriberExample.Subscriber2.class.getName());
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus()
        .consumer(Publisher.class.getName(), message -> {
          LOG.debug(" {}:  Message Consumed on Subscriber2", message.body());
        });

    }
  }
}
