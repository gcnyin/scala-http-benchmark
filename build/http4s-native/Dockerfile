FROM sbtscala/scala-sbt:eclipse-temurin-11.0.15_1.7.1_2.13.8

EXPOSE 8080/tcp
RUN apt-get update
RUN apt-get install -y llvm libssl-dev curl clang

# Build native binary
WORKDIR /app
COPY . /app
RUN sbt http4s-native/nativeLink

ENTRYPOINT http4s-native/target/scala-2.13/http4s-native-out
