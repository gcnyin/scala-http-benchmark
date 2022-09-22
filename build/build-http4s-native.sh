#!/bin/bash

SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
cd $SCRIPT_DIR/..
docker build . -t http4s-ce3-native-benchmark:1.0-SNAPSHOT -f  build/http4s-native/Dockerfile
