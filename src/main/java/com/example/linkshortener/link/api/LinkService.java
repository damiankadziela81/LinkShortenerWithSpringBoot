package com.example.linkshortener.link.api;

import com.example.linkshortener.link.api.LinkDto;

import java.util.List;

public interface LinkService {
    LinkDto createLink(LinkDto toDto);


    String obtainLinkAndIncreaseVisits(String id);

    List<LinkDto> getLinksForVisitsHigherThan(Integer visits);
}
