package de.holube.vac.lang;

import org.apache.bcel.Const;
import org.apache.bcel.generic.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by BCELifier from HelloWorld.java (some modifications were made)
 */
public class HelloWorldCreator {

    private static final String CLASS_NAME = "de.holube.vac.lang.HelloWorld2";

    private final InstructionFactory instructionFactory;
    private final ConstantPoolGen poolGen;
    private final ClassGen classGen;

    public HelloWorldCreator() {
        classGen = new ClassGen(CLASS_NAME, "java.lang.Object", "HelloWorld2.java", Const.ACC_PUBLIC | Const.ACC_SUPER, new String[0]);
        classGen.setMajor(Const.MAJOR_21);
        classGen.setMinor(Const.MINOR_21);

        poolGen = classGen.getConstantPool();
        instructionFactory = new InstructionFactory(classGen, poolGen);
    }

    public static void main(String[] args) throws Exception {
        HelloWorldCreator creator = new HelloWorldCreator();
        creator.create(new FileOutputStream("src/main/java/de/holube/vac/lang/HelloWorld2.class"));
    }

    public void create(OutputStream out) throws IOException {
        createConstructor();
        createMain();
        classGen.getJavaClass().dump(out);
    }

    private void createConstructor() {
        InstructionList il = new InstructionList();
        MethodGen method = new MethodGen(Const.ACC_PUBLIC, Type.VOID, Type.NO_ARGS, new String[0], "<init>", CLASS_NAME, il, poolGen);

        il.append(InstructionFactory.createLoad(Type.OBJECT, 0));
        il.append(instructionFactory.createInvoke("java.lang.Object", "<init>", Type.VOID, Type.NO_ARGS, Const.INVOKESPECIAL));
        il.append(InstructionFactory.createReturn(Type.VOID));

        method.setMaxStack();
        method.setMaxLocals();
        classGen.addMethod(method.getMethod());
        il.dispose();
    }

    private void createMain() {
        InstructionList il = new InstructionList();
        MethodGen method = new MethodGen(Const.ACC_PUBLIC | Const.ACC_STATIC, Type.VOID, new Type[]{new ArrayType(Type.STRING, 1)}, new String[]{"args"}, "main", CLASS_NAME, il, poolGen);

        il.append(instructionFactory.createFieldAccess("java.lang.System", "out", new ObjectType("java.io.PrintStream"), Const.GETSTATIC));
        il.append(new PUSH(poolGen, "Hello World"));
        il.append(instructionFactory.createInvoke("java.io.PrintStream", "println", Type.VOID, new Type[]{Type.STRING}, Const.INVOKEVIRTUAL));
        il.append(InstructionFactory.createReturn(Type.VOID));

        method.setMaxStack();
        method.setMaxLocals();
        classGen.addMethod(method.getMethod());
        il.dispose();
    }

}