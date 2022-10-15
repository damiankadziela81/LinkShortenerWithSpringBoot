package com.example.linkshortener.link.api.exceptions;

public class DuplicateLinkException extends RuntimeException {

    public DuplicateLinkException(final String id ) {
        super("Link " + id + " already exists.");
    }
}
