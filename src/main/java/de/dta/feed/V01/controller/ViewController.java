package de.dta.feed.V01.controller;

import de.dta.feed.V01.model.Feedback;
import de.dta.feed.V01.service.FeedbackService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ViewController {
    @Autowired
    private FeedbackService feedbackService;

    @ApiOperation(
            value = "Abrufen aller Feedbacks",
            produces = "application/json",
            response = Feedback.class
    )
    @GetMapping
    public ModelAndView getFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getFeedbacks();
        ModelAndView modelAndView = new ModelAndView("feedbacks");
        modelAndView.addObject("feedbacks",feedbacks);
        return modelAndView;
    }
}
