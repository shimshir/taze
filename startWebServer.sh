#!/usr/bin/env bash

cd $(dirname ${BASH_SOURCE[0]})/src/main/resources/static
node server.js
