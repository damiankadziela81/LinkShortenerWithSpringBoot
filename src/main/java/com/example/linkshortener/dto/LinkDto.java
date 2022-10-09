package com.example.linkshortener.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
@Builder
public record LinkDto(
        String id,
        @JsonIgnore
        String email,
        String targetUrl,
        LocalDate expirationDate,
        int visits
) {
    public String getShortenedLink() {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/s/{id}")
                .buildAndExpand(id)
                .toUriString();
    }
}
