val projectVersion = "0.1.0"

lazy val root = (project in file("."))
  .settings(
    name := "scala-http-benchmark",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.11"
  )
  .aggregate(
    `akka-http`,
    `finatra-http`,
    `finch`,
    `http4s`,
    `http4s-js`,
    `http4s-native`,
    `http4s-zio2`,
    `tapir-netty`,
    `vertx-web`,
    `tapir-vertx`,
    `zio-http`,
  )

val LogbackVersion = "1.4.8"
val AkkaVersion = "2.8.3"
val AkkaHttpVersion = "10.5.2"

lazy val `akka-http` = (project in file("akka-http"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "akka-http",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.11",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Runtime
    ),
    Docker / packageName := "akka-http-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:17.0.7_7-jre-jammy",
    dockerExposedPorts ++= Seq(8080)
  )

val Http4sVersion = "0.23.23"
val CatsEffectVersion = "3.5.1"

lazy val `http4s` = (project in file("http4s"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "http4s",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.11",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % CatsEffectVersion,
      "org.http4s" %% "http4s-ember-server" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Runtime
    ),
    Docker / packageName := "http4s-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:17.0.7_7-jre-jammy",
    dockerExposedPorts ++= Seq(8080)
  )

lazy val `zio-http` = (project in file("zio-http"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "zio-http",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.11",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio-http" % "3.0.0-RC2"
    ),
    Docker / packageName := "zio-http-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:17.0.7_7-jre-jammy",
    dockerExposedPorts ++= Seq(8080)
  )

lazy val `http4s-zio2` = (project in file("http4s-zio2"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "http4s-zio2",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.11",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % CatsEffectVersion,
      "org.http4s" %% "http4s-ember-server" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Runtime,
      "dev.zio" %% "zio" % "2.0.15",
      "dev.zio" %% "zio-interop-cats" % "23.0.0.8"
    ),
    Docker / packageName := "http4s-zio2-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:17.0.7_7-jre-jammy",
    dockerExposedPorts ++= Seq(8080)
  )

lazy val `finch` = (project in file("finch"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "finch",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.11",
    libraryDependencies ++= Seq(
      "com.github.finagle" %% "finch-core" % "0.34.1",
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Runtime
    ),
    Docker / packageName := "finch-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:17.0.7_7-jre-jammy",
    dockerExposedPorts ++= Seq(8080)
  )

lazy val `finatra-http` = (project in file("finatra-http"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "finatra-http",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.11",
    libraryDependencies ++= Seq(
      "com.twitter" %% "finatra-http-server" % "22.12.0",
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Runtime
    ),
    Docker / packageName := "finatra-http-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:11.0.17_8-jre-focal",
    dockerExposedPorts ++= Seq(8080)
  )

lazy val `http4s-js` = (project in file("http4s-js"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "http4s-js",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.11",
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-effect" % CatsEffectVersion,
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

lazy val `http4s-native` = (project in file("http4s-native"))
  .enablePlugins(ScalaNativePlugin)
  .settings(
    name := "http4s-native",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.11",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-effect" % CatsEffectVersion,
      "org.http4s" %%% "http4s-ember-server" % Http4sVersion,
      "org.http4s" %%% "http4s-dsl" % Http4sVersion,
      "com.armanbilge" %%% "epollcat" % "0.1.5"
    )
  )

lazy val `vertx-web` = (project in file("vertx-web"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "vertx-web",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.11",
    libraryDependencies ++= Seq(
      "io.vertx" % "vertx-web" % "4.4.4",
      "ch.qos.logback" % "logback-classic" % LogbackVersion
    ),
    Docker / packageName := "vertx-web-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:17.0.7_7-jre-jammy",
    dockerExposedPorts ++= Seq(8080)
  )

val tapirVersion = "1.6.2"

lazy val `tapir-netty` = (project in file("tapir-netty"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "tapir-netty",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.11",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-netty-server" % tapirVersion,
      "ch.qos.logback" % "logback-classic" % LogbackVersion
    ),
    Docker / packageName := "tapir-netty-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:17.0.7_7-jre-jammy",
    dockerExposedPorts ++= Seq(8080)
  )

lazy val `tapir-vertx` = (project in file("tapir-vertx"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "tapir-vertx",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.11",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-vertx-server" % tapirVersion,
      "ch.qos.logback" % "logback-classic" % LogbackVersion
    ),
    Docker / packageName := "tapir-vertx-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:17.0.7_7-jre-jammy",
    dockerExposedPorts ++= Seq(8080)
  )
