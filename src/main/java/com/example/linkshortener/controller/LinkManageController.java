package com.example.linkshortener.controller;

import com.example.linkshortener.link.api.LinkDto;
import com.example.linkshortener.link.api.LinkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/links")
class LinkManageController {

    private final LinkService linkService;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    LinkDto createLink(@Valid @RequestBody CreateLinkDto link) {
        return linkService.createLink(link.toDto());
    }
}
