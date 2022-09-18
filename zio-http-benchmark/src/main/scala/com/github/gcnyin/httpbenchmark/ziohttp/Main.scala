package com.github.gcnyin.httpbenchmark.ziohttp

import zhttp.http._
import zhttp.service.Server
import zio._

object Main extends ZIOAppDefault {
  val app: Http[Any, Nothing, Request, Response] = Http.collect[Request] { case Method.GET -> !! / "" =>
    Response.text("Hello, world!")
  }

  override def run: ZIO[Any, Any, Any] = Server.start(8080, app).exitCode
}
