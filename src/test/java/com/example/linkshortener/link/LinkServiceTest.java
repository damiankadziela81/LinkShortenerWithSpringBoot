package com.example.linkshortener.link;

import com.example.linkshortener.dto.LinkDto;
import com.example.linkshortener.link.api.exceptions.DuplicateLinkException;
import com.example.linkshortener.link.api.exceptions.LinkNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LinkServiceTest {

    private LinkService linkService;

    @BeforeEach
    void setUp() {
       linkService = new LinkServiceImpl(new LinkRepositoryInMemory(new HashMap<>()));
    }

    @Test
    void shouldSaveAndRetrieveLink() {
        //given
        final LinkDto linkDto = new LinkDto("id","email", "google.com", null, 0 );

        //when
        final LinkDto resultLink = linkService.createLink(linkDto);
        final String resultTargetUrl = linkService.obtainLink(linkDto.id());

        //then
        assertEquals(linkDto.id(), resultLink.id());
        assertEquals(linkDto.targetUrl(), resultTargetUrl);
    }

    @Test
    @DisplayName("Should throw link not found exception when there there is no link in the repository")
    void shouldThrowExceptionWhenLinkNotFoundInTheRepository() {
        //given
        String nonExistingId = "cokolwiek";

        //when then
        assertThrows(LinkNotFoundException.class, () -> linkService.obtainLink(nonExistingId));
    }

    @Test
    @DisplayName("Should throw duplicate key exception when link with same id already added")
    void ShouldThrowExceptionWhenLinkAlreadyExists() {
        //given
        LinkDto linkDto = new LinkDto("duplikat","email", "google.com", null, 0 );
        LinkDto linkDto2 = new LinkDto("duplikat","email", "google.com", null, 0 );
        linkService.createLink(linkDto);

        //when
        //then
        assertThrows(DuplicateLinkException.class, () -> linkService.createLink(linkDto2));

    }
}