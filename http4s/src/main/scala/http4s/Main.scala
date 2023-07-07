package http4s

import cats.effect._
import com.comcast.ip4s._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.ember.server.EmberServerBuilder

import java.util.Date

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    Http4sdemoServer.runForever(org.http4s.dsl.io).as(ExitCode.Success)
  }

  object Http4sdemoServer {
    def runForever[F[_]: Async](dsl: Http4sDsl[F]): F[Nothing] = {
      EmberServerBuilder
        .default[F]
        .withHost(host = ipv4"0.0.0.0")
        .withPort(port = port"8080")
        .withHttpApp(routes(dsl).orNotFound)
        .build
        .useForever
    }

    def routes[F[_]: Sync](dsl: Http4sDsl[F]): HttpRoutes[F] = {
      import dsl._
      HttpRoutes.of[F] { case GET -> Root =>
        Ok(
          """
          |<!DOCTYPE html>
          |<html>
          |<body>
          |
          |<h1>My First Heading</h1>
          |<p>My first paragraph.</p>
          |
          |</body>
          |</html>
          |""".stripMargin,
          "Date" -> new Date().toString
        )
      }
    }
  }

}
