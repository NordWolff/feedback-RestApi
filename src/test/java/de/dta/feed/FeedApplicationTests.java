package de.dta.feed;

import de.dta.feed.feedback.model.Feedback;
import de.dta.feed.feedback.model.Thumbnail;
import de.dta.feed.feedback.repository.FeedbackRepository;
import de.dta.feed.feedback.repository.ThumbnailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class FeedApplicationTests {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ThumbnailRepository thumbnailRepository;

    @BeforeEach
    void before() {

    }

    @Test
    void contextLoads() {
        thumbnailRepository.deleteAll();
        feedbackRepository.deleteAll();
        Thumbnail thumbnail1 = new Thumbnail();
        Thumbnail thumbnail2 = new Thumbnail();


        Feedback feedback = Feedback.builder()
                .attr("Fix/Verkabelung")
                .freeText("Hier steht ein Text")
                .author("assia@service")
                .incidentSeverity("häufig(jeden Tag)")
                .incidentType("Internet Ausfall")
                .knownError("WLAN")
                .lineId("DEU.DTAG.KHG051")
                .username("thomas.wolff@telekom.de")
                .resolvedCi("KVz/Kabel zum KVz")
                .rating(2)
                .build();

        thumbnail1 = Thumbnail.builder()
                .title("Back")
                .url("https://api4.angular-buch.com/images/angular_auflage3.jpg")
                .feedback(feedback)
                .build();
        thumbnail2 = Thumbnail.builder()
                .title("Front")
                .url("https://api4.angular-buch.com/images/angular_auflage3.jpg")
                .feedback(feedback)
                .build();

        List<Thumbnail> thumbnailList = new ArrayList<>();
        thumbnailList.add(thumbnail1);
        thumbnailList.add(thumbnail2);

        thumbnailRepository.saveAll(thumbnailList);

        feedback = feedbackRepository.save(feedback);

        System.out.println(feedback.toString());
    }

}
