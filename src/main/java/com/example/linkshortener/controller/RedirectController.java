package com.example.linkshortener.controller;

import com.example.linkshortener.link.api.LinkService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@RestController
class RedirectController {

    private final LinkService linkService;


    @GetMapping(path = "/s/{id}")
    public void redirectLink(
            @PathVariable String id,
            HttpServletResponse httpServletResponse
    ) throws IOException {
        String targetUrl = linkService.obtainLinkAndIncreaseVisits(id);
        httpServletResponse.sendRedirect(targetUrl);
    }
}
