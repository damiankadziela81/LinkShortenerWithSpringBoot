package com.example.linkshortener.link.api;

import com.example.linkshortener.link.api.LinkDto;

public interface LinkService {
    LinkDto createLink(LinkDto toDto);


    String obtainLinkAndIncreaseVisits(String id);
}
