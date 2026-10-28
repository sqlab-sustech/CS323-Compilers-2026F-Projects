package impl.symbol;
import impl.type.Type;
public final class VariableSymbol implements Symbol {
    private final String name;
    private final Type type;
    public VariableSymbol(String name, Type type) {
        this.name = name; this.type = type;
    }
    @Override public String getName() { return name; }
    @Override public Type getType() { return type; }
}
