package de.dta.feed.feedback.service;

import de.dta.feed.feedback.model.Feedback;
import de.dta.feed.feedback.model.Thumbnail;
import de.dta.feed.feedback.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    public List<Feedback> getFeedbacks() {
        return (List<Feedback>) feedbackRepository.findAll();
    }

    public Optional<Feedback>  getFeedbackById(Integer id) {
        Optional<Feedback> byId = feedbackRepository.findById(id);
        return byId;
    }

    public List<Feedback> getFeedbackAllByLineId(String lineId) {
        List<Feedback> allByLineId = feedbackRepository.findAllByLineId(lineId);
        return allByLineId;
    }


    @Transactional
    public void save(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    public void deleteById(Integer id) {
        feedbackRepository.deleteById(id);
    }

    public void deleteByLineId(String lineId) {
        feedbackRepository.deleteByLineId(lineId);
    }

    public List<Feedback> getFeedbackByUsername(String username) {
        List<Feedback> allByUsername = feedbackRepository.findAllByUsername(username);
        return allByUsername;
    }

    public List<Feedback> searchAllFeedbackByLineId(String searchTerm) {
        List<Feedback> allByLineId = feedbackRepository.searchAllFeedbackByLineId(searchTerm);
        return allByLineId;
    }

    public void update(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    public Feedback findByLineId(String lineId) {
      return  feedbackRepository.findByLineId(lineId);
    }
}
