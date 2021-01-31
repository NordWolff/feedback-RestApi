package de.dta.feed.V01.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "V1_THUMBNAIL")
public class Thumbnail {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull(message = "Id kann nicht null sein")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 1000)
    private String url;

    private String title;

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

}
