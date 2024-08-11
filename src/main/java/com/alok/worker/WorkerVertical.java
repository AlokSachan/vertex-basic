package com.alok.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerVertical extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(WorkerVertical.class.getName());

  public static void main(String[] args) {
//    var vertex= Vertx.vertx();
//    vertex.deployVerticle(new WorkerVertical());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    LOG.debug("deployed as worker verticle");
    startPromise.complete();
    Thread.sleep(5000);
    LOG.debug("Blocking operation is done");
  }
}
