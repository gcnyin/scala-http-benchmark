package com.github.gcnyin.httpbenchmark.ziohttp

import zio._
import zio.http._

object Main extends ZIOAppDefault {
  val app: Http[Any, Nothing, Request, Response] = {
    Http.collect[Request] { case Method.GET -> Root =>
      Response.text("""
          |<!DOCTYPE html>
          |<html>
          |<body>
          |
          |<h1>My First Heading</h1>
          |<p>My first paragraph.</p>
          |
          |</body>
          |</html>
          |""".stripMargin)
    }
  }

  override def run: ZIO[Any, Any, Any] = {
    Server.serve(app).provide(Server.default)
  }
}
