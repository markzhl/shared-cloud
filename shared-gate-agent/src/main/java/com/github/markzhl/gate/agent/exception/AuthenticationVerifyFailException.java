package com.github.markzhl.gate.agent.exception;

/**
 * Created by mark on 2017/7/5.
 */
public class AuthenticationVerifyFailException extends RuntimeException {
    public AuthenticationVerifyFailException(String message) {
        super(message);
    }
}
