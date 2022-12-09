package com.github.gcnyin.httpbenchmark.vertxweb

import io.vertx.core.{AbstractVerticle, Promise, Vertx}
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router

object Main {
  def main(args: Array[String]): Unit = {
    val vertx = Vertx.vertx()
    val processors = Runtime.getRuntime().availableProcessors()
    for (i <- 1 to processors) {
      vertx.deployVerticle(new HttpServerVerticle())
    }
  }

  class HttpServerVerticle extends AbstractVerticle {
    override def start(startPromise: Promise[Void]): Unit = {
      val server = vertx.createHttpServer()
      val router = Router.router(vertx)
      router.route(HttpMethod.GET, "/").handler { ctx =>
        val response = ctx.response
        response.putHeader("Content-Type", "text/plain")
        response.end("Hello, world!")
      }
      server.requestHandler(router).listen(8080).andThen { _ =>
        startPromise.complete()
      }
    }
  }
}
