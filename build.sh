#!/bin/bash

if groups $USER | grep &>/dev/null '\bdocker\b'; then
  DOCKER="docker"
else
  DOCKER="sudo docker"
fi

$DOCKER build -t hbpmip/mipmap-demo-ehr-to-i2b2 --build-arg BUILD_DATE=$(date --iso-8601=seconds) .
