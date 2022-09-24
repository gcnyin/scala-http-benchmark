package com.github.gcnyin.httpbenchmark.vertxweb

import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router

object Main {
  def main(args: Array[String]): Unit = {
    val vertx = Vertx.vertx()
    val server = vertx.createHttpServer()
    val router = Router.router(vertx)
    router.route(HttpMethod.GET, "/").handler { ctx =>
      val response = ctx.response
      response.putHeader("content-type", "text/plain")
      response.end("Hello, world!")
    }
    server.requestHandler(router).listen(8080)
  }
}
