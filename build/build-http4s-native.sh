#!/usr/bin/env sh

cd $(dirname $0)

cd ..

echo $(pwd)

docker build . -t http4s-native-benchmark:0.1.0 -f build/http4s-native/Dockerfile
