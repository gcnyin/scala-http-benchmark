package ziohttp4s

import com.comcast.ip4s._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Server
import zio.interop.catz._
import zio.{ExitCode, Scope, Task, URIO, ZIO, ZIOAppArgs, ZIOAppDefault, ZLayer}

object Main extends ZIOAppDefault {

  object getRoutes extends Http4sDsl[Task] {
    val zio_routes: HttpRoutes[Task] = HttpRoutes.of { case GET -> Root =>
      Ok("Hello, world!")
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
