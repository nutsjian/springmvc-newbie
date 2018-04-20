package me.nutsjian.springmvc.newbie.dto;

import com.google.gson.Gson;

public class Response<T> {
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static String ok() {
        Response response = new Response();
        response.setCode(200);
        response.setMessage("success");
        return new Gson().toJson(response);
    }

    public static String error() {
        Response response = new Response();
        response.setCode(500);
        response.setMessage("error");
        return new Gson().toJson(response);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
