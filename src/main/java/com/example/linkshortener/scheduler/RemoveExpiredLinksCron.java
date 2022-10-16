package com.example.linkshortener.scheduler;

import com.example.linkshortener.link.ExpiredLinksService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor //lombok
@Component //Spring
@Slf4j //logger
class RemoveExpiredLinksCron {

    private final ExpiredLinksService expiredLinksService;

    @Scheduled(cron = "${expired.links.cron}")

    void removeExpiredLinks(){
        log.info("Expired links cron job started.");
        expiredLinksService.removeExpiredLinks(LocalDate.now());
        log.info("Expired links cron job ended.");
    }
}
