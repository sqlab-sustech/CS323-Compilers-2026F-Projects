package impl.symbol;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Scope {
    protected final Map<String, Symbol> symbols = new LinkedHashMap<>();
    private final Scope parent;
    public Scope(Scope parent) { this.parent = parent; }
    public Scope getParent() { return parent; }
    public Collection<Symbol> getLocalSymbols() {
        return Collections.unmodifiableCollection(symbols.values());
    }
    // TODO: check ONLY current scope for a duplicate; permit shadowing.
    public boolean define(Symbol symbol) {
        throw new UnsupportedOperationException("TODO: Scope.define");
    }
    // TODO: current scope only.
    public Symbol resolveLocal(String name) {
        throw new UnsupportedOperationException("TODO: Scope.resolveLocal");
    }
    // TODO: current scope, then recursively parent; return null if missing.
    public Symbol resolve(String name) {
        throw new UnsupportedOperationException("TODO: Scope.resolve");
    }
}
