package impl;
import framework.project3.ErrorReporter;
import generated.Splc;
import generated.SplcBaseVisitor;
import impl.symbol.*;
import impl.type.*;
import org.antlr.v4.runtime.Token;
import java.util.List;

// Student's main semantic-analysis implementation.
public final class NameResolver extends SplcBaseVisitor<Void> {
    private final GlobalScope globalScope = new GlobalScope();
    private Scope currentScope = globalScope;
    private final ErrorReporter reporter;
    public NameResolver(ErrorReporter reporter) { this.reporter = reporter; }
    public GlobalScope getGlobalScope() { return globalScope; }

    public void analyze(Splc.ProgramContext program) {
        // TODO: Visit global definitions IN SOURCE ORDER.
        visit(program);
    }
    private Type resolveType(Splc.TypeContext ctx) {
        // TODO: INT -> PrimitiveType.INT.
        // STRUCT Identifier -> look up ONLY previously completed struct.
        // On undefined struct: reporter.undefinedStruct(nameToken), return null.
        throw new UnsupportedOperationException("TODO: resolveType");
    }
    private Type resolveDeclaredType(Splc.VariableDeclContext ctx) {
        // TODO: Resolve base type and wrap dimensions from RIGHT to LEFT.
        // e.g. int a[2][3] -> ArrayType(ArrayType(INT, 3), 2).
        throw new UnsupportedOperationException("TODO: resolveDeclaredType");
    }
    private void enterScope() { currentScope = new Scope(currentScope); }
    private void exitScope() { currentScope = currentScope.getParent(); }

    @Override public Void visitStructDef(Splc.StructDefContext ctx) {
        // TODO: reject duplicate struct name; build members in declaration
        // order; reject unknown member types and duplicate members;
        // register struct ONLY after all its fields have been processed.
        return null;
    }
    @Override public Void visitGlobalVarDef(Splc.GlobalVarDefContext ctx) {
        // TODO: resolve declared type, then define in global scope.
        return null;
    }
    @Override public Void visitFunctionDef(Splc.FunctionDefContext ctx) {
        // TODO: resolve return type; define the FunctionSymbol in global
        // scope BEFORE its body (allow direct recursion, not forward calls).
        // TODO: use FunctionSymbol as current scope, insert params, then
        // traverse the OUTERMOST body statements without opening new scope.
        return null;
    }
    @Override public Void visitBlock(Splc.BlockContext ctx) {
        // TODO: nested block creates a new Scope, always restore on exit.
        return null;
    }
    @Override public Void visitLocalVarDef(Splc.LocalVarDefContext ctx) {
        // TODO: resolve type, define variable in current scope, and visit
        // initializer if present; do not type-check initializer.
        return null;
    }
    @Override public Void visitExpression(Splc.ExpressionContext ctx) {
        // TODO: resolve ordinary Identifier and function-call name.
        // TODO: visit every operand/argument expression.
        // SPECIAL CASE: in 'expression DOT Identifier', the trailing
        // Identifier is a MEMBER NAME, NOT an ordinary lexical reference.
        // Do not resolve the DOT member here; P4 handles member existence.
        return null;
    }
}
