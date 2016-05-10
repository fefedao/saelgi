#!/bin/bash
set -e

export WS_IMAGENAME="battycode/saelgi"
export WS_TAG="1.0.0-0"

PROJECT_DIR=$(readlink -f "$(dirname $0)/../../../../")
DOCKER_DIR=src/main/docker/build

MVN_BUILD="mvn clean -f pom.xml verify -DskipTests && chown -R $(id -u $USER) /app/target"

docker run --rm -v $PWD/settings.xml:/root/.m2/settings.xml -v /root/.m2 -v $PROJECT_DIR:/app -w /app andreptb/maven:3.3.9-jdk8 sh -c "$MVN_BUILD"
RESULT_CODE="$?"
if [ $RESULT_CODE != "0" ]; then
  exit RESULT_CODE
fi

docker build -f $PROJECT_DIR/Dockerfile -t $WS_IMAGENAME:$WS_TAG $PROJECT_DIR
