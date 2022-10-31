package com.evereats.fooder.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class ApiError {
    private Integer status;
    private String type;
    private String title;
    private String detail;
    private List<Field> fields;
}
