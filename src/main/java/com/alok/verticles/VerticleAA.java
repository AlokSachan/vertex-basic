package com.alok.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleAA extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    System.out.println("start VerticleAA vertex : " + VerticleAA.class.getName());
    startPromise.complete();
  }

//  @Override
//  public void stop(Promise<Void> stopPromise) throws Exception {
//    System.out.println("Stop "+ getClass().getName());
//    stopPromise.complete();
//  }
}
