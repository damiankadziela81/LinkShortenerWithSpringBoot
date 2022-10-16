package com.example.linkshortener.link;

import com.example.linkshortener.link.api.LinkDto;
import com.example.linkshortener.link.api.LinkService;
import com.example.linkshortener.link.api.exceptions.DuplicateLinkException;
import com.example.linkshortener.link.api.exceptions.LinkNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public List<LinkDto> getLinksForVisitsHigherThan(final Integer visits) {
        return linkRepository.findAllByVisitsGreaterThan(visits)
                .stream()
                .map(linkEntity -> linkEntity.toDto())
                .collect(Collectors.toList());
    }
}
