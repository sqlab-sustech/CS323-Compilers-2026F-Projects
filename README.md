# CS323 Project 3 — Student Starter

Project 3 focuses on semantic analysis: type representation, symbol tables, scope management, and name resolution. **All graded input programs are syntactically valid. Syntax-error reporting/recovery is outside this project.**

## Build and test

Use Java 17 and ANTLR 4.13.2. Put `antlr-4.13.2-complete.jar` in `libs/` (or pass `ANTLR_JAR=/path/to/antlr-4.13.2-complete.jar`):

```bash
make build
make test
make run < testcases/project3/in/003_struct_and_var.splc
make handin
```

`make handin` creates `target/project3-handin.zip`. The ZIP contains exactly the five student implementation files, with their `src/main/java/...` paths preserved.

## Student implementation boundary

Implement only these five files:

- `src/main/java/impl/NameResolver.java`
- `src/main/java/impl/type/StructType.java`
- `src/main/java/impl/symbol/Scope.java`
- `src/main/java/impl/symbol/FunctionSymbol.java`
- `src/main/java/impl/symbol/GlobalScope.java`

Do not change their public APIs, the supplied grammar, framework classes, or grader output format. Build scripts and tests may be used for local verification; hidden tests are not included in the student package.
