package com.github.gcnyin.httpbenchmark.ziohttp4s

import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.circe.CirceEntityEncoder._
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Server
import com.comcast.ip4s._
import zio.{ExitCode, Scope, Task, URIO, ZIO, ZIOAppArgs, ZIOAppDefault, ZLayer}
import zio.interop.catz._

object Main extends ZIOAppDefault {

  def zio_routes: HttpRoutes[Task] = {
    val dsl = new Http4sDsl[Task] {}
    import dsl._
    HttpRoutes.of { case GET -> Root =>
      Ok("Hello, world!")
    case GET -> Root / "json" =>
      Ok(Map("message" -> "Hello, world!"))
    }
  }

  val zioHttp4sLayer: ZLayer[Any, Throwable, Server] =
    ZLayer.scoped {
      EmberServerBuilder
        .default[Task]
        .withHost(host = ipv4"0.0.0.0")
        .withPort(port = port"8080")
        .withHttpApp(zio_routes.orNotFound)
        .build
        .toScopedZIO
    }

  val entry_point: URIO[Any, ExitCode] = ZIO.never.exitCode

  def run: URIO[Any with ZIOAppArgs with Scope, ExitCode] =
    entry_point.provideLayer(zioHttp4sLayer).catchAll(t => ZIO.succeed(t.printStackTrace()).exitCode)
}
