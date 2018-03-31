#!/bin/bash
TYPE=$1
[ -z "$1" ] && TYPE="master"

kill $(cat ./$TYPE.pid) || true