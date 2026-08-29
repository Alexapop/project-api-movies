package dev.ioana.apimovies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ioana.apimovies.entity.YearEntity;

public interface YearRepository extends JpaRepository<YearEntity, Long> {

    List<YearEntity> findByReleaseYear(Integer releaseYear);
}
