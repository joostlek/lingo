package dev.joostlek.lingo.infrastructure.persistency.jpa.mapper;

import dev.joostlek.lingo.util.MapperConfiguration;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaMapperConfigurationInitializer implements CommandLineRunner {

    private final ModelMapper modelMapper;

    public JpaMapperConfigurationInitializer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public void run(String... args) {
        MapperConfiguration[] mapperConfiguration = {
                new DictionaryMappingConfiguration(),
                new DictionaryIdMappingConfiguration(),
                new WordMappingConfiguration(),
                new WordIdMappingConfiguration(),
                new GameMappingConfiguration(),
                new GameIdMappingConfiguration(),
                new RoundMappingConfiguration(),
                new RoundIdMappingConfiguration(),
                new TurnMappingConfiguration(),
                new TurnIdMappingConfiguration(),
                new ResultMappingConfiguration(),
        };
        for (MapperConfiguration configuration : mapperConfiguration) {
            configuration.execute(modelMapper);
        }
    }
}
