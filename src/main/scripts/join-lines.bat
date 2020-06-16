@echo off

setlocal

set PROGRAM_NAME=%~n0
set BIN_DIR=%~dp0
set TARGET_DIR=%BIN_DIR%
set EXECUTABLE_JAR=

for %%J in ("%TARGET_DIR%/%PROGRAM_NAME%*.jar") do (
	set EXECUTABLE_JAR=%%J
)

if defined EXECUTABLE_JAR (
	java -jar "%EXECUTABLE_JAR%" %*
) else (
	echo %PROGRAM_NAME%: program jar not found. 1>&2
)

endlocal