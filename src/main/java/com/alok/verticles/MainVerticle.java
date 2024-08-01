package com.alok.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class MainVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    final var vertx = Vertx.vertx();
    vertx.deployVerticle(new MainVerticle());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    System.out.println("start main vertex : " + MainVerticle.class.getName());
    vertx.deployVerticle(new VerticleA());
    vertx.deployVerticle(new VerticleB());
    startPromise.complete();
  }

//  @Override
//  public void stop(Promise<Void> stopPromise) throws Exception {
//    System.out.println("Stop " + MainVerticle.class.getName());
//    stopPromise.complete();
//  }
}
