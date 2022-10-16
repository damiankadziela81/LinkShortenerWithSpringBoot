package com.example.linkshortener.link;

import com.example.linkshortener.link.api.LinkDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
interface LinkRepository extends CrudRepository<LinkEntity, String> {
    @Query("SELECT e FROM LinkEntity e WHERE e.expirationDate < ?1")
    List<LinkEntity> findLinksBeforeDate(LocalDate currentDate);

    List<LinkEntity> findAllByVisitsGreaterThan(Integer visits);

    LinkDto findAllById(String id);

}
