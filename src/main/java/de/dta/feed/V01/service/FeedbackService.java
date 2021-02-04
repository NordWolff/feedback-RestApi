package de.dta.feed.V01.service;

import de.dta.feed.V01.model.Feedback;
import de.dta.feed.V01.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public void setFeedback(Feedback feedback) { feedbackRepository.save(feedback);}

    @Transactional
    public void deleteCustomer(Integer id) { feedbackRepository.deleteById(id);}

    public List<Feedback> getFeedbackByUsername(String username) {

        List<Feedback> allByUsername = feedbackRepository.findAllByUsername(username);
        return allByUsername;
    }

    public List<Feedback> searchAllFeedbackByLineId(String searchTerm) {
        List<Feedback> allByLineId = feedbackRepository.searchAllFeedbackByLineId(searchTerm);
        return allByLineId;

    }
}
