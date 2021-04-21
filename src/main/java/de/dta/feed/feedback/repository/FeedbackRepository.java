package de.dta.feed.feedback.repository;


import de.dta.feed.feedback.model.Feedback;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {

    List<Feedback> findAll();

    @Query("select c from Feedback c where c.lineId = :lineId")
    List<Feedback> findAllByLineId(@Param("lineId") String lineId);

    @Query("select c from Feedback c where c.username = :username")
    List<Feedback> findAllByUsername(@Param("username") String username);

    @Query("select c from Feedback c where c.lineId like %:lineId%")
    List<Feedback> searchAllFeedbackByLineId(String lineId);

    @Query("select c from Feedback c where c.lineId = :lineId")
    Feedback findByLineId(@Param("lineId") String lineId);

    //@Query("delete from Feedback c where c.lineId = :lineId")
    Integer deleteByLineId(@Param("lineId") String lineId);
}
