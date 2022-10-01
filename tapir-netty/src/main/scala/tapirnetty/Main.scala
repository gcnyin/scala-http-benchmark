package tapirnetty

import sttp.tapir.server.netty.NettyFutureServer

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.io.StdIn
import ExecutionContext.Implicits.global

object Main {
  def main(args: Array[String]): Unit = {
    val port = sys.env.get("http.port").map(_.toInt).getOrElse(8080)
    val program = for {
      binding <- NettyFutureServer().port(port).addEndpoints(Endpoints.apiEndpoints).start()
      _ <- Future {
        println(s"Server started at http://localhost:${binding.port}. Press ENTER key to exit.")
        StdIn.readLine()
      }
      stop <- binding.stop()
    } yield stop

    Await.result(program, Duration.Inf)
  }
}
