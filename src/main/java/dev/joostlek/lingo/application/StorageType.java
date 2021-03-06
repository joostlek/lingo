package dev.joostlek.lingo.application;

public enum StorageType {
    IN_MEMORY("memory"),
    DATABASE("database");

    private final String beanName;

    StorageType(String beanName) {
        this.beanName = beanName;
    }

    public String beanName() {
        return beanName;
    }
}
