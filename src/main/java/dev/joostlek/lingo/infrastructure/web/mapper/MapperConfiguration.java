package dev.joostlek.lingo.infrastructure.web.mapper;

import org.modelmapper.ModelMapper;

public interface MapperConfiguration {
    void execute(ModelMapper modelMapper);
}
