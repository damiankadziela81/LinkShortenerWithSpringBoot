package com.example.linkshortener.link;

import java.time.LocalDate;

public interface ExpiredLinksService{
    void removeExpiredLinks(LocalDate now);
}
