# scala-http-benchmark

## Build

```
sbt akka-http-benchmark/docker:publishLocal \
    http4s-ce3-benchmark/docker:publishLocal \
    zio-http-benchmark/docker:publishLocal
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
