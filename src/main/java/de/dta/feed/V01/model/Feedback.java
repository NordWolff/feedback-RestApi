package de.dta.feed.V01.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "V1_FEEDBACK")
public class Feedback {

    @Id
    @NotNull(message = "Id kann nicht null sein")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Username kann nicht leer sein")
    private String username;

    @Column(name = "LINEID")
    @NotNull(message = "LineId kann nicht null sein")
    private String lineId;

    @Lob
    @Column(length = 1000)
    private String freeText;

    @NotNull(message = "incidentSeverity kann nicht null sein")
    private String incidentSeverity;

    @NotNull(message = "resolvedCi kann nicht leer sein")
    private String resolvedCi;

    @NotNull(message = "incidentType kann nicht leer sein")
    private String incidentType;

    @NotNull(message = "attr kann nicht leer sein")
    private String attr;

    @NotNull(message = "KnownError kann nicht leer sein")
    private String knownError;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date published;

    private Date edit;

    //private Status status; //: DoneStatus;

    @NotNull(message = "Author kann nicht leer sein")
    private String author;

    private Integer rating;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Thumbnail> thumbnails = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getLineId() {
        return lineId;
    }

    public String getFreeText() {
        return freeText;
    }

    public String getIncidentSeverity() {
        return incidentSeverity;
    }

    public String getResolvedCi() {
        return resolvedCi;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public String getAttr() {
        return attr;
    }

    public String getKnownError() {
        return knownError;
    }

    public Date getPublished() {
        return published;
    }

    public Date getEdit() {
        return edit;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getRating() {
        return rating;
    }

    public List<Thumbnail> getThumbnails() {
        return thumbnails;
    }
}
