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

export_var UNJ_CONSULTAPROCESSO_URLWEBSERVICE CONSULTAPROCESSOWS_PORT_8080_TCP_ADDR "http://$CONSULTAPROCESSOWS_PORT_8080_TCP_ADDR:8080/consultaprocessows"
export_var UNJ_PETWS_URLWEBSERVICE PETWS_PORT_8080_TCP_ADDR "http://$PETWS_PORT_8080_TCP_ADDR:8080/tjws/services/ServicoMNI?wsdl"
export_var UNJ_PETWSSG_URLWEBSERVICE PETWSSG_PORT_8080_TCP_ADDR "http://$PETWSSG_PORT_8080_TCP_ADDR:8080/tjwssg/services/ServicoMNI?wsdl"
export_var UNJ_PETWSSGCR_URLWEBSERVICE PETWSSGCR_PORT_8080_TCP_ADDR "http://$PETWSSGCR_PORT_8080_TCP_ADDR:8080/tjwssg/services/ServicoMNI?wsdl"

java $JAVA_OPTS -jar mniws.jar $@