package org.softaria.ams.platform.db;

public class DataBaseException extends RuntimeException {

    private final String message;

    public DataBaseException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
