#!/usr/bin/env sh
# Compile and launch "Le Monde du Forgeron" (requires a JDK 11+)
cd "$(dirname "$0")/src" || exit 1
mkdir -p ../build
javac -encoding UTF-8 -d ../build *.java && java -cp "../build:." Game
