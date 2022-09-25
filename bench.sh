#!/bin/env bash

# zio-http
docker run -d --name zio-http --cpus="1" --memory="1024m" -p 8080:8080 zio-http-benchmark:0.1.0

sleep 5

echo "zio-http warmup start"

wrk -t10 -c200 -d60s "http://127.0.0.1:8080"

echo "zio-http start"

echo "zio-http start" >> result.txt

wrk -t10 -c200 -d60s "http://127.0.0.1:8080" >> result.txt

echo "zio-http end"

echo "zio-http end" >> result.txt

docker stop zio-http

# finch
docker run -d --name finch --cpus="1" --memory="1024m" -p 8080:8080 finch-benchmark:0.1.0

sleep 5

echo "finch warmup"

wrk -t10 -c200 -d60s "http://127.0.0.1:8080"

echo "finch start"

echo "finch start" >> result.txt

wrk -t10 -c200 -d60s "http://127.0.0.1:8080" >> result.txt

echo "finch end"

echo "finch end" >> result.txt

docker stop finch

# akka-http
docker run -d --name akka-http --cpus="1" --memory="1024m" -p 8080:8080 akka-http-benchmark:0.1.0

sleep 5

echo "akka-http warmup"

wrk -t10 -c200 -d60s "http://127.0.0.1:8080"

echo "akka-http start"

echo "akka-http start" >> result.txt

wrk -t10 -c200 -d60s "http://127.0.0.1:8080" >> result.txt

echo "akka-http end"

echo "akka-http end" >> result.txt

docker stop akka-http

# http4s
docker run -d --name http4s --cpus="1" --memory="1024m" -p 8080:8080 http4s-benchmark:0.1.0

sleep 5

echo "http4s warmup"

wrk -t10 -c200 -d60s "http://127.0.0.1:8080"

echo "http4s start"

echo "http4s start" >> result.txt

wrk -t10 -c200 -d60s "http://127.0.0.1:8080" >> result.txt

echo "http4s end"

echo "http4s end" >> result.txt

docker stop http4s

# finatra-http
docker run -d --name finatra-http --cpus="1" --memory="1024m" -p 8080:8888 finatra-http-benchmark:0.1.0

sleep 5

echo "finatra-http warmup"

wrk -t10 -c200 -d60s "http://127.0.0.1:8080"

echo "finatra-http start" >> result.txt

echo "finatra-http start"

wrk -t10 -c200 -d60s "http://127.0.0.1:8080" >> result.txt

echo "finatra-http end"

echo "finatra-http end" >> result.txt

docker stop finatra-http

# vertx-web
docker run -d --name vertx-web --cpus="1" --memory="1024m" -p 8080:8888 vertx-web-benchmark:0.1.0

sleep 5

echo "vertx-web warmup"

wrk -t10 -c200 -d60s "http://127.0.0.1:8080"

echo "vertx-web start" >> result.txt

echo "vertx-web start"

wrk -t10 -c200 -d60s "http://127.0.0.1:8080" >> result.txt

echo "vertx-web end"

echo "vertx-web end" >> result.txt

docker stop vertx-web

# http4s-js
docker run -d --name http4s-js --cpus="1" --memory="1024m" -p 8080:8888 http4s-js-benchmark:0.1.0

sleep 5

echo "http4s-js warmup"

wrk -t10 -c200 -d60s "http://127.0.0.1:8080"

echo "http4s-js start" >> result.txt

echo "http4s-js start"

wrk -t10 -c200 -d60s "http://127.0.0.1:8080" >> result.txt

echo "http4s-js end"

echo "http4s-js end" >> result.txt

docker stop http4s-js
