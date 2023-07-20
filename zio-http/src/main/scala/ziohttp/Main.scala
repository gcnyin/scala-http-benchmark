package ziohttp

import zio._
import zio.http._

object Main extends ZIOAppDefault {
  val app: Http[Any, Nothing, Request, Response] = {
    Http.collect[Request] { case Method.GET -> Root =>
      Response.text("Hello, world!")
    }
  }

  override def run: ZIO[Any, Any, Any] = {
    Server.serve(app).provide(Server.default)
  }
}
