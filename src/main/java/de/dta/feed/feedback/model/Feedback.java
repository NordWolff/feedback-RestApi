package de.dta.feed.feedback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "V1_FEEDBACK")
public class Feedback {

    @Id
    @GeneratedValue
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
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date edit;
    //private Status status; //: DoneStatus;
    @NotNull(message = "Author kann nicht leer sein")
    private String author;
    private Integer rating;
    @OneToMany(mappedBy = "feedback", cascade = CascadeType.ALL)
    private List<Thumbnail> thumbnails;


    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", lineId='" + lineId + '\'' +
                ", freeText='" + freeText + '\'' +
                ", incidentSeverity='" + incidentSeverity + '\'' +
                ", resolvedCi='" + resolvedCi + '\'' +
                ", incidentType='" + incidentType + '\'' +
                ", attr='" + attr + '\'' +
                ", knownError='" + knownError + '\'' +
                ", published=" + published +
                ", edit=" + edit +
                ", author='" + author + '\'' +
                ", rating=" + rating +
                ", thumbnails=" + thumbnails +
                '}';
    }
}
