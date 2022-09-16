package com.github.gcnyin.httpbenchmark.http4s

import zhttp.http.{Http, HttpApp}
import zio.{ZIO, ZIOAppDefault}
import zhttp.service.Server

object Main extends ZIOAppDefault {
  val app: HttpApp[Any, Nothing] = Http.text("Hello World!")

  override def run: ZIO[Any, Any, Any] = Server.start(8080, app).exitCode
}
