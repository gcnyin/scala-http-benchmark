val projectVersion = "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "scala-http-benchmark",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.8"
  )
  .aggregate(`akka-benchmark`, `http4s-benchmark`)

val LogbackVersion = "1.2.11"
val AkkaVersion = "2.6.20"
val AkkaHttpVersion = "10.2.10"

lazy val `akka-benchmark` = (project in file("akka-benchmark"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "akka-benchmark",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.8",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Runtime
    )
  )

val Http4sVersion = "0.23.15"
val CirceVersion = "0.14.2"

lazy val `http4s-benchmark` = (project in file("http4s-benchmark"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "http4s-benchmark",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.8",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-server" % Http4sVersion,
      "org.http4s" %% "http4s-ember-client" % Http4sVersion,
      "org.http4s" %% "http4s-circe" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "io.circe" %% "circe-generic" % CirceVersion,
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Runtime
    )
  )
