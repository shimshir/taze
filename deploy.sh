#!/bin/bash

cd ./src/main/resources/static/
webpack
cd ../../../../
git push origin master
