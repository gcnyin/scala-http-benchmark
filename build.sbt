val projectVersion = "0.1.0"

lazy val root = (project in file("."))
  .settings(
    name := "scala-http-benchmark",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.9"
  )
  .aggregate(
    `akka-http-benchmark`,
    `http4s-ce3-benchmark`,
    `zio-http-benchmark`,
    `http4s-zio2-benchmark`,
    `finch-benchmark`,
    `finatra-http-benchmark`,
    `http4s-ce3-js-benchmark`
  )

val LogbackVersion = "1.4.1"
val AkkaVersion = "2.6.20"
val AkkaHttpVersion = "10.2.10"

lazy val `akka-http-benchmark` = (project in file("akka-http-benchmark"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "akka-http-benchmark",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.9",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Runtime
    ),
    Docker / packageName := "akka-http-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:11.0.16_8-jre-focal",
    dockerExposedPorts ++= Seq(8080)
  )

val Http4sVersion = "0.23.16"
val CirceVersion = "0.14.3"

lazy val `http4s-ce3-benchmark` = (project in file("http4s-ce3-benchmark"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "http4s-ce3-benchmark",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.9",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-server" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Runtime
    ),
    Docker / packageName := "http4s-ce3-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:11.0.16_8-jre-focal",
    dockerExposedPorts ++= Seq(8080)
  )

lazy val `zio-http-benchmark` = (project in file("zio-http-benchmark"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "zio-http-benchmark",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.9",
    libraryDependencies ++= Seq(
      "io.d11" %% "zhttp" % "2.0.0-RC11"
    ),
    Docker / packageName := "zio-http-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:11.0.16_8-jre-focal",
    dockerExposedPorts ++= Seq(8080)
  )

lazy val `http4s-zio2-benchmark` = (project in file("http4s-zio2-benchmark"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "http4s-zio2-benchmark",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.9",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-server" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Runtime,
      "dev.zio" %% "zio" % "2.0.2",
      "dev.zio" %% "zio-interop-cats" % "3.3.0"
    ),
    Docker / packageName := "http4s-zio2-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:11.0.16_8-jre-focal",
    dockerExposedPorts ++= Seq(8080)
  )

lazy val `finch-benchmark` = (project in file("finch-benchmark"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "finch-benchmark",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.9",
    libraryDependencies ++= Seq(
      "com.github.finagle" %% "finch-core" % "0.34.0",
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Runtime
    ),
    Docker / packageName := "finch-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:11.0.16_8-jre-focal",
    dockerExposedPorts ++= Seq(8080)
  )

lazy val `finatra-http-benchmark` = (project in file("finatra-http-benchmark"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "finatra-http-benchmark",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.9",
    libraryDependencies ++= Seq(
      "com.twitter" %% "finatra-http-server" % "22.7.0",
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Runtime
    ),
    Docker / packageName := "finatra-http-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:11.0.16_8-jre-focal",
    dockerExposedPorts ++= Seq(8080)
  )

lazy val `http4s-ce3-js-benchmark` = (project in file("http4s-ce3-js-benchmark"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "http4s-ce3-js-benchmark",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.9",
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "org.http4s" %%% "http4s-ember-server" % Http4sVersion,
      "org.http4s" %%% "http4s-dsl" % Http4sVersion
    ),
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
    },
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.CommonJSModule)
    }
  )
