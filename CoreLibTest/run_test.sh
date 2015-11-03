#!/bin/bash

clear ;

if [ -z $1 ]; then
	echo "For what class?"
	exit 1;
fi

LIB=".:../CoreLib/bin/CoreLib.jar:./Jar/"

javac -cp $LIB $1.java 

java -classpath $LIB $1
