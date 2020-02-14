package com.rest.oracleconnection.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorEntity {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected Integer errorCode;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected String errorMessage;
}
