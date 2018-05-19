package com.limkee.model;

public class ErrorDisplay {
	private String errorCode;
    private String errorName;
    private String errorDescription;

    public ErrorDisplay() {
    }

    //
    //  This is used to show unknown error.
    //
    public ErrorDisplay(String errorCode) {
        this.errorCode = errorCode;
        this.errorName = "Error";
        this.errorDescription = "Unknown error occurred. Please try again later. (Error " + errorCode + ")";
    }

    //
    // This is generally used to display errors on mobile devices
    //
    public ErrorDisplay(String errorCode, String errorName, String errorDescription) {
        this.errorCode = errorCode;
        this.errorName = errorName;
        this.errorDescription = errorDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorName() {
        return errorName;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}