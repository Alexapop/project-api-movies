package dev.ioana.apimovies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ioana.apimovies.entity.ActorEntity;

public interface ActorRepository extends JpaRepository<ActorEntity, Long> {

    List<ActorEntity> findByNameActorIgnoreCase(String nameActor);
}
