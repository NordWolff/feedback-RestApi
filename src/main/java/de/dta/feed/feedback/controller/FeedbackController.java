package de.dta.feed.feedback.controller;

import de.dta.feed.feedback.model.Feedback;
import de.dta.feed.feedback.model.HelloSpring;
import de.dta.feed.feedback.model.Thumbnail;
import de.dta.feed.feedback.service.FeedbackService;
import de.dta.feed.feedback.service.ThumbnailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins="http://localhost:4200")  /**Ressource kein Header behoben*/
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private ThumbnailService thumbnailService;

    @GetMapping("/welcome")
    public HelloSpring sayHello() {
        return new HelloSpring("Spring is Online");
    }

    @ApiOperation(
            value = "Abrufen aller Feedbacks",
            produces = "application/json",
            response = Feedback.class
    )
    @GetMapping(path = "/reports")
    public ResponseEntity getFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getFeedbacks();
        return new ResponseEntity(feedbacks, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Abrufen eines Feedback mit der ID",
            produces = "application/json",
            response = Feedback.class
    )
    @GetMapping(value = "/id/{feedbackId}")
    public ResponseEntity FeedbackById(@PathVariable Integer feedbackId) {
        Optional<Feedback> feedbackById = feedbackService.getFeedbackById(feedbackId);
        return new ResponseEntity(feedbackById, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Abrufen aller Feedbacks mit der LineId",
            produces = "application/json",
            response = Feedback.class
    )
    @GetMapping(value = "/report/{lineId}")
    public ResponseEntity FeedbackAllByLineId(@PathVariable String lineId) {
        Feedback findbyLineId = feedbackService.findByLineId(lineId);
        return new ResponseEntity(findbyLineId, HttpStatus.OK);
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
    public ResponseEntity editFeedback(@Valid @RequestBody Feedback feedback) {
        //FeedbackMapper.MAPPER.toFeedback(feedback);
        feedbackService.update(feedback);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity addFeedback(@RequestBody Feedback feedback) {
        //FeedbackMapper.MAPPER.toFeedback(feedback);
        Feedback saveFeedback = feedbackService.save(feedback);
        saveFeedback.getThumbnails().forEach(thumbnail -> {thumbnail.setFeedback(saveFeedback);thumbnailService.save(thumbnail);});
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove/{feedbackId}")
    public ResponseEntity deleteFeedback(@PathVariable Integer feedbackId) {
        try {
            feedbackService.deleteById(feedbackId);
            return new ResponseEntity(HttpStatus.OK);
        } catch(EmptyResultDataAccessException emptyResultDataAccessException) {
            //When already deleted, it's ok!
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    // http://localhost:8080/api/feedback/remove/DEU.DTAG.91KEF4
    @DeleteMapping(value = "/delete/{lineId}")
    public ResponseEntity deleteFeedback(@PathVariable String lineId) {
        try {
            feedbackService.deleteByLineId(lineId);
            return new ResponseEntity(HttpStatus.OK);
        } catch(EmptyResultDataAccessException emptyResultDataAccessException) {
            //When already deleted, it's ok!
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{searchTerm}")
    public ResponseEntity getSearchAllWithLineId(@PathVariable String searchTerm) {
         List<Feedback> listFeedbacks = feedbackService.searchAllFeedbackByLineId(searchTerm);
         return new ResponseEntity(listFeedbacks,HttpStatus.OK);
    }

}
