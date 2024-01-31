package com.nova.recycle.recycleme.domain.security.common;

public class AccessDeniedExcption extends RuntimeException{
    public AccessDeniedExcption(String message){
        super(message);
    }
}
