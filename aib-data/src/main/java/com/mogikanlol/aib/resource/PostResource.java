package com.mogikanlol.aib.resource;

import com.mogikanlol.aib.domain.Post;
import com.mogikanlol.aib.dto.NewPostRequest;
import com.mogikanlol.aib.dto.PostDto;
import com.mogikanlol.aib.service.PostService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostResource {

    private final PostService postService;
    private final MapperFacade mapperFacade;

    @PostMapping
    public PostDto create(@RequestBody @Valid NewPostRequest request) {
        Post post = postService.create(request);
        return mapperFacade.map(post, PostDto.class);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id) {
        return postService.delete(id);
    }
}
