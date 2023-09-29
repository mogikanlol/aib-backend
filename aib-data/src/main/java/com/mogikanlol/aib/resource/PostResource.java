package com.mogikanlol.aib.resource;

import com.mogikanlol.aib.domain.Post;
import com.mogikanlol.aib.dto.NewPostRequest;
import com.mogikanlol.aib.dto.PostDto;
import com.mogikanlol.aib.dto.PostPatchDto;
import com.mogikanlol.aib.mapper.PostMapper;
import com.mogikanlol.aib.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostResource {

    private final PostService postService;
    private final PostMapper postMapper;

    @PostMapping
    public PostDto create(@RequestBody @Valid NewPostRequest request) {
        Post post = postService.create(request);
        return postMapper.map(post);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id) {
        return postService.delete(id);
    }

    @PatchMapping("/{id}")
    public PostDto update(@PathVariable("id") Long id, @RequestBody PostPatchDto postDto) {
        Post post = postService.update(id, postDto);
        return postMapper.map(post);
    }
}
