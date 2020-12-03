package com.myapp.smartagricultureplus.Object;

public class Operation {
    String operationname;
    int  operationimage;
    String operationdate;
    String operationoperating;
    public Operation() {

    }

    public Operation(String operationname) {
        this.operationname = operationname;
    }

    public Operation(String operationname, int operationimage) {
        this.operationname = operationname;
        this.operationimage = operationimage;
    }

    public Operation(String operationname, int operationimage, String operationdate) {
        this.operationname = operationname;
        this.operationimage = operationimage;
        this.operationdate = operationdate;
    }

    public Operation(String operationname, int operationimage, String operationdate, String operationoperating) {
        this.operationname = operationname;
        this.operationimage = operationimage;
        this.operationdate = operationdate;
        this.operationoperating = operationoperating;
    }

    public String getOperationname() {
        return operationname;
    }

    public void setOperationname(String operationname) {
        this.operationname = operationname;
    }

    public int getOperationimage() {
        return operationimage;
    }

    public void setOperationimage(int operationimage) {
        this.operationimage = operationimage;
    }

    public String getOperationdate() {
        return operationdate;
    }

    public void setOperationdate(String operationdate) {
        this.operationdate = operationdate;
    }

    public String getOperationoperating() {
        return operationoperating;
    }

    public void setOperationoperating(String operationoperating) {
        this.operationoperating = operationoperating;
    }
}
