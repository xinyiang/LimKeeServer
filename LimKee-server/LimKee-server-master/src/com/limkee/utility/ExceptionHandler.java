package com.limkee.utility;

import java.util.HashMap;

import javax.ws.rs.core.Response;

import com.limkee.constant.ErrorConstants;
import com.limkee.model.ErrorDisplay;

public class ExceptionHandler {
	public static Response handleException(Exception e){
        HashMap<String, ErrorDisplay> apiErrorMap = ErrorConstants.getErrorMap();

        //Get error message to show from exception
        ErrorDisplay errorDisplay = apiErrorMap.get(e.getMessage());

        //If null = no such errors in httpconstants.
        if (errorDisplay != null){

            String errorValue = e.getMessage();

            return Response.status(200).entity(ErrorConstants.getErrorDisplay(errorValue)).build();

        }
        e.printStackTrace();
        return Response.status(200).build();

    }
}