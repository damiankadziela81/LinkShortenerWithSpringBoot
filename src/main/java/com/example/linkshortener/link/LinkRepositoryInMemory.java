package com.example.linkshortener.link;

import org.apache.commons.lang3.NotImplementedException;

import java.util.Map;
import java.util.Optional;

class LinkRepositoryInMemory implements LinkRepository{

    final Map<String, LinkEntity> entityMap;

    LinkRepositoryInMemory(final Map<String, LinkEntity> entityMap) {
        this.entityMap = entityMap;
    }

    @Override
    public <S extends LinkEntity> S save(final S entity) {
        return (S) entityMap.put(entity.getId(), entity);
    }

    @Override
    public <S extends LinkEntity> Iterable<S> saveAll(final Iterable<S> entities) {
        throw new NotImplementedException();
    }

    @Override
    public Optional<LinkEntity> findById(final String s) {
        return Optional.ofNullable(entityMap.get(s));
    }

    @Override
    public boolean existsById(final String s) {
        throw new NotImplementedException();
    }

    @Override
    public Iterable<LinkEntity> findAll() {
        throw new NotImplementedException();
    }

    @Override
    public Iterable<LinkEntity> findAllById(final Iterable<String> strings) {
        throw new NotImplementedException();
    }

    @Override
    public long count() {
        throw new NotImplementedException();
    }

    @Override
    public void deleteById(final String s) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(final LinkEntity entity) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteAllById(final Iterable<? extends String> strings) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteAll(final Iterable<? extends LinkEntity> entities) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteAll() {
        throw new NotImplementedException();
    }
}
