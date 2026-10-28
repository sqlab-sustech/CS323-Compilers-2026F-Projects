package impl.type;
public final class ArrayType implements Type {
    private final Type elementType;
    private final int length;
    public ArrayType(Type elementType, int length) {
        this.elementType = elementType;
        this.length = length;
    }
    public Type getElementType() { return elementType; }
    public int getLength() { return length; }
    // ArrayType(INT, 3) prints int[3]; outer array dimensions come first.
    @Override public String prettyPrint() {
        StringBuilder dimensions = new StringBuilder();
        Type base = this;
        while (base instanceof ArrayType array) {
            dimensions.append("[").append(array.getLength()).append("]");
            base = array.getElementType();
        }
        return base.prettyPrint() + dimensions;
    }
}
