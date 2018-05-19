package com.limkee.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class InputStreamUtility {

    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static HashMap<String, String> convertResultStringToHashMap(String result){

        HashMap<String, String> resultMap = new HashMap<>();

        Scanner sc = new Scanner (result);
        sc.useDelimiter("&");
        while (sc.hasNext()){
            String pair = sc.next();

            String[] values = pair.split("=");

            if (values.length >= 2){
                resultMap.put(values[0], values[1]);
            }

        }

        return resultMap;

    }

    public static InputStream clone(final InputStream inputStream) {
        try {
            inputStream.mark(0);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int readLength = 0;
            while ((readLength = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, readLength);
            }
            inputStream.reset();
            outputStream.flush();
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
