package dev.joostlek.lingo.domain.model;

public enum WordLength {
    FIVE(5),
    SIX(6),
    SEVEN(7);

    private final int length;

    WordLength(int length) {
        this.length = length;
    }

    public static WordLength valueOf(int length) {
        for (WordLength wordLength : WordLength.values()) {
            if (wordLength.length == length) return wordLength;
        }
        throw new IllegalArgumentException("Lengte is niet ondersteund door de applicatie");
    }

    public int getLength() {
        return length;
    }
}
