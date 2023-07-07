package com.github.gcnyin.httpbenchmark.akkahttp

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

object Main {
  private val logger: Logger = LoggerFactory.getLogger(Main.getClass)

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val route =
      pathSingleSlash {
        get {
          complete(
            HttpEntity(
              ContentTypes.`text/plain(UTF-8)`,
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
          |""".stripMargin
            )
          )
        }
      }

    Http().newServerAt("0.0.0.0", 8080).bind(route).onComplete {
      case Failure(exception) =>
        logger.error("error", exception)
      case Success(value) =>
        logger.info("{}", value)
    }
  }
}
