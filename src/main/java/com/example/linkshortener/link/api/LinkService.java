package com.example.linkshortener.link.api;

import java.util.List;

public interface LinkService {
    LinkDto createLink(LinkDto toDto);


    String obtainLinkAndIncreaseVisits(String id);

    List<LinkDto> getLinksForVisitsHigherThan(Integer visits);

    LinkDto getLinkById(String id);

}
