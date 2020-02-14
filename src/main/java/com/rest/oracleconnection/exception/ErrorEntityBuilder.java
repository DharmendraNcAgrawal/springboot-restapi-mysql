package com.rest.oracleconnection.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
@Scope("singleton")
@PropertySource("classpath:messages.properties")
public class ErrorEntityBuilder {

    @Value("${EMPLOYEE_NOT_FOUND_MESSAGE}")
    private String employeeNotFoundMessage;
    @Value("${INTERNAL_SERVER_ERROR_MESSAGE}")
    private   String internalServerErrorMessage;

    private HashMap<String, ErrorEntity> codeToErrorMap;

    private ErrorEntityBuilder() {
    }

    @PostConstruct
    public void getErrorCodes() {
        codeToErrorMap = new HashMap<>();
        codeToErrorMap.put(ErrorCodes.RESOURCE_NOT_FOUND, ErrorEntity.builder().errorCode(Integer.valueOf(ErrorCodes.RESOURCE_NOT_FOUND)).errorMessage(employeeNotFoundMessage).build());
        codeToErrorMap.put(ErrorCodes.INTERNAL_SERVER_ERROR, ErrorEntity.builder().errorCode(Integer.valueOf(ErrorCodes.INTERNAL_SERVER_ERROR)).errorMessage(internalServerErrorMessage).build());

    }

    public ErrorEntity build(String code) {
        return codeToErrorMap.get(code);
    }
}
