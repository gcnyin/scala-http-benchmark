# scala-http-benchmark

NOTE! This benchmark is for reference only and does not represent real-world application performance.

`http4s-ce3-native` is not available for now.

## Build

### Build http4s native
```bash
bash build/build-http4s-native.sh
```

### Build other modules
```
sbt docker:publishLocal
```

## Build `http4s-ce3-js-benchmark`

For `http4s-ce3-js-benchmark`, to build scala javascript file

```
sbt http4s-ce3-js-benchmark/fullLinkJS
```

The result javascript file is `http4s-ce3-js-benchmark/target/scala-2.13/http4s-ce3-js-benchmark-opt/main.js`.

If you want to run it by `node`

```
node http4s-ce3-js-benchmark/target/scala-2.13/http4s-ce3-js-benchmark-opt/main.js
```

## Build `http4s-ce3-js-benchmark` docker image

```
./http4s-ce3-js-benchmark/build.sh
```

## Run

Example

```
docker run -d --name akka-http-benchmark --cpus="1" --memory="1024m" \
    -p 8080:8080 akka-http-benchmark:1.0-SNAPSHOT
```

## Test

```
wrk -t5 -c100 -d10s "http://127.0.0.1:8080"
```
