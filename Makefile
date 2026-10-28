JAVA ?= java
JAVAC ?= javac
ANTLR_JAR ?= libs/antlr-4.13.2-complete.jar
MAIN := Main
GENERATED_DIR := target/generated
CLASS_DIR := target/classes
JAR_FILE := target/project3.jar
HANDIN_FILE := target/project3-handin.zip
PUBLIC_IN := testcases/project3/in
PUBLIC_OUT := testcases/project3/out
HIDDEN_IN := testcases/project3/hidden/in
HIDDEN_OUT := testcases/project3/hidden/out

.PHONY: all generate compile build run test test-hidden test-all handin clean
all: build

generate:
	@test -f "$(ANTLR_JAR)" || (echo "ANTLR jar not found: $(ANTLR_JAR)" >&2; exit 1)
	@rm -rf $(GENERATED_DIR)
	@mkdir -p $(GENERATED_DIR)
	$(JAVA) -jar "$(ANTLR_JAR)" -Dlanguage=Java -package generated -o $(GENERATED_DIR) SplLexer.g4
	$(JAVA) -jar "$(ANTLR_JAR)" -Dlanguage=Java -visitor -no-listener -package generated -lib $(GENERATED_DIR) -o $(GENERATED_DIR) Splc.g4

compile: generate
	@rm -rf $(CLASS_DIR)
	@mkdir -p $(CLASS_DIR)
	$(JAVAC) -encoding UTF-8 --release 17 -cp "$(ANTLR_JAR)" -d $(CLASS_DIR) $$(find src/main/java -name '*.java') $$(find $(GENERATED_DIR) -name '*.java')

build: compile
	@mkdir -p target
	jar --create --file $(JAR_FILE) --main-class $(MAIN) -C $(CLASS_DIR) .

run: build
	$(JAVA) -Xms16m -Xmx128m -XX:MaxMetaspaceSize=128m -cp "$(JAR_FILE):$(ANTLR_JAR)" $(MAIN)

test: build
	bash scripts/run_tests.sh "$(ANTLR_JAR)" "$(PUBLIC_IN)" "$(PUBLIC_OUT)"

test-hidden: build
	bash scripts/run_tests.sh "$(ANTLR_JAR)" "$(HIDDEN_IN)" "$(HIDDEN_OUT)"

test-all: test test-hidden

handin:
	@mkdir -p target
	@rm -f "$(HANDIN_FILE)"
	zip -q "$(HANDIN_FILE)" \
		src/main/java/impl/NameResolver.java \
		src/main/java/impl/type/StructType.java \
		src/main/java/impl/symbol/Scope.java \
		src/main/java/impl/symbol/FunctionSymbol.java \
		src/main/java/impl/symbol/GlobalScope.java
	@echo "Created $(HANDIN_FILE)"
	@echo "Submission entries:"
	@unzip -Z1 "$(HANDIN_FILE)"

clean:
	rm -rf target
