package com.threetrack.dto;

import org.springframework.data.domain.Page;

public class Response<T> {

    private T data;
    private boolean success;
    private String message;
    private Page page;

    public Response() {
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

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
