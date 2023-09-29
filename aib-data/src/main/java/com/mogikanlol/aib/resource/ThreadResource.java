package com.mogikanlol.aib.resource;

import com.mogikanlol.aib.domain.Thread;
import com.mogikanlol.aib.dto.NewThreadRequest;
import com.mogikanlol.aib.dto.ThreadDto;
import com.mogikanlol.aib.dto.ThreadWithPostsDto;
import com.mogikanlol.aib.mapper.ThreadMapper;
import com.mogikanlol.aib.service.ThreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/threads")
public class ThreadResource {

    private final ThreadService threadService;
    private final ThreadMapper threadMapper;

    @PostMapping
    public ThreadDto create(@RequestPart("thread") @Valid NewThreadRequest request,
                            @RequestPart(name = "image", required = false) MultipartFile image) {
        Thread thread = threadService.create(request, image);
        return threadMapper.map(thread);
    }

    @GetMapping("/{id}")
    public ThreadWithPostsDto getById(@PathVariable("id") Long id) {
        Thread thread = threadService.getById(id);
        return threadMapper.mapWithPosts(thread);
    }

}
