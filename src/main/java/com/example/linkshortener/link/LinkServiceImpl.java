package com.example.linkshortener.link;

import com.example.linkshortener.dto.LinkDto;
import com.example.linkshortener.link.exceptions.DuplicateLinkException;
import com.example.linkshortener.link.exceptions.LinkNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;


@Service
class LinkServiceImpl implements LinkService {

    private final HashMap<String , LinkDto> linkRepository;

    LinkServiceImpl() {
        linkRepository = new HashMap<>();
    }

    @Override
    public LinkDto createLink(final LinkDto toDto) {
        if (linkRepository.containsKey(toDto.id()))
            throw new DuplicateLinkException(toDto.id());
        linkRepository.put(toDto.id(), toDto);
        return toDto;
    }

    @Override
    public String obtainLink(final String id) {

        final LinkDto linkDto = linkRepository.get(id);
        if(linkDto==null){
            throw new LinkNotFoundException(id);
        }
        return linkDto.targetUrl();
    }
}
