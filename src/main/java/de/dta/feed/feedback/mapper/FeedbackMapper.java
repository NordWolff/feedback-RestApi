package de.dta.feed.feedback.mapper;

import de.dta.feed.feedback.model.Feedback;
import org.mapstruct.factory.Mappers;

public interface FeedbackMapper {
    FeedbackMapper MAPPER = Mappers.getMapper(FeedbackMapper.class);

    void toFeedback(Feedback feedback);
}
