#!/bin/bash
if [[ $# -eq 0 ]];then
   echo "no args supplied please provide an input"
else
   java -jar "../target/exercise-jar-with-dependencies.jar" $1
fi