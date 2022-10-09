package com.example.linkshortener.controller;

import com.example.linkshortener.dto.LinkDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

record CreateLinkDto(
        @Schema(description = "Identifier/alias to link. Used for redirection", example = "link-alias", required = true)
        String id,
        @Schema(description = "Email address of shortened link recipient", example = "name@org.com", required = true)
        String email,
        @Schema(description = "URL to be shortened", example = "long_url_name.com", required = true)
        String targetUrl,
        @Schema(description = "Date at which the shortened link will expire", example = "2027-12-03", required = false)
        LocalDate expirationDate
) {

    LinkDto toDto() {
        return new LinkDto(
                id,
                email,
                targetUrl,
                expirationDate,
                0
        );
    }

}
