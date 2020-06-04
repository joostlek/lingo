package dev.joostlek.lingo.application.dictionary.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateDictionaryCommand {
    private String language;

    public CreateDictionaryCommand(String language) {
        super();

        this.language = language;
    }
}
