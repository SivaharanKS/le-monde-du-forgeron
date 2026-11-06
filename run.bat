@echo off
rem Compile and launch "Le Monde du Forgeron" (requires a JDK 11+)
cd /d "%~dp0src"
if not exist ..\build mkdir ..\build
javac -encoding UTF-8 -d ..\build *.java || (pause & exit /b 1)
java -cp "..\build;." Game
