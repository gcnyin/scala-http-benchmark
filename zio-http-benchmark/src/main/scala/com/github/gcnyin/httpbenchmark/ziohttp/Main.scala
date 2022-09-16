package com.github.gcnyin.httpbenchmark.ziohttp

import zhttp.http.{Http, HttpApp}
import zhttp.service.Server
import zio.{ZIO, ZIOAppDefault}

object Main extends ZIOAppDefault {
  val app: HttpApp[Any, Nothing] = Http.text("Hello, world!")

  override def run: ZIO[Any, Any, Any] = Server.start(8080, app).exitCode
}
