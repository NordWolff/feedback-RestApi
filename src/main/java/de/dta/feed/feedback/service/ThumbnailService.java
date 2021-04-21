package de.dta.feed.feedback.service;

import de.dta.feed.feedback.model.Feedback;
import de.dta.feed.feedback.model.Thumbnail;
import de.dta.feed.feedback.repository.ThumbnailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ThumbnailService {

    @Autowired
    ThumbnailRepository thumbnailRepository;


    @Transactional
    public void saveAll(List<Thumbnail> thumbnails) {
        thumbnailRepository.saveAll(thumbnails);
    }

    @Transactional
    public void save(Thumbnail thumbnail) {
        thumbnailRepository.save(thumbnail);
    }

    public void deletebyId(Integer id) {
        thumbnailRepository.deleteById(id);
    }
}
