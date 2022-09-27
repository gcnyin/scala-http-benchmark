package tapirnetty

import sttp.tapir._
import sttp.tapir.server.ServerEndpoint

import scala.concurrent.Future

object Endpoints {
  private val root: Endpoint[Unit, Unit, Unit, String, Any] = endpoint.get
    .in("")
    .out(stringBody)

  val helloServerEndpoint: ServerEndpoint[Any, Future] = root.serverLogicSuccess(_ => Future.successful(s"Hello, world!"))

  val apiEndpoints: List[ServerEndpoint[Any, Future]] = List(helloServerEndpoint)
}
