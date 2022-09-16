package com.github.gcnyin.httpbenchmark.http4s

import cats.effect.{ExitCode, IO, IOApp}

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    Http4sdemoServer.stream[IO].compile.drain.as(ExitCode.Success)

  import cats.effect.{Async, Resource, Sync}
  import cats.syntax.all._
  import com.comcast.ip4s._
  import fs2.Stream
  import org.http4s.HttpRoutes
  import org.http4s.dsl.Http4sDsl
  import org.http4s.ember.server.EmberServerBuilder

  object Http4sdemoServer {
    def stream[F[_]: Async]: Stream[F, Nothing] = {
      for {
        exitCode <- Stream.resource(
          EmberServerBuilder
            .default[F]
            .withHost(host = ipv4"0.0.0.0")
            .withPort(port = port"8080")
            .withHttpApp(routes[F].orNotFound)
            .build >>
            Resource.eval(Async[F].never)
        )
      } yield exitCode
    }.drain

    def routes[F[_]: Sync]: HttpRoutes[F] = {
      val dsl = new Http4sDsl[F] {}
      import dsl._
      HttpRoutes.of[F] { case GET -> Root =>
        for {
          resp <- Ok("Hello, world!")
        } yield resp
      }
    }
  }

}
