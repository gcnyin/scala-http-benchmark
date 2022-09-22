#!/usr/bin/env sh

cd $(dirname $0)

echo $(pwd)

docker build . -t http4s-ce3-native-benchmark:0.1.0 -f ./http4s-native/Dockerfile
