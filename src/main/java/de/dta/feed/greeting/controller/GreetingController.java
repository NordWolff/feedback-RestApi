package de.dta.feed.greeting.controller;

import de.dta.feed.feedback.model.Feedback;
import de.dta.feed.feedback.service.FeedbackService;
import de.dta.feed.greeting.service.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class GreetingController {

    private final GreetingService greetingService;
    private final FeedbackService feedbackService;


    public GreetingController(GreetingService greetingService, FeedbackService feedbackService) {
        this.greetingService = greetingService;
        this.feedbackService = feedbackService;
    }

   /* @GetMapping("/greeting")
    public String getGreeting(
            final Principal principal)
    {
        return String.format(
                "Hello, %s.",
                Optional.ofNullable(principal)
                        .map(Principal::getName)
                        .orElse("Anonymous"));
    }*/

    @GetMapping
    public ModelAndView getFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getFeedbacks();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("feedbacks",feedbacks);
        return modelAndView;
    }

    // curl -u thomas:MAxtor10 http://localhost:8080/greeting
    @GetMapping(value = "/greeting")
    public ModelAndView getGreeting(final Principal principal) {
        String greeting = String.format(
                "Hallo, %s.",
                Optional.ofNullable(principal)
                        .map(Principal::getName)
                        .orElse("Gast"));
        ModelAndView modelAndView = new ModelAndView("greeting");
        modelAndView.addObject("greeting", greeting);
        return modelAndView;

    }

    @GetMapping("/adminGreeting")
    public String getAdminGreeting() {
        return greetingService
                .adminGreeting();
    }

    @GetMapping("/superAdminGreeting")
    public String getSuperAdminGreeting() {
        return greetingService
                .superAdminGreeting();
    }

    @GetMapping("/greetings")
    public List<String> getGreetings() {
        return greetingService
                .greetings();
    }
}
