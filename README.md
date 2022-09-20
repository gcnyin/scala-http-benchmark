# scala-http-benchmark

NOTE! This benchmark is for reference only and does not represent real-world application performance.

## Build

```
sbt docker:publishLocal
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
