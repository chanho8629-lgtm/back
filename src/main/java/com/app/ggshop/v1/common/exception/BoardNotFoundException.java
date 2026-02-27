package com.app.ggshop.v1.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException(String message) {
        super(message);
    }
}
