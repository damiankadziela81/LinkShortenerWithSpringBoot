package com.example.linkshortener.link.api;

import java.util.List;

public interface LinkService {
    LinkDto createLink(LinkDto toDto);


    String obtainLinkAndIncreaseVisits(String id);

    List<LinkDto> getLinksForVisitsHigherThan(Integer visits);

    LinkDto getLinkById(String id);

    /**
     * @param id this is alias
     * @param email used for security
     * @return returns {@code true} when link was found and deleted
     */
    boolean deleteLink(String id, String email);

}
