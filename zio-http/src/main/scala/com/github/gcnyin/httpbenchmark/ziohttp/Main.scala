package com.github.gcnyin.httpbenchmark.ziohttp

import zio._
import zio.http._
import zio.http.model.Method

object Main extends ZIOAppDefault {
  val app: Http[Any, Nothing, Request, Response] = {
    Http.collect[Request] { case Method.GET -> !! =>
      Response.text("Hello, world!")
    }
  }

  override def run: ZIO[Any, Any, Any] = {
    Server.serve(app).provide(Server.default)
  }
}
