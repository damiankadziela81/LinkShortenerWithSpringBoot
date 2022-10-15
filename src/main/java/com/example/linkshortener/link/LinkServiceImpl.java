package com.example.linkshortener.link;

import com.example.linkshortener.dto.LinkDto;
import com.example.linkshortener.link.api.exceptions.DuplicateLinkException;
import com.example.linkshortener.link.api.exceptions.LinkNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;


    @Override
    public LinkDto createLink(final LinkDto toDto) {
        if (linkRepository.findById(toDto.id()).isPresent())
            throw new DuplicateLinkException(toDto.id());
        linkRepository.save(LinkEntity.fromDto(toDto));
        return toDto;
    }

    @Override
    public String obtainLink(final String id) {
        final LinkEntity linkEntity = linkRepository.findById(id)
                .orElseThrow(() -> new LinkNotFoundException(id));
        return linkEntity.getTargetUrl();
    }
}
