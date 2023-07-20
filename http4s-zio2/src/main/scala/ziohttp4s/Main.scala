package ziohttp4s

import com.comcast.ip4s._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Server
import zio.interop.catz._
import zio.{ExitCode, Scope, Task, URIO, ZIO, ZIOAppArgs, ZIOAppDefault, ZLayer}

object Main extends ZIOAppDefault {

  val dsl = new Http4sDsl[Task] {}
  import dsl._

  val zioRoutes: HttpRoutes[Task] = HttpRoutes.of { case GET -> Root =>
    Ok("Hello, world!")
  }

  val zioHttp4sLayer: ZLayer[Any, Throwable, Server] =
    ZLayer.scoped {
      EmberServerBuilder
        .default[Task]
        .withHost(host = ipv4"0.0.0.0")
        .withPort(port = port"8080")
        .withHttpApp(zioRoutes.orNotFound)
        .build
        .toScopedZIO
    }

  val entryPoint: URIO[Any, ExitCode] = ZIO.never.exitCode

  def run: ZIO[Any with ZIOAppArgs with Scope, Throwable, ExitCode] =
    entryPoint.provideLayer(zioHttp4sLayer).map(_ => ExitCode.success)

}
