module de.holube.vac {
    requires static lombok;
    requires org.apache.commons.collections4;
    requires org.apache.bcel;
    requires org.antlr.antlr4.runtime;

    exports de.holube.vac.array;
    exports de.holube.vac.collections;
    exports de.holube.vac.stream;
}