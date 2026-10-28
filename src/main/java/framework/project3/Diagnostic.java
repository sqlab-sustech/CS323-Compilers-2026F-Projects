package framework.project3;

public final class Diagnostic {
    public enum Kind {
        UNDECLARED_IDENTIFIER,
        REDEFINED_SYMBOL,
        UNDEFINED_STRUCT,
        REDEFINED_STRUCT,
        DUPLICATE_MEMBER
    }
    private final Kind kind;
    private final int line;
    private final String name;
    public Diagnostic(Kind kind, int line, String name) {
        this.kind = kind; this.line = line; this.name = name;
    }
    public Kind getKind() { return kind; }
    public int getLine() { return line; }
    public String getName() { return name; }
    @Override public String toString() {
        return "Error " + kind.name() + " at Line " + line + ": " + name;
    }
}
