package com.github.gcnyin.httpbenchmark.ziohttp4s

import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Server
import com.comcast.ip4s._
import zio.{ExitCode, Scope, Task, URIO, ZIO, ZIOAppArgs, ZIOAppDefault, ZLayer}
import zio.interop.catz._

object Main extends ZIOAppDefault {

  object getRoutes extends Http4sDsl[Task] {
    val zio_routes: HttpRoutes[Task] = HttpRoutes.of { case GET -> Root =>
      Ok("""
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

  val zioHttp4sLayer: ZLayer[Any, Throwable, Server] =
    ZLayer.scoped {
      EmberServerBuilder
        .default[Task]
        .withHost(host = ipv4"0.0.0.0")
        .withPort(port = port"8080")
        .withHttpApp(getRoutes.zio_routes.orNotFound)
        .build
        .toScopedZIO
    }

  val entry_point: URIO[Any, ExitCode] = ZIO.never.exitCode

  def run: ZIO[Any with ZIOAppArgs with Scope, Throwable, ExitCode] =
    entry_point.provideLayer(zioHttp4sLayer).catchAll(t => ZIO.succeed(t.printStackTrace()).map(_ => ExitCode.failure))

}
