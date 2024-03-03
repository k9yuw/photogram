package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.Exception.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice // 모든 Exception 다 낚아챔!!
public class ControllerExceptionHandler {
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e){
        // 클라이언트한테 응답할 때는 Script가 좋음.
        // Ajax 통신이나 Android 통신을 할 때는 CMResponseDto가 좋음
        return Script.back(e.getErrorMap().toString());
    }

}
