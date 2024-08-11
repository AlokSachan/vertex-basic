package com.alok.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleN extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    System.out.println("start " + VerticleN.class.getName() +" on Thread " + Thread.currentThread().getName());
    System.out.println("config " + config().toString());
    startPromise.complete();
  }
}
