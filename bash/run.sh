#!/usr/bin/env bash

if [[ $# -eq 0 ]] ; then
    echo 'no args supplied please provide an input'
    exit 1
else
  java -jar ./target/exercise-jar-with-dependencies.jar $1
fi