package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.Exception.CustomValidationException;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice // 모든 Exception 다 낚아챔!!
public class ControllerExceptionHandler {
    @ExceptionHandler(CustomValidationException.class)
    public CMRespDto<?> validationException(CustomValidationException e){
        return new CMRespDto(-1,e.getMessage(), e.getErrorMap());
    }

}
