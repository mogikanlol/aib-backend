package com.mogikanlol.aib.service;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long id) {
        super(String.format("Post with id = %s is not found", id));
    }
}
