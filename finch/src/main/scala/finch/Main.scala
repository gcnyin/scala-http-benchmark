package finch

import cats.effect.{ExitCode, IO, IOApp}
import io.finch._

object Main extends IOApp with Endpoint.Module[IO] {
  val api: Endpoint[IO, String] = get(pathEmpty) {
    Ok("Hello, world!")
  }

  override def run(args: List[String]): IO[ExitCode] =
    Bootstrap[IO].serve[Text.Plain](api).listen(":8080").useForever
}
