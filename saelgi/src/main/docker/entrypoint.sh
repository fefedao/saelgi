#!/bin/sh

export_var(){
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

exec $@