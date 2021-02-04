package de.dta.feed.V01.controller;

import de.dta.feed.V01.model.Feedback;
import de.dta.feed.V01.model.HelloSpring;
import de.dta.feed.V01.service.FeedbackService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins="http://localhost:4200")  /**Ressource kein Header behoben*/
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/welcome")
    public HelloSpring sayHello() {
        return new HelloSpring("Spring is Online");
    }


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
    @GetMapping(value = "/id/{id}")
    public ResponseEntity FeedbackById(@PathVariable Integer id) {
        Optional<Feedback> feedbackById = feedbackService.getFeedbackById(id);
        return new ResponseEntity(feedbackById, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Abrufen aller Feedbacks mit der LineId",
            produces = "application/json",
            response = Feedback.class
    )
    @GetMapping(value = "/lineId/{lineId}")
    public ResponseEntity FeedbackAllByLineId(@PathVariable String lineId) {
        List<Feedback> feedbackAllByLineId = feedbackService.getFeedbackAllByLineId(lineId);
        return new ResponseEntity(feedbackAllByLineId, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Abrufen aller Feedbacks mit dem Username",
            produces = "application/json",
            response = Feedback.class
    )
    @GetMapping(value = "/username/{username}")
    public ResponseEntity FeedbackByUsername(@PathVariable String username){
        List<Feedback> feedbackByUsername = feedbackService.getFeedbackByUsername(username);
        return new ResponseEntity(feedbackByUsername, HttpStatus.OK);
    }

    @PutMapping(value = "/edit")
    public ResponseEntity changeFeedback(@Valid @RequestBody Feedback feedback) {
        //FeedbackMapper.MAPPER.toFeedback(feedback);
        feedbackService.setFeedback(feedback);
        return new ResponseEntity(HttpStatus.OK);

    }

    @PostMapping(value = "/add")
    public ResponseEntity addFeedback(@Valid @RequestBody Feedback feedback) {
        //FeedbackMapper.MAPPER.toFeedback(feedback);
        feedbackService.setFeedback(feedback);
        return new ResponseEntity(HttpStatus.OK);

    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity deleteFeedback(@PathVariable Integer id) {
        try {
            feedbackService.deleteCustomer(id);
        } catch(EmptyResultDataAccessException emptyResultDataAccessException) {
            //When already deleted, it's ok!
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search/{searchTerm}")
    public ResponseEntity getSearchAllWithLineId(@PathVariable String searchTerm) {
         feedbackService.searchAllFeedbackByLineId(searchTerm);
         return new ResponseEntity(HttpStatus.OK);
    }
}
