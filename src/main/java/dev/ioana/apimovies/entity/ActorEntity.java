package dev.ioana.apimovies.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "actors")
public class ActorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_actor")
    private Long id;

    @Column(name = "name_actor")
    private String nameActor;

    @ManyToMany(mappedBy = "actors", fetch = FetchType.LAZY)
    private Set<MovieEntity> movies = new HashSet<>();

    public ActorEntity() {

    }

    public ActorEntity(String nameActor) {
        this.nameActor = nameActor;
    }

    public Long getId() {
        return id;
    }

    public String getNameActor() {
        return nameActor;
    }
}
