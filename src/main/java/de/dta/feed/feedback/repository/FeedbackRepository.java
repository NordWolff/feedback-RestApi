package de.dta.feed.feedback.repository;


import de.dta.feed.feedback.model.Feedback;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {

    @Query("select c from Feedback c where c.lineId = :lineId")
    List<Feedback> findAllByLineId(String lineId);

    @Query("select c from Feedback c where c.username = :username")
    List<Feedback> findAllByUsername(String username);

    @Query("select c from Feedback c where c.lineId like %:lineId%")
    Feedback[] searchAllFeedbackByLineId(String lineId);
}
