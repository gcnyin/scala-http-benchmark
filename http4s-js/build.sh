#!/usr/bin/env sh

cd $(dirname $0)

echo $(pwd)

docker build . -t http4s-ce3-js-benchmark:0.1.0
