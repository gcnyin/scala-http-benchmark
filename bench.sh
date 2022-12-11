#!/bin/env bash

SERVER_CPUS=${SERVER_CPUS:-1}
SERVER_MEMORY=${SERVER_MEMORY:-1024m}

# akka-http
docker run -d --name akka-http --cpus "${SERVER_CPUS}" --memory="${SERVER_MEMORY}" -p 8080:8080 akka-http-benchmark:0.1.0

sleep 5

echo "akka-http warmup"

wrk -t10 -c200 -d60s "http://127.0.0.1:8080"

echo "akka-http start"

echo "## akka-http" >> result.md

wrk -t10 -c200 -d60s "http://127.0.0.1:8080" >> result.md

echo "akka-http end"

docker stop akka-http


# finatra-http
docker run -d --name finatra-http --cpus "${SERVER_CPUS}" --memory="${SERVER_MEMORY}" -p 8080:8888 finatra-http-benchmark:0.1.0

sleep 5

echo "finatra-http warmup"

wrk -t10 -c200 -d60s "http://127.0.0.1:8080"

echo "finatra-http start"

echo "## finatra-http" >> result.md

wrk -t10 -c200 -d60s "http://127.0.0.1:8080" >> result.md

echo "finatra-http end"

docker stop finatra-http


# finch
docker run -d --name finch --cpus "${SERVER_CPUS}" --memory="${SERVER_MEMORY}" -p 8080:8080 finch-benchmark:0.1.0

sleep 5

echo "finch warmup"

wrk -t10 -c200 -d60s "http://127.0.0.1:8080"

echo "finch start"

echo "## finch" >> result.md

wrk -t10 -c200 -d60s "http://127.0.0.1:8080" >> result.md

echo "finch end"

docker stop finch


# http4s
docker run -d --name http4s --cpus "${SERVER_CPUS}" --memory="${SERVER_MEMORY}" -p 8080:8080 http4s-benchmark:0.1.0

sleep 5

echo "http4s warmup"

wrk -t10 -c200 -d60s "http://127.0.0.1:8080"

echo "http4s start"

echo "## http4s" >> result.md

wrk -t10 -c200 -d60s "http://127.0.0.1:8080" >> result.md

echo "http4s end"

docker stop http4s


# vertx-web
docker run -d --name vertx-web --cpus "${SERVER_CPUS}" --memory="${SERVER_MEMORY}" -p 8080:8080 vertx-web-benchmark:0.1.0

sleep 5

echo "vertx-web warmup start"

wrk -t10 -c200 -d60s "http://127.0.0.1:8080"

echo "vertx-web start"

echo "## vertx-web" >> result.md

wrk -t10 -c200 -d60s "http://127.0.0.1:8080" >> result.md

echo "vertx-web end"

docker stop vertx-web


# zio-http
docker run -d --name zio-http --cpus "${SERVER_CPUS}" --memory="${SERVER_MEMORY}" -p 8080:8080 zio-http-benchmark:0.1.0

sleep 5

echo "zio-http warmup start"

wrk -t10 -c200 -d60s "http://127.0.0.1:8080"

echo "zio-http start"

echo "## zio-http" >> result.md

wrk -t10 -c200 -d60s "http://127.0.0.1:8080" >> result.md

echo "zio-http end"

docker stop zio-http
