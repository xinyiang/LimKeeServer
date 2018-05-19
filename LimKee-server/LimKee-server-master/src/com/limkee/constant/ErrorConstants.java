package com.limkee.constant;

import java.lang.reflect.Field;
import java.util.HashMap;
import com.oneshop.model.ErrorDisplay;

public class ErrorConstants {

    //  This hashmap is used for Retrieval of error messages
    private static HashMap<String, ErrorDisplay> errorMap = new HashMap<>();

    //
    //  ERROR DECLARATION
    //  This is where you add new error variables!
    //  Error codes must not repeat. Error name and description are equivalent of those shown in error message boxes!
    //
    
    //Account.
    public static final ErrorDisplay MISSING_USERNAME = new ErrorDisplay("099", "Error", "Please fill in your username/email");
    public static final ErrorDisplay MISSING_PASSWORD = new ErrorDisplay("100", "Error", "Please fill in your password");
    public static final ErrorDisplay MISSING_PRODUCTID = new ErrorDisplay("101", "Error", "Please include the product ID");
    public static final ErrorDisplay MISSING_TOKEN = new ErrorDisplay("102", "Error", "Please include the device token");
    public static final ErrorDisplay MISSING_WISHLIST_NAME = new ErrorDisplay("103", "Error", "Please include the name of the wishlist");
    public static final ErrorDisplay MISSING_WISHLISTID = new ErrorDisplay("104", "Error", "Please include the wishlist ID");

    // 500 - ALREADY EXIST
    public static final ErrorDisplay PRODUCT_ALREADY_EXIST_IN_BUDGET = new ErrorDisplay("500", "Error", "Product has already been added to budget.");
    public static final ErrorDisplay WISHLIST_ALREADY_EXIST = new ErrorDisplay("501", "Error", "Wishlist already exist.");
    public static final ErrorDisplay PRODUCT_ALREADY_EXIST_IN_WISHLIST = new ErrorDisplay("502", "Error", "Product has already been added to wishlist.");
    
    //
    //  600 ~ 900 ALL OTHER ERRORS
    //
    public static final ErrorDisplay MISSING_API = new ErrorDisplay("600"); //missing API key
    public static final ErrorDisplay INVALID_API = new ErrorDisplay("601"); //invalid API key
    public static final ErrorDisplay INVALID_REQUEST = new ErrorDisplay("700"); //Invalid request for security related, etc.
    public static final ErrorDisplay SERVER_ERROR = new ErrorDisplay("900"); //server error
    
    
    
    
    //
    //  This is where errorMap hashmap is initialised and inserted!
    //

    static {
        //Gets all fields using reflection
        Field[] fields = ErrorConstants.class.getFields();
        for (Field f : fields){
            try {
                //Inserts all variables in this class into hashmap
                Object object = f.get(new ErrorDisplay());

                if (object instanceof ErrorDisplay){
                    String errorCode = ((ErrorDisplay) object).getErrorCode();
                    errorMap.put(errorCode, (ErrorDisplay) object);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    //
    //  Gets Error based on error code given
    //
    public static ErrorDisplay getErrorDisplay (String errorCode){
        return errorMap.get(errorCode);
    }

    //
    //  Gets Error name based on error code given
    //
    public static String getErrorName (String errorCode){
        ErrorDisplay errorDisplay = getErrorDisplay(errorCode);

        if (errorDisplay != null){
            return errorDisplay.getErrorName();
        }

        return null;
    }

    //
    //  Gets Error Map container ErrorDisplay items.
    //  Key: ErrorCode. Value: ErrorDisplay item
    //
    public static HashMap<String, ErrorDisplay> getErrorMap() {
        return errorMap;
    }
}
