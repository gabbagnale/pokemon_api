#!/bin/bash

mvn clean install

docker build -t pokemon_api -f Dockerfile .

docker run -p 8080:8080 pokemon_api
