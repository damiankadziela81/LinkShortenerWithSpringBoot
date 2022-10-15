package com.example.linkshortener.link;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LinkRepository extends CrudRepository<LinkEntity, String> {
}
