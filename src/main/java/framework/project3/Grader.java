package framework.project3;
import impl.symbol.*;
import impl.type.*;

// All visible program output belongs to the framework, not student code.
public final class Grader {
    private Grader() {}
    public static void print(ErrorReporter reporter, GlobalScope scope) {
        if (reporter.hasErrors()) {
            for (Diagnostic diagnostic : reporter.getDiagnostics()) {
                System.out.println(diagnostic);
            }
            return;
        }
        System.out.println("OK");
        for (StructType type : scope.getStructTypes()) {
            System.out.println("Struct " + type.getName());
            for (VariableSymbol member : type.getMembers()) {
                System.out.println("  Field " + member.getName() + ": "
                        + member.getType().prettyPrint());
            }
        }
        for (Symbol symbol : scope.getLocalSymbols()) {
            if (symbol instanceof VariableSymbol v) {
                System.out.println("Variable " + v.getName() + ": "
                        + v.getType().prettyPrint());
            } else if (symbol instanceof FunctionSymbol function) {
                StringBuilder sb = new StringBuilder();
                for (VariableSymbol p : function.getParameters()) {
                    if (sb.length() > 0) sb.append(", ");
                    sb.append(p.getType().prettyPrint()).append(" ").append(p.getName());
                }
                System.out.println("Function " + function.getName() + "("
                        + sb + "): " + function.getType().prettyPrint());
            }
        }
    }
}
