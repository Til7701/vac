package de.holube.vac.lang;

import org.apache.bcel.util.BCELifier;

public class BCELifierStuff {

    public static void main(String[] args) throws Exception {
        BCELifier.main(new String[]{HelloWorld.class.getName()});
    }

}
