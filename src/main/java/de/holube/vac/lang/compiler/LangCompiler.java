package de.holube.vac.lang.compiler;

import de.holube.vac.lang.LangBaseListener;
import de.holube.vac.lang.LangParser;
import org.apache.bcel.Const;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.*;

public class LangCompiler extends LangBaseListener {

    private static final String CLASS_NAME = "HelloWorld";

    private final InstructionFactory instructionFactory;
    private final ConstantPoolGen poolGen;
    private final ClassGen classGen;

    private final InstructionList il = new InstructionList();
    private final MethodGen method;

    public LangCompiler() {
        classGen = new ClassGen(CLASS_NAME, "java.lang.Object", "HelloWorld.java", Const.ACC_PUBLIC | Const.ACC_SUPER, new String[0]);
        classGen.setMajor(Const.MAJOR_21);
        classGen.setMinor(Const.MINOR_21);

        poolGen = classGen.getConstantPool();
        instructionFactory = new InstructionFactory(classGen, poolGen);
        createConstructor();
        method = new MethodGen(Const.ACC_PUBLIC | Const.ACC_STATIC, Type.VOID, new Type[]{new ArrayType(Type.STRING, 1)}, new String[]{"args"}, "main", CLASS_NAME, il, poolGen);
    }

    public JavaClass getJavaClass() {
        il.append(InstructionFactory.createReturn(Type.VOID));

        method.setMaxStack();
        method.setMaxLocals();
        classGen.addMethod(method.getMethod());
        il.dispose();
        return classGen.getJavaClass();
    }

    private void createConstructor() {
        InstructionList il = new InstructionList();
        MethodGen constructorMethod = new MethodGen(Const.ACC_PUBLIC, Type.VOID, Type.NO_ARGS, new String[0], "<init>", CLASS_NAME, il, poolGen);

        il.append(InstructionFactory.createLoad(Type.OBJECT, 0));
        il.append(instructionFactory.createInvoke("java.lang.Object", "<init>", Type.VOID, Type.NO_ARGS, Const.INVOKESPECIAL));
        il.append(InstructionFactory.createReturn(Type.VOID));

        constructorMethod.setMaxStack();
        constructorMethod.setMaxLocals();
        classGen.addMethod(constructorMethod.getMethod());
        il.dispose();
    }

    @Override
    public void enterPrint(LangParser.PrintContext ctx) {
        super.enterPrint(ctx);
        String text = ctx.STRING().getText();

        il.append(instructionFactory.createFieldAccess("java.lang.System", "out", new ObjectType("java.io.PrintStream"), Const.GETSTATIC));
        il.append(new PUSH(poolGen, text));
        il.append(instructionFactory.createInvoke("java.io.PrintStream", "println", Type.VOID, new Type[]{Type.STRING}, Const.INVOKEVIRTUAL));
    }

}
