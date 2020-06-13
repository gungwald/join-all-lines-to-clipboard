#!/bin/sh
PROGRAM_NAME=`basename "$0" .sh`
BIN_DIR=`dirname "$0"` && [ "$BIN_DIR" = "." ] && BIN_DIR=`pwd`
TARGET_DIR="$BIN_DIR"/target

existsWithPrefixAndSuffix()
{
	ls "$1"*"$2" > /dev/null 2>&1
}

while true
do
	if existsWithPrefixAndSuffix "$TARGET_DIR/$PROGRAM_NAME-" ".jar"
	then
		EXECUTABLE_JAR=`"$BIN_DIR"/target/"$PROGRAM_NAME"-*.jar | tail -1`
		exec java -jar "$EXECUTABLE_JAR" "$@"
	else
		printf "Program jar not found. Do you want to build it\? (y/n) "
		read ANSWER
		if [ "$ANSWER" = "y" ]
		then
			mvn clean package
		else
			break
		fi
	fi
done