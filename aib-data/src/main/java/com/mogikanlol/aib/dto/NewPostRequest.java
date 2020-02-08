package com.mogikanlol.aib.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class NewPostRequest {

    @NotNull
    private Long threadId;

    @NotEmpty
    private String content;
}
