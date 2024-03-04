package com.cos.photogramstart.handler.Exception;

import java.util.Map;

public class CustomApiException extends RuntimeException{

    // 객체를 구분할 때 쓰는 시리얼번호
    private static final long serialVersionUID = 1L;

    public CustomApiException(String message) {
        super(message);
    }
}
