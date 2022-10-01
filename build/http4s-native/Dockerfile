FROM sbtscala/scala-sbt:eclipse-temurin-11.0.15_1.7.1_2.13.8

EXPOSE 8080/tcp
RUN apt-get update
RUN apt-get install -y llvm libssl-dev curl clang

# Build native binary
RUN mkdir /root/src
COPY . /root/src/
WORKDIR "/root/src"
RUN sbt http4s-native/nativeLink

RUN cp http4s-native/target/scala-2.13/http4s-native-out /usr/local/bin/http4s-native
ENTRYPOINT /usr/local/bin/http4s-native
USER 1001:0
