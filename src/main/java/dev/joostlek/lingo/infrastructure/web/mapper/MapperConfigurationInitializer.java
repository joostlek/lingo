package dev.joostlek.lingo.infrastructure.web.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfigurationInitializer implements CommandLineRunner {

    private final ModelMapper modelMapper;

    public MapperConfigurationInitializer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void run(String... args) {
        MapperConfiguration[] mapperConfiguration = {
                new DictionaryIdMappingConfiguration(),
                new GameIdMappingConfiguration(),
                new GameMapperConfiguration(),
                new RoundIdMappingConfiguration(),
                new WordLengthMappingConfiguration(),
                new RoundMappingConfiguration(),
                new DictionaryMappingConfiguration(),
                new WordMappingConfiguration(),
                new WordIdMappingConfiguration(),
                new TurnIdMappingConfiguration(),
                new TurnMappingConfiguration(),
                new ResultMappingConfiguration(),
                new DictionarySizeMappingConfiguration(),
        };
        for (MapperConfiguration configuration : mapperConfiguration) {
            configuration.execute(modelMapper);
        }
    }
}
