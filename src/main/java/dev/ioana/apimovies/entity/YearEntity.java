package dev.ioana.apimovies.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

   @OneToMany(mappedBy = "year", fetch = FetchType.LAZY)
    private Set<MovieEntity> movies = new HashSet<>();




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
