package com.mogikanlol.aib.mapper;

import com.mogikanlol.aib.dto.ThreadDto;
import com.mogikanlol.aib.domain.Thread;
import com.mogikanlol.aib.dto.ThreadWithPostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ThreadMapper {

    private final PostMapper postMapper;

    public ThreadDto map(Thread entity) {
        return new ThreadDto()
                .setId(entity.getId())
                .setTitle(entity.getTitle())
                .setImageUrl("http://localhost:8081/images/" + entity.getImageName())
                .setContent(entity.getContent());
    }

    public List<ThreadDto> map(List<Thread> threads) {
        return threads.stream()
                .map(this::map)
                .toList();
    }

    public ThreadWithPostsDto mapWithPosts(Thread entity) {
        ThreadWithPostsDto dto = new ThreadWithPostsDto();
        dto.setPosts(postMapper.map(entity.getPosts()));
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setImageUrl("http://localhost:8081/images/" + entity.getImageName());
        dto.setContent(entity.getContent());

        return dto;
    }
}
