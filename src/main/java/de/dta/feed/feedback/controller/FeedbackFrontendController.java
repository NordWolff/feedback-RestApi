package de.dta.feed.feedback.controller;

import de.dta.feed.feedback.dto.ThumbnailCreationDto;
import de.dta.feed.feedback.model.Thumbnail;
import de.dta.feed.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FeedbackFrontendController {

    @Autowired
    private FeedbackService feedbackService;

    // http://localhost:8080/api/feedback/create
    @GetMapping("/create")
    public String showForm(Model model) {
        ThumbnailCreationDto thumbnailForm = new ThumbnailCreationDto();

        for (int i = 1; i <= 3; i++) {
            thumbnailForm.addThumbnail(new Thumbnail());
        }
        model.addAttribute("thumbnail", thumbnailForm);
        return "add-thumbnail";
    }

    @PostMapping("/save")
    public String saveBooks(@ModelAttribute ThumbnailCreationDto form, Model model) {
        //feedbackService.saveAll(form.getThumbnails());
        model.addAttribute("thumbnails", feedbackService.getFeedbacks());
        return "redirect:/add-thumbnail";
    }
}
