package com.example.linkshortener.link;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
class ExpiredLinksServiceImpl implements ExpiredLinksService{

    private final LinkRepository linkRepository;

    @Override
    public void removeExpiredLinks(final LocalDate currentDate) {
        final List<LinkEntity> expiredLinks = linkRepository.findLinksBeforeDate(currentDate);
        linkRepository.deleteAll(expiredLinks);
        //todo dodac logger
    }
}
