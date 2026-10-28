#!/usr/bin/env bash
set -euo pipefail
ANTLR_JAR="${1:?usage: run_tests.sh ANTLR_JAR INPUT_DIR OUTPUT_DIR}"
INPUT_DIR="${2:?usage: run_tests.sh ANTLR_JAR INPUT_DIR OUTPUT_DIR}"
OUTPUT_DIR="${3:?usage: run_tests.sh ANTLR_JAR INPUT_DIR OUTPUT_DIR}"
JAR_FILE="target/project3.jar"
count=0
passed=0
shopt -s nullglob
for input in "$INPUT_DIR"/*.splc; do
    count=$((count + 1))
    name="$(basename "$input" .splc)"
    expected="$OUTPUT_DIR/$name.out"
    if [[ ! -f "$expected" ]]; then
        echo "[MISSING] $expected" >&2
        exit 1
    fi
    actual="$(mktemp)"
    java -Xms16m -Xmx128m -XX:MaxMetaspaceSize=128m \
      -cp "$JAR_FILE:$ANTLR_JAR" Main < "$input" > "$actual"
    if diff -u "$expected" "$actual"; then
        echo "[PASS] $name"
        passed=$((passed + 1))
    else
        echo "[FAIL] $name"
    fi
    rm -f "$actual"
done
if [[ "$count" -eq 0 ]]; then
    echo "No test cases found in $INPUT_DIR" >&2
    exit 1
fi
echo "Result: $passed / $count passed"
[[ "$passed" -eq "$count" ]]
