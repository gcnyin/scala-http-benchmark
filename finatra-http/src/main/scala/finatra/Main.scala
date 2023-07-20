package finatra

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

object Main extends HelloWorldServer

class HelloWorldServer extends HttpServer {
  override def configureHttp(router: HttpRouter): Unit = {
    router.add[HelloWorldController]
  }
}

class HelloWorldController extends Controller {
  get("/") { _: Request =>
    "Hello, world!"
  }
}
