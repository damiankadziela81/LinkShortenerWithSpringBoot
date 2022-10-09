package com.example.linkshortener.controller;

import com.example.linkshortener.dto.LinkDto;
import com.example.linkshortener.link.LinkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/links")
class LinkManageController {

    private final LinkService linkService;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    LinkDto createLink(@RequestBody CreateLinkDto link) {
        return linkService.createLink(link.toDto());
    }
}
