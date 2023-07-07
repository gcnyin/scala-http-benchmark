package com.github.gcnyin.httpbenchmark.finch

import cats.effect.{ExitCode, IO, IOApp}
import io.finch._

object Main extends IOApp with Endpoint.Module[IO] {
  val api: Endpoint[IO, String] = get(pathEmpty) {
    Ok("""
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

  override def run(args: List[String]): IO[ExitCode] =
    Bootstrap[IO].serve[Text.Plain](api).listen(":8080").useForever
}
