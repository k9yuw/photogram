package com.cos.photogramstart.handler.Exception;

import java.util.Map;

public class CustomException extends RuntimeException{

    // 객체를 구분할 때 쓰는 시리얼번호
    private static final long serialVersionUID = 1L;

    public CustomException(String message) {
        super(message);
    }

}
