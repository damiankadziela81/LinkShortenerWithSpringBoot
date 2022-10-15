package com.example.linkshortener.link;

import com.example.linkshortener.link.api.LinkDto;
import com.example.linkshortener.link.api.LinkService;
import com.example.linkshortener.link.api.exceptions.DuplicateLinkException;
import com.example.linkshortener.link.api.exceptions.LinkNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@AllArgsConstructor
class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;


    @Override
    @Transactional
    public LinkDto createLink(final LinkDto toDto) {
        if (linkRepository.findById(toDto.id()).isPresent())
            throw new DuplicateLinkException(toDto.id());
        linkRepository.save(LinkEntity.fromDto(toDto));
        return toDto;
    }

    @Override
    @Transactional
    public String obtainLinkAndIncreaseVisits(final String id) {
        final LinkEntity linkEntity = linkRepository.findById(id)
                .orElseThrow(() -> new LinkNotFoundException(id));
        linkEntity.setVisits(linkEntity.getVisits() + 1);
        return linkEntity.getTargetUrl();
    }
}
