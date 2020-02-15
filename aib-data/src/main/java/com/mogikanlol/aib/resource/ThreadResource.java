package com.mogikanlol.aib.resource;

import com.mogikanlol.aib.domain.Thread;
import com.mogikanlol.aib.dto.NewThreadRequest;
import com.mogikanlol.aib.dto.ThreadDto;
import com.mogikanlol.aib.dto.ThreadWithPostsDto;
import com.mogikanlol.aib.service.ThreadService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/threads")
public class ThreadResource {

    private final ThreadService threadService;
    private final MapperFacade mapperFacade;

    @PostMapping
    public ThreadDto create(@RequestPart("thread") @Valid NewThreadRequest request,
                            @RequestPart(name = "image", required = false) MultipartFile image) {
        Thread thread = threadService.create(request, image);
        return mapperFacade.map(thread, ThreadDto.class);
    }

    @GetMapping("/{id}")
    public ThreadWithPostsDto getById(@PathVariable("id") Long id) {
        Thread thread = threadService.getById(id);
        return mapperFacade.map(thread, ThreadWithPostsDto.class);
    }

}
