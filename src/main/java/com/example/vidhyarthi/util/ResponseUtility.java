package com.example.vidhyarthi.util;

import com.example.vidhyarthi.Response.ResponseObject;
import org.springframework.http.HttpStatus;


public class ResponseUtility {
    public static ResponseObject resourceCreated(Object object, String message, HttpStatus status) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setBody(object);
        responseObject.setStatus(HttpStatus.CREATED);
        responseObject.setMessage(message);
        return responseObject;
    }

    public static ResponseObject resourceNotCreated(Object object, String message, HttpStatus status) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setStatus(status);
        responseObject.setBody(object);
        responseObject.setMessage(message);
        return responseObject;
    }

    public static ResponseObject resourceNotFound(Object object, String message, HttpStatus status) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setBody(object);
        responseObject.setStatus(status);
        responseObject.setMessage(message);
        return responseObject;
    }

    public static ResponseObject resourceFound(Object object, String message, HttpStatus status) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setBody(object);
        responseObject.setStatus(status);
        responseObject.setMessage(message);
        System.out.println(object.getClass().getName() + " houqhdoqw");
        return responseObject;
    }
}
