package impl.symbol;
import impl.type.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// A function is BOTH a named Symbol (in global scope) and a Scope
// (containing parameters and top-level locals in its body).
public final class FunctionSymbol extends Scope implements Symbol {
    private final String name;
    private final Type returnType;
    private final List<VariableSymbol> parameters = new ArrayList<>();
    public FunctionSymbol(String name, Type returnType, Scope parent) {
        super(parent); this.name = name; this.returnType = returnType;
    }
    @Override public String getName() { return name; }
    @Override public Type getType() { return returnType; }
    public List<VariableSymbol> getParameters() {
        return Collections.unmodifiableList(parameters);
    }
    // TODO: insert a parameter into THIS function scope, then append it to
    // the ordered parameter list. Duplicate parameter => false.
    public boolean addParameter(VariableSymbol parameter) {
        throw new UnsupportedOperationException("TODO: FunctionSymbol.addParameter");
    }
}
