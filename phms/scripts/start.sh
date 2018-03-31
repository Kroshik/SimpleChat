#!/bin/bash
TYPE=$1
[ -z "$1" ] && TYPE="master"
[ "$TYPE" == "master" ] && PORT="8000"
[ "$TYPE" == "develop" ] && PORT="8080"

java -Xmx512M -Xms128M -XX:MaxPermSize=64M -server -Dserver.port=$PORT -jar build/libs/phms-$(./gradlew -q version).jar & echo $! > ./$TYPE.pid &