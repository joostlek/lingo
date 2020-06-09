package dev.joostlek.lingo.infrastructure.web.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MapperConfigurationInitializer implements CommandLineRunner {

    private final List<MapperConfiguration> mapperConfiguration;

    private final ModelMapper modelMapper;

    public MapperConfigurationInitializer(List<MapperConfiguration> mapperConfiguration, ModelMapper modelMapper) {
        this.mapperConfiguration = mapperConfiguration;
        this.modelMapper = modelMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        for (MapperConfiguration configuration : mapperConfiguration) {
            configuration.execute(modelMapper);
        }
    }
}
