package com.example.linkshortener.link;

import com.example.linkshortener.link.api.LinkService;
import com.example.linkshortener.link.api.exceptions.LinkNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class LinkServiceDeleteTest {

    private LinkService linkService;
    LinkRepository linkRepository;

    @BeforeEach
    void setUp() {
        linkRepository = mock(LinkRepository.class);
        linkService = new LinkServiceImpl(linkRepository);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            id, email, id, email
            id, email, id, Email
            id, email, ID, email     
            """
    )
    void shouldDeleteLinkWhenFound(String entityId, String entityEmail, String deleteLinkId, String deleteLinkEmail) {

        //given
        LinkEntity createdLink = LinkEntity
                .builder()
                .id(entityId)
                .email(entityEmail)
                .build();
        given(linkRepository.findById(entityId)).willReturn(Optional.of(createdLink));

        //when
        final boolean isLinkDeleted = linkService.deleteLink(deleteLinkId, deleteLinkEmail);

        //then
        assertTrue(isLinkDeleted, "Link should be deleted.");
        then(linkRepository).should().deleteById(entityId);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            id, email, id, email1
            id, email, id,      
               
            """
    )
    void shouldNotDeleteLink(String entityId, String entityEmail, String deleteLinkId, String deleteLinkEmail) {

        //given
        LinkEntity createdLink = LinkEntity
                .builder()
                .id(entityId)
                .email(entityEmail)
                .build();
        given(linkRepository.findById(entityId)).willReturn(Optional.of(createdLink));

        //when
        final boolean isLinkDeleted = linkService.deleteLink(deleteLinkId, deleteLinkEmail);

        //then
        assertFalse(isLinkDeleted, "Link shouldn't be deleted.");
        then(linkRepository).should().findById(any());
        then(linkRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void shouldNotDeleteLinkWhenIsNull() {

        //given
        given(linkRepository.findById("id")).willReturn(Optional.empty());

        //when

        //then
        assertThrows(LinkNotFoundException.class, () -> linkService.deleteLink("id",null));

    }
}