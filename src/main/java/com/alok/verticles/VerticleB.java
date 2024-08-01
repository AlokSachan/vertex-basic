package com.alok.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleB extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    System.out.println("start VerticleB vertex : " + VerticleB.class.getName());
    startPromise.complete();
  }
}
