#!/bin/bash

set -x

./build.sh

docker build -t dev.k2data.com.cn:5001/k2data/k2alpha-mail:dev-0.1.0 .
docker push dev.k2data.com.cn:5001/k2data/k2alpha-mail:dev-0.1.0
