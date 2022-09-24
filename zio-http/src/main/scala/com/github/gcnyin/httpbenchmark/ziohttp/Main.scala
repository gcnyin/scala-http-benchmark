package com.github.gcnyin.httpbenchmark.ziohttp

import zhttp.http._
import zhttp.service.Server
import zio._

object Main extends ZIOAppDefault {
  def app(frozen: Response): Http[Any, Nothing, Request, Response] = Http.collect[Request] { case Method.GET -> !! =>
    frozen
  }

  override def run: ZIO[Any, Any, Any] =
    Response.text("Hello, world!").freeze.flatMap(frozen => Server.start(8080, app(frozen))).exitCode
}
