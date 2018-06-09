package cn.sf201.core.exception;

public class StoreProcessException extends RequestProcessException {
    public StoreProcessException() {
    }

    public StoreProcessException(String message) {
        super(message);
    }

    public static final String NOT_ENOUGH_STORE = "库存不足！";
}
