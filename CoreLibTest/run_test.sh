#!/bin/bash

reset ;

if [ -z $1 ]; then
	echo "For what class?"
	for f in `ls *.java -1`
	do
		echo " - $f";
	done
	exit 1;
fi

LIB=".:../CoreLib/bin/CoreLib.jar:./Jar/"

javac -cp $LIB $1.java 

java -classpath $LIB $1
