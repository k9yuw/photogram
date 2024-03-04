package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.Exception.CustomValidationApiException;
import com.cos.photogramstart.handler.Exception.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// Script, CMResponseDto 비교
// 1. 클라이언트한테 응답할 때는 Script가 좋음.
// 2. Ajax 통신을 할 때는 CMResponseDto가 좋음
// 3. Android 통신을 할 때는 CMResponseDto가 좋음

@RestController
@ControllerAdvice // 모든 Exception 다 낚아챔!!
public class ControllerExceptionHandler {
    @ExceptionHandler(CustomValidationException.class)
    // Script(String)으로 통신하는 것
    public String validationException(CustomValidationException e){
        return Script.back(e.getErrorMap().toString());
    }

    @ExceptionHandler(CustomValidationApiException.class)
    // 데이터로 통신하는 것 - Ajax, Android 통신 시 이용
    public ResponseEntity<?> validationApiException(CustomValidationApiException e){
        return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),e.getErrorMap()),HttpStatus.BAD_REQUEST);
    }

}
