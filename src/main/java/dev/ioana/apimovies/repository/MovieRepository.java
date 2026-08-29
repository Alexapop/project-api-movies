package dev.ioana.apimovies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ioana.apimovies.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    List<MovieEntity> findByTitleIgnoreCase(String title);

    List<MovieEntity> findDistinctByGenres_NameIgnoreCase(String genres);

}
