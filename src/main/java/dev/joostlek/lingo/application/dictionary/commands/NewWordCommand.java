package dev.joostlek.lingo.application.dictionary.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NewWordCommand {
    private String dictionaryId;

    private String word;

    public NewWordCommand(String dictionaryId, String word) {
        super();

        this.dictionaryId = dictionaryId;
        this.word = word;
    }
}