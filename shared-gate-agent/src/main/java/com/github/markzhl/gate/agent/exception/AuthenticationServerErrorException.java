package com.github.markzhl.gate.agent.exception;

/**
 * Created by mark on 2017/7/5.
 */
public class AuthenticationServerErrorException extends RuntimeException {
    public AuthenticationServerErrorException(String message) {
        super(message);
    }
}
