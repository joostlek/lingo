package dev.joostlek.lingo.domain.model.game.round;

public class Result {
    private char character;

    private int position;

    private Feedback feedback;

    private Result(char character, int position, Feedback feedback) {
        this.character = character;
        this.position = position;
        this.feedback = feedback;
    }

    public static Result correct(char character, int position) {
        return new Result(character, position, Feedback.CORRECT);
    }

    public static Result present(char character, int position) {
        return new Result(character, position, Feedback.PRESENT);
    }

    public static Result absent(char character, int position) {
        return new Result(character, position, Feedback.ABSENT);
    }

    public static Result invalid(char character, int position) {
        return new Result(character, position, Feedback.INVALID);
    }

    public char character() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int position() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Feedback feedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
}
