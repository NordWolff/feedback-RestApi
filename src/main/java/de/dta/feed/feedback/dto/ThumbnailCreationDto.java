package de.dta.feed.feedback.dto;

import de.dta.feed.feedback.model.Thumbnail;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ThumbnailCreationDto {

    private List<Thumbnail> thumbnails = new ArrayList<>();

    // default and parameterized constructor

    public void addThumbnail(Thumbnail thumbnail) {
        this.thumbnails.add(thumbnail);
    }
}
