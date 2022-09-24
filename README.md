# scala-http-benchmark

NOTE! This benchmark is for reference only and does not represent real-world application performance.

## Build

### Build http4s native

```
./build/build-http4s-native.sh
```

### Build http4s js

Build javascript file

```
sbt http4s-js/fullLinkJS
```

The result javascript file is `http4s-js/target/scala-2.13/http4s-js-opt/main.js`.

If you want to run it by `node`

```
node http4s-js/target/scala-2.13/http4s-js-opt/main.js
```

Build docker image

```
./http4s-ce3-js-benchmark/build.sh
```

### Build other modules

```
sbt docker:publishLocal
```

## Run

Example

```
docker run -d --name akka-http-benchmark --cpus="1" --memory="1024m" \
    -p 8080:8080 akka-http-benchmark:0.1.0
```

## Test

```
wrk -t5 -c100 -d10s "http://127.0.0.1:8080"
```
