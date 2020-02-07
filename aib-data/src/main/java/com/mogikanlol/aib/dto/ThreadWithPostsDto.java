package com.mogikanlol.aib.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ThreadWithPostsDto extends ThreadDto {

    private List<PostDto> posts;
}
