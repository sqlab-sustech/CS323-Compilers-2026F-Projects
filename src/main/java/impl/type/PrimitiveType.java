package impl.type;
public final class PrimitiveType implements Type {
    public static final PrimitiveType INT = new PrimitiveType();
    private PrimitiveType() {}
    @Override public String prettyPrint() { return "int"; }
}
