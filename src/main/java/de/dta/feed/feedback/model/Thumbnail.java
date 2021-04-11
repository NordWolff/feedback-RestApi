package de.dta.feed.feedback.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "V1_THUMBNAIL")
public class Thumbnail {

    //@ManyToOne
    //@JoinColumn(name = "feedback_id")
    //private Feedback feedback;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 1000)
    private String url;

    @Column
    private String title;

}
