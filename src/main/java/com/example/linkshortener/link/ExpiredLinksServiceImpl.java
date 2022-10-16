package com.example.linkshortener.link;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
class ExpiredLinksServiceImpl implements ExpiredLinksService{

    private final LinkRepository linkRepository;

    @Override
    public void removeExpiredLinks(final LocalDate currentDate) {
        final List<LinkEntity> expiredLinks = linkRepository.findLinksBeforeDate(currentDate);
        linkRepository.deleteAll(expiredLinks);
        log.info((long) expiredLinks.size() + " items with time expiration before " + currentDate + " has been deleted");
    }
}
