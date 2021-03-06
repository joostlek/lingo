package dev.joostlek.lingo.domain;

public abstract class Entity extends AssertionConcern {

    private int concurrencyVersion;

    public Entity() {
        super();

        this.setConcurrencyVersion(0);
    }

    public int concurrencyVersion() {
        return this.concurrencyVersion;
    }

    private void setConcurrencyVersion(int aConcurrencyVersion) {
        this.concurrencyVersion = aConcurrencyVersion;
    }
}
