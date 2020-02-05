package com.mogikanlol.aib.service;

import com.mogikanlol.aib.domain.Thread;
import com.mogikanlol.aib.repository.ThreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThreadService {

    private final ThreadRepository repository;

    public Thread getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ThreadNotFoundException(id));
    }
}
