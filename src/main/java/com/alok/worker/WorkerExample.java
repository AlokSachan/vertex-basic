package com.alok.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerExample extends AbstractVerticle {
  private static final Logger LOG = LoggerFactory.getLogger(WorkerExample.class.getName());

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new WorkerExample());

  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.deployVerticle(new WorkerVertical(),
      new DeploymentOptions()
        .setWorker(true)
        .setWorkerPoolSize(1)
        .setWorkerPoolName("my-worker-vertical"));
    startPromise.complete();
    executeWorker();
  }

  private void executeWorker() {
    vertx.executeBlocking(event-> {
      LOG.debug("execute blocking code");
      try {
        event.complete();
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        LOG.error("Failed  {} ",  e);
        event.fail(e);
      }
    }, result -> {
      if(result.succeeded()){
        LOG.error("Blocking call done.");
      }
      else {
        LOG.error("Blocking call failed due to {} " ,  result.cause());
      }
    });
  }
}
