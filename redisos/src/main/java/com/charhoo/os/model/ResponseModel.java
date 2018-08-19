package com.charhoo.os.model;

public class ResponseModel {

    public static final String STATUS_SUCCESS = "1";
    public static final String STATUS_FAIL = "0";
    public static final String STATUS_NORESULT = "2";

    private String status;
    private Object result;
    private String error;


    public ResponseModel(String status, Object result, String error){
        this.status = status;
        this.result = result;
        this.error = error;
    }

    public static ResponseModel getInstance(String status, Object result, String error){
        return new ResponseModel(status,result,error);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
