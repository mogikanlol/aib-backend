package com.mogikanlol.aib.service;

import com.mogikanlol.aib.domain.Post;
import com.mogikanlol.aib.domain.Thread;
import com.mogikanlol.aib.dto.NewPostRequest;
import com.mogikanlol.aib.repository.PostRepository;
import com.mogikanlol.aib.repository.ThreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final ThreadRepository threadRepository;
    private final PostRepository postRepository;

    public Post create(NewPostRequest request) {
        Thread thread = threadRepository.findById(request.getThreadId())
                .orElseThrow(() -> new ThreadNotFoundException(request.getThreadId()));

        Post post = new Post()
                .setThread(thread)
                .setContent(request.getContent());

        return postRepository.save(post);
    }

    public Long delete(Long id) {
        postRepository.deleteById(id);
        return id;
    }
}
