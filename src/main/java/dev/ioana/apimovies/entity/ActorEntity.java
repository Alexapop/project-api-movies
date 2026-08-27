package dev.ioana.apimovies.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
