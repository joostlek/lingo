package dev.joostlek.lingo.infrastructure.web.mapper;

import dev.joostlek.lingo.domain.model.game.round.Feedback;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

public class FeedbackMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        Converter<Feedback, String> feedbackConverter = new AbstractConverter<>() {
            @Override
            protected String convert(Feedback source) {
                return source.toString();
            }
        };

        modelMapper.addConverter(feedbackConverter);
    }
}
