package de.dta.feed;

import de.dta.feed.feedback.model.Feedback;
import de.dta.feed.feedback.model.Thumbnail;
import de.dta.feed.feedback.repository.FeedbackRepository;
import de.dta.feed.feedback.repository.ThumbnailRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
class FeedApplicationTests {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ThumbnailRepository thumbnailRepository;

    private Feedback feedback;
    List<Thumbnail> thumbnailList = new ArrayList<>();


    @BeforeEach
    void createFeedback() {
        feedback = Feedback.builder()
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

               feedback = feedbackRepository.save(feedback);
    }


    @BeforeEach
    void createThumbnails() {
        Thumbnail thumbnail1 = Thumbnail.builder()
                .title("Back")
                .url("https://api4.angular-buch.com/images/angular_auflage3.jpg")
                .feedback(feedback)
                .build();
        Thumbnail thumbnail2 = Thumbnail.builder()
                .title("Front")
                .url("https://api4.angular-buch.com/images/angular_auflage3.jpg")
                .feedback(feedback)
                .build();

        thumbnailList.add(thumbnail1);
        thumbnailList.add(thumbnail2);

        thumbnailList.forEach(thumbnail -> thumbnailRepository.save(thumbnail));
    }

    @Test
    void thumbnailSizeTest() {
      assertEquals(thumbnailList.size(),2);
    }

    @Test
    void feedbackFindByIdTest(){
       Optional<Feedback> testFeedback = feedbackRepository.findById(feedback.getId());
       assertEquals(testFeedback.get().getAuthor(),"assia@service");
    }

    @AfterEach
    void afterLoad() {
        thumbnailRepository.deleteAll();
        feedbackRepository.deleteAll();
    }

}
