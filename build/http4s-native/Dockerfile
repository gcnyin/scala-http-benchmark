FROM sbtscala/scala-sbt:eclipse-temurin-17.0.4_1.7.1_2.13.9

EXPOSE 8080/tcp
RUN apt-get update
RUN apt-get install -y llvm
RUN apt-get install -y libssl-dev
RUN apt-get install -y curl
RUN apt-get install -y clang

# Build native binary
RUN mkdir /root/src
COPY . /root/src/
WORKDIR "/root/src"
RUN sbt nativeLink

RUN cp http4s-ce3-native-benchmark/target/scala-2.13/http4s-ce3-native-benchmark-out /usr/local/bin/http4s-native
ENTRYPOINT /usr/local/bin/http4s-native
USER 1001:0
