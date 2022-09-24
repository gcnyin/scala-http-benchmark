val projectVersion = "0.1.0"

lazy val root = (project in file("."))
  .settings(
    name := "scala-http-benchmark",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.9"
  )
  .aggregate(
    `akka-http`,
    `http4s`,
    `zio-http`,
    `http4s-zio2`,
    `finch`,
    `finatra-http`,
    `http4s-js`,
    `http4s-native`
  )

val LogbackVersion = "1.4.1"
val AkkaVersion = "2.6.20"
val AkkaHttpVersion = "10.2.10"

lazy val `akka-http` = (project in file("akka-http"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "akka-http",
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

lazy val `http4s` = (project in file("http4s"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "http4s",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.9",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-server" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Runtime
    ),
    Docker / packageName := "http4s-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:11.0.16_8-jre-focal",
    dockerExposedPorts ++= Seq(8080)
  )

lazy val `zio-http` = (project in file("zio-http"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "zio-http",
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

lazy val `http4s-zio2` = (project in file("http4s-zio2"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "http4s-zio2",
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

lazy val `finch` = (project in file("finch"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "finch",
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

lazy val `finatra-http` = (project in file("finatra-http"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "finatra-http",
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

lazy val `http4s-js` = (project in file("http4s-js"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "http4s-js",
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

lazy val `http4s-native` = (project in file("http4s-native"))
  .enablePlugins(ScalaNativePlugin)
  .settings(
    name := "http4s-native",
    organization := "com.github.gcnyin",
    version := projectVersion,
    scalaVersion := "2.13.9",
    libraryDependencies ++= Seq(
      "org.http4s" %%% "http4s-ember-server" % Http4sVersion,
      "org.http4s" %%% "http4s-dsl" % Http4sVersion,
      "com.armanbilge" %%% "epollcat" % "0.1.1"
    )
  )
