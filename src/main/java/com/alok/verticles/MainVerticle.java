package com.alok.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;


import java.util.UUID;
import java.util.logging.Logger;


public class MainVerticle extends AbstractVerticle {
  private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(MainVerticle.class);

  public static void main(String[] args) {
    final var vertx = Vertx.vertx();
    vertx.deployVerticle(new MainVerticle());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    log.debug("start main vertex : " + MainVerticle.class.getName());
    vertx.deployVerticle(new VerticleA());
    vertx.deployVerticle(new VerticleB());
    vertx.deployVerticle(VerticleN.class.getName(), new DeploymentOptions()
      .setConfig(new JsonObject()
        .put("id", UUID.randomUUID().toString())
        .put("name",VerticleN.class.getSimpleName())));
    startPromise.complete();
  }

}
