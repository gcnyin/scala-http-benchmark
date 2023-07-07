package vertx

import io.vertx.core.http.HttpMethod
import io.vertx.core.{AbstractVerticle, Promise, Vertx}
import io.vertx.ext.web.Router
import org.slf4j.{Logger, LoggerFactory}

import java.util.Date

object Main {
  private val logger: Logger = LoggerFactory.getLogger(classOf[Main.type])

  def main(args: Array[String]): Unit = {
    val vertx = Vertx.vertx()
    val processors = Runtime.getRuntime.availableProcessors()
    for (_ <- 1 to processors) {
      vertx.deployVerticle(new HttpServerVerticle())
    }
  }

  class HttpServerVerticle extends AbstractVerticle {
    override def start(startPromise: Promise[Void]): Unit = {
      val server = vertx.createHttpServer()
      val router = Router.router(vertx)
      router.route(HttpMethod.GET, "/").handler { ctx =>
        val response = ctx.response
        response.putHeader("Content-Type", "text/plain; charset=utf-8")
        val date = new Date().toString
        response.putHeader("Date", date)
        response.end("""
          |<!DOCTYPE html>
          |<html>
          |<body>
          |
          |<h1>My First Heading</h1>
          |<p>My first paragraph.</p>
          |
          |</body>
          |</html>
          |""".stripMargin)
      }
      server.requestHandler(router).listen(8080).andThen { _ =>
        logger.info("verticle deployed: {}", deploymentID())
        startPromise.complete()
      }
    }
  }
}
