package dev.joostlek.lingo.util;

import org.modelmapper.ModelMapper;

public interface MapperConfiguration {
    void execute(ModelMapper modelMapper);
}
