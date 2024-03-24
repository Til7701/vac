package de.holube.vac.lang.interpreter;

import de.holube.vac.lang.LangLexer;
import de.holube.vac.lang.LangParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class InterpreterMain {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Please provide a file to interpret");
            System.exit(1);
        }

        String file = args[0];
        LangLexer lexer = new LangLexer(CharStreams.fromFileName(file));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LangParser parser = new LangParser(tokens);
        ParseTree tree = parser.print();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new LangInterpreter(), tree);
    }

}
