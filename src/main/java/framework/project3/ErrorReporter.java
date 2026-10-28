package framework.project3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.antlr.v4.runtime.Token;

public final class ErrorReporter {
    private final List<Diagnostic> diagnostics = new ArrayList<>();
    private void report(Diagnostic.Kind kind, Token token) {
        diagnostics.add(new Diagnostic(kind, token.getLine() - 1, token.getText()));
    }
    public void undeclaredIdentifier(Token token) {
        report(Diagnostic.Kind.UNDECLARED_IDENTIFIER, token);
    }
    public void redefinedSymbol(Token token) {
        report(Diagnostic.Kind.REDEFINED_SYMBOL, token);
    }
    public void undefinedStruct(Token token) {
        report(Diagnostic.Kind.UNDEFINED_STRUCT, token);
    }
    public void redefinedStruct(Token token) {
        report(Diagnostic.Kind.REDEFINED_STRUCT, token);
    }
    public void duplicateMember(Token token) {
        report(Diagnostic.Kind.DUPLICATE_MEMBER, token);
    }
    public boolean hasErrors() { return !diagnostics.isEmpty(); }
    // Stable sort: same-line diagnostics remain in discovery order.
    public List<Diagnostic> getDiagnostics() {
        List<Diagnostic> out = new ArrayList<>(diagnostics);
        out.sort(Comparator.comparingInt(Diagnostic::getLine));
        return Collections.unmodifiableList(out);
    }
}
