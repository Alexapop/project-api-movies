package dev.ioana.apimovies.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "years")
public class YearEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_year")
    private Long id;

    @Column(name = "release_year")
    private Integer releaseYear;

    public YearEntity() {

    }

    public YearEntity(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Long getId() {
        return id;

    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

}
