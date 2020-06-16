#!/bin/sh
PROGRAM_NAME=`basename "$0" .sh`
BIN_DIR=`dirname "$0"` && [ "$BIN_DIR" = "." ] && BIN_DIR=`pwd`
TARGET_DIR="$BIN_DIR"

existsWithPrefixAndSuffix()
{
	ls "$1"*"$2" > /dev/null 2>&1
}

if existsWithPrefixAndSuffix "$TARGET_DIR/$PROGRAM_NAME" ".jar"
then
	EXECUTABLE_JAR=`ls "$TARGET_DIR"/"$PROGRAM_NAME"*.jar | tail -1`
	exec java -jar "$EXECUTABLE_JAR" "$@"
else
	printf "Program jar not found.\n" 1>&2
fi