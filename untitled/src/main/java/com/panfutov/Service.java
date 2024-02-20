package com.panfutov;

import com.panfutov.result.Result;

import java.util.Random;

public class Service {
    private final Random rand = new Random();

    static class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }
    }

    public void doWithException() {
        if (rand.nextBoolean()) {
            throw new BusinessException("Failed");
        }
    }

    public Result<Void> doWithResult() {
        if (rand.nextBoolean()) {
            Result.failure("Failed");
        }
        return Result.successVoid();
    }
}
