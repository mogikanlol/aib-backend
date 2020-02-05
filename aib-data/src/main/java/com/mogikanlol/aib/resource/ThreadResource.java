package com.mogikanlol.aib.resource;

import com.mogikanlol.aib.domain.Thread;
import com.mogikanlol.aib.dto.ThreadDto;
import com.mogikanlol.aib.service.ThreadService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/threads")
public class ThreadResource {

    private final ThreadService threadService;
    private final MapperFacade mapperFacade;

    @GetMapping("/{id}")
    public ThreadDto getById(@PathVariable("id") Long id) {
        Thread thread = threadService.getById(id);
        return mapperFacade.map(thread, ThreadDto.class);
    }

}
