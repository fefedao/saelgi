#!/bin/bash
set -e

export WS_IMAGENAME="docker-web.softplan.com.br/unj/mniws"
export WS_TAG="1.3.2-6"

PROJECT_DIR=$(readlink -f "$(dirname $0)/../../../../")
DOCKER_DIR=src/main/docker/build

MVN_BUILD="mvn clean -f pom.xml verify -DskipTests && chown -R $(id -u $USER) /app/target"

#Busca o arquivo de configuração do maven no server //server21.softplan.com.br/O/saj/versoes/interna/fWeb/componentes/docker (O:/saj/versoes/interna/fWeb/componentes/docker)
rm -rf $PWD/settings.xml
wget -R -q -t 1 --timeout=20 http://unj-versoes.softplan.com.br/interna/fWeb/componentes/docker/settings.xml -P $PWD

docker run --rm -v $PWD/settings.xml:/root/.m2/settings.xml -v /root/.m2 -v $PROJECT_DIR:/app -w /app andreptb/maven:3.3.9-jdk8 sh -c "$MVN_BUILD"
RESULT_CODE="$?"
if [ $RESULT_CODE != "0" ]; then
  exit RESULT_CODE
fi

docker build -f $PROJECT_DIR/Dockerfile -t $WS_IMAGENAME:$WS_TAG $PROJECT_DIR
