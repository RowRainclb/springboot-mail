#!/usr/bin/env bash

mvn clean

mvn package -Dmaven.test.skip=true

mv ./target/k2alpha-mail-send-1.0-SNAPSHOT.jar ./target/k2alpha-mail-send.jar

echo "Build success. target: ./target/k2alpha-mail-send.jar"