package tapirnetty

import sttp.tapir._
import sttp.tapir.server.ServerEndpoint

import scala.concurrent.Future

object Endpoints {
  private val root: Endpoint[Unit, Unit, Unit, String, Any] = endpoint.get
    .in("")
    .out(stringBody)

  val helloServerEndpoint: ServerEndpoint[Any, Future] = root.serverLogicSuccess(_ => Future.successful(s"""
          |<!DOCTYPE html>
          |<html>
          |<body>
          |
          |<h1>My First Heading</h1>
          |<p>My first paragraph.</p>
          |
          |</body>
          |</html>
          |""".stripMargin))

  val apiEndpoints: List[ServerEndpoint[Any, Future]] = List(helloServerEndpoint)
}
