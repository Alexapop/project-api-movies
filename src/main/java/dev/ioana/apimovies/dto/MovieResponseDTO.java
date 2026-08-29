package dev.ioana.apimovies.dto;

import java.util.Set;

public record MovieResponseDTO(
        Long id,
        String title,
        Integer releaseYear,
        Set<String> genres,
        Set<String> actors

) {

}
