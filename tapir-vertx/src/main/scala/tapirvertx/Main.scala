package tapirvertx

import io.vertx.core.{AbstractVerticle, Promise, Vertx}
import io.vertx.ext.web._
import org.slf4j.{Logger, LoggerFactory}
import sttp.tapir.server.vertx.VertxFutureServerInterpreter

object Main {
  private val logger: Logger = LoggerFactory.getLogger(classOf[Main.type])

  def main(args: Array[String]): Unit = {
    val vertx = Vertx.vertx()
    val router = Router.router(vertx)
    val attach = VertxFutureServerInterpreter().route(Endpoints.helloServerEndpoint)
    attach(router)

    vertx.nettyEventLoopGroup().forEach { _ =>
      vertx.deployVerticle(new HttpServerVerticle(router = router))
    }
  }

  class HttpServerVerticle(router: Router) extends AbstractVerticle {
    override def start(startPromise: Promise[Void]): Unit = {
      val server = vertx.createHttpServer()
      server.requestHandler(router).listen(8080).andThen { _ =>
        logger.info("verticle deployed: {}", deploymentID())
        startPromise.complete()
      }
    }
  }
}
