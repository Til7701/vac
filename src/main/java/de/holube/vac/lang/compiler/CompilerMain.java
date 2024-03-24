package de.holube.vac.lang.compiler;

import de.holube.vac.lang.LangLexer;
import de.holube.vac.lang.LangParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.bcel.classfile.JavaClass;

import java.io.File;
import java.io.IOException;

public class CompilerMain {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Please provide a file to interpret");
            System.exit(1);
        }
        String fileName = args[0];
        File file = new File(fileName);
        File outDir = file.getParentFile();

        LangLexer lexer = new LangLexer(CharStreams.fromFileName(fileName));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LangParser parser = new LangParser(tokens);
        ParseTree tree = parser.file();
        ParseTreeWalker walker = new ParseTreeWalker();
        LangCompiler compiler = new LangCompiler();
        walker.walk(compiler, tree);
        JavaClass javaClass = compiler.getJavaClass();

        String className = javaClass.getClassName();
        String fileNameWithoutExtension = className.substring(className.lastIndexOf('.') + 1);
        javaClass.dump(outDir.getAbsolutePath() + "/" + fileNameWithoutExtension + ".class");
    }

}
