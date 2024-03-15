module de.holube.vac {
    requires static lombok;
    requires org.apache.commons.lang3;
    requires org.apache.commons.collections4;

    exports de.holube.vac.collections;
    exports de.holube.vac.stream;
    exports de.holube.vac.stream.list;
}