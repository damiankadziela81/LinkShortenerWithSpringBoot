package com.example.linkshortener.link;

import com.example.linkshortener.dto.LinkDto;

public interface LinkService {
    LinkDto createLink(LinkDto toDto);


    String obtainLinkAndIncreaseVisits(String id);
}
