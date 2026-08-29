package dev.ioana.apimovies.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")

public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_movie")
    private Long id;

    @Column(name = "title")
    private String title;

    // Movies-year relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_year")
    private YearEntity year;

    // movies-genre relationship

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movies_genres", joinColumns = @JoinColumn(name = "id_movie"), inverseJoinColumns = @JoinColumn(name = "id_genre"))
    private Set<GenreEntity> genres = new HashSet<>();

    // movies-actors relationship

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movies_actors", joinColumns = @JoinColumn(name = "id_movie"), inverseJoinColumns = @JoinColumn(name = "id_actor"))
    private Set<ActorEntity> actors = new HashSet<>();

    public MovieEntity() {

    }

    public MovieEntity(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
