package com.github.gcnyin.httpbenchmark.finatrahttp

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.{Controller, HttpServer}
import com.twitter.finatra.http.routing.HttpRouter

object Main extends HelloWorldServer

class HelloWorldServer extends HttpServer {
  override def configureHttp(router: HttpRouter): Unit = {
    router.add[HelloWorldController]
  }
}

class HelloWorldController extends Controller {
  get("/") { _: Request =>
    "Hello, world!"
  }
}
