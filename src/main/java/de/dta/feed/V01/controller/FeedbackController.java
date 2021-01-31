package de.dta.feed.V01.controller;

import de.dta.feed.V01.mapper.FeedbackMapper;
import de.dta.feed.V01.model.Feedback;
import de.dta.feed.V01.service.FeedbackService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @ApiOperation(
            value = "Abrufen aller Feedbacks",
            produces = "application/json",
            response = Feedback.class
    )
    @GetMapping
    public ResponseEntity getFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getFeedbacks();

        return new ResponseEntity(feedbacks, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Abrufen eines Feedback mit der ID",
            produces = "application/json",
            response = Feedback.class
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity FeedbackById(@PathVariable Integer id) {
        Optional<Feedback> feedbackById = feedbackService.getFeedbackById(id);
        return new ResponseEntity(feedbackById, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Abrufen aller Feedbacks mit der LineId",
            produces = "application/json",
            response = Feedback.class
    )
    @GetMapping(value = "/{lineId}")
    public ResponseEntity FeedbackAllByLineId(@PathVariable String lineId) {
        List<Feedback> feedbackAllByLineId = feedbackService.getFeedbackAllByLineId(lineId);
        return new ResponseEntity(feedbackAllByLineId, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Abrufen aller Feedbacks mit dem Username",
            produces = "application/json",
            response = Feedback.class
    )
    @GetMapping(value = "/{username}")
    public ResponseEntity FeedbackByUsername(@PathVariable String username){
        List<Feedback> feedbackByUsername = feedbackService.getFeedbackByUsername(username);
        return new ResponseEntity(feedbackByUsername, HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity addOrChangeFeedback(@Valid @RequestBody Feedback feedback) {
        //FeedbackMapper.MAPPER.toFeedback(feedback);
        feedbackService.setFeedback(feedback);
        return new ResponseEntity(HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteFeedback(@PathVariable Integer id) {
        try {
            feedbackService.deleteCustomer(id);
        } catch(EmptyResultDataAccessException emptyResultDataAccessException) {
            //When already deleted, it's ok!
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
