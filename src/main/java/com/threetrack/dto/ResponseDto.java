package com.threetrack.dto;

public class ResponseDto<T> {

    private T data;
    private boolean success;
    private String message;

    public ResponseDto() {
        this.success=false;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
