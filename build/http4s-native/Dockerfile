FROM ubuntu:latest
EXPOSE 8080/tcp
RUN apt-get update
RUN apt-get install -y llvm
RUN apt-get install -y libssl-dev
RUN apt-get install -y curl
RUN apt-get install -y clang

# Install jdk

RUN curl "https://download.bell-sw.com/java/19+37/bellsoft-jdk19+37-linux-amd64-lite.deb" -o jdk.deb
RUN apt-get install -y ./jdk.deb

# Install sbt
RUN \
  curl -fsL "https://github.com/sbt/sbt/releases/download/v1.7.1/sbt-1.7.1.tgz" | tar xfz - -C /opt/ && \
  chown -R root:root /opt/sbt && \
  chmod -R 755 /opt/sbt && \
  ln -s /opt/sbt/bin/sbt /usr/local/bin/sbt

# Build native binary
RUN mkdir /root/src
COPY . /root/src/
WORKDIR "/root/src"
RUN sbt nativeLink

RUN cp http4s-ce3-native-benchmark/target/scala-2.13/http4s-ce3-native-benchmark-out /usr/local/bin/http4s-native
ENTRYPOINT /usr/local/bin/http4s-native
USER 1001:0
