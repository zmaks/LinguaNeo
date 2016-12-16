package com.zheltoukhov.linguaneo.translator.exception;

public class TranslatorException extends RuntimeException {
    public TranslatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public TranslatorException(String message) {
        super(message);
    }
}
