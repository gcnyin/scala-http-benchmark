package com.github.gcnyin.httpbenchmark.ziohttp

import zhttp.http._
import zhttp.service.server.ServerChannelFactory
import zhttp.service.{EventLoopGroup, Server}
import zio._

object Main extends ZIOAppDefault {

  // Based on optimization technique by Tushar Mathur
  // Added static JSON, this style can be adopted in multiple forms to add more routes

  val frozenTextResponse: Task[Response] = Response.text("Hello, world!").freeze
  val frozenJSONResponse: Task[Response] = Response.json("""{"message": "Hello, world!"}""").freeze

  def routeDefault(text: Response): HttpApp[Any, Nothing] = Http.fromHExit(HExit.succeed(text)).whenPathEq("/")

  def routeJSON(json: Response): HttpApp[Any, Nothing] = Http.fromHExit(HExit.succeed(json)).whenPathEq("/json")

  // Default way to define routes
  def routesZIO: HttpApp[Any, Nothing] = Http.collectZIO[Request] { case Method.GET -> !! / "zio" =>
    Clock.currentDateTime.map(s => Response.text(s.toString))
  }

  val routesHTTP: HttpApp[Any, Nothing] = {
    Http.collect[Request] { case Method.GET -> !! / "http" =>
      Response.text("Hello, world!")
    }
  }

  override def run: ZIO[Any with ZIOAppArgs with Scope, Throwable, Unit] = for {
    staticDefaultResponse <- frozenTextResponse
    staticJsonResponse <- frozenJSONResponse
    serverFibre <- (
      Server.app(
        routesHTTP ++
          routesZIO ++
          routeDefault(staticDefaultResponse) ++
          routeJSON(staticJsonResponse)) ++
        Server.disableLeakDetection ++
        Server.disableFlowControl)
      .withBinding("localhost", 8080)
      .start.catchAll(t => ZIO.succeed(t.printStackTrace()).exitCode)
      .provide(
        ServerChannelFactory.auto ++ EventLoopGroup.auto()) //provide number of threads,try nio or uring instead of auto
      .fork
    _ <- Console.readLine("Press Enter to stop the server\n")
    _ <- Console.printLine("Interrupting server...")
    _ <- serverFibre.interrupt
  } yield ()
}