package de.dta.feed.feedback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "V1_THUMBNAIL")
public class Thumbnail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 1000)
    private String url;
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feedback_ref", nullable = false)
    @JsonIgnore
    private Feedback feedback;

    @Override
    public String toString() {
        return "Thumbnail{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", feedback=" + feedback +
                '}';
    }
}
