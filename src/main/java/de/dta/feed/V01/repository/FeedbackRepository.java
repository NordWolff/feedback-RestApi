package de.dta.feed.V01.repository;


import de.dta.feed.V01.model.Feedback;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {

    @Query("select c from Feedback c where c.lineId = :lineId")
    List<Feedback> findAllByLineId(String lineId);

    @Query("select c from Feedback c where c.username = :username")
    List<Feedback> findAllByUsername(String username);

    @Query("select c from Feedback c where c.lineId like '%in%'")
    List<Feedback> searchAllFeedbackByLineId(String lineId);
}
