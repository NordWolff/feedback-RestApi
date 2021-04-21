package de.dta.feed.feedback.repository;

import de.dta.feed.feedback.model.Thumbnail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThumbnailRepository extends CrudRepository<Thumbnail, Integer> {

    @Query("select c from Thumbnail c where c.feedback.lineId =:lineId")
    List<Thumbnail> findThumbnailByFeedback_lineId(@Param("lineId") String lineId);

    void deleteThumbnailsByFeedback(Integer feedbackId);
}
