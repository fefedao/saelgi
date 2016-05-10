#!/bin/bash

JAVA_OPTS="-Ddb2.jcc.charsetDecoderEncoder=3 -Djava.security.egd=file:/dev/./urandom -Xrs -Duser.language=pt -Duser.country=BR -Duser.timezone=America/Sao_Paulo -Dfile.encoding=UTF-8 $JAVA_OPTS"

function export_var() {
  eval VAL=\$$2
  if [ ! -z $VAL ]; then
    if [ ! -z $3 ]; then
      VAL=$3
    fi
    export $1=$VAL
    echo "config por link: $1=$VAL"
  fi
}

java $JAVA_OPTS -jar mniws.jar $@