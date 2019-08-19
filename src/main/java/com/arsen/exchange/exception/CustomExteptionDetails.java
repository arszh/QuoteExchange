package com.arsen.exchange.exception;

class CustomExteptionDetails {
    private int errorCode;
    private String description;
    private ExceptionLevel level;

    public CustomExteptionDetails(int errorCode, String description, ExceptionLevel level) {
        this.errorCode = errorCode;
        this.description = description;
        this.level = level;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExceptionLevel getLevel() {
        return level;
    }

    public void setLevel(ExceptionLevel level) {
        this.level = level;
    }


    @Override
    public String toString() {
        return "CustomExteptionDetails{" +
                "errorCode=" + errorCode +
                ", description='" + description + '\'' +
                ", level='" + level.toString() + '\'' +
                '}';
    }
}
