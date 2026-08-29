package dev.ioana.apimovies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ioana.apimovies.entity.GenreEntity;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    List<GenreEntity> findByNameIgnoreCase(String name);
}
