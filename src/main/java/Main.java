import framework.project3.ErrorReporter;
import framework.project3.Grader;
import generated.SplLexer;
import generated.Splc;
import impl.NameResolver;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * Project 3 entry point. All test inputs are guaranteed to be syntactically
 * valid. This project deliberately does not implement or grade syntax-error
 * recovery; it focuses on semantic analysis only.
 */
public final class Main {
    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromStream(System.in);
        SplLexer lexer = new SplLexer(input);
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Splc parser = new Splc(tokens);
        parser.removeErrorListeners();

        Splc.ProgramContext program = parser.program();
        ErrorReporter reporter = new ErrorReporter();
        NameResolver resolver = new NameResolver(reporter);
        resolver.analyze(program);
        Grader.print(reporter, resolver.getGlobalScope());
    }
}
