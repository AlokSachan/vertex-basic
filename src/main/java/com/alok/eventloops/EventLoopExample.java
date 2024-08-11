package com.alok.eventloops;

import io.vertx.core.*;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

@Slf4j
public class EventLoopExample extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(EventLoopExample.class.getName());

  public static void main(String[] args) {
    var vertex = Vertx.vertx(
      new VertxOptions()
        .setMaxEventLoopExecuteTime(500)
        .setMaxEventLoopExecuteTimeUnit(TimeUnit.MILLISECONDS)
        .setBlockedThreadCheckInterval(1)
        .setBlockedThreadCheckIntervalUnit(TimeUnit.SECONDS)
        .setEventLoopPoolSize(4)
    );
    //vertex.deployVerticle(new EventLoopExample());
    vertex.deployVerticle(EventLoopExample.class.getName(), new DeploymentOptions()
      .setInstances(4));
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    LOG.debug("start {}", getClass().getName());
    startPromise.complete();
    //Thread.sleep(5000);
  }
}
