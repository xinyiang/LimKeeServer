package com.limkee.controller;

import org.glassfish.jersey.server.ContainerRequest;

import com.limkee.constant.ErrorConstants;
import com.limkee.constant.HttpConstants;
import com.limkee.constant.PrefConstants;
import com.limkee.model.ServerMode;
import com.limkee.utility.InputStreamUtility;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import static com.limkee.utility.InputStreamUtility.convertStreamToString;

@Provider
public class AuthFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        //
        //  If it is a swagger json reading request, dont request for api key!
        String path = containerRequestContext.getUriInfo().getPath();

        if (path.equals("swagger.json")){
            if (PrefConstants.CURRENT_OPERATING_MODE == ServerMode.PRODUCTION) {
                Response response = Response
                        .status(Response.Status.BAD_REQUEST)
                        .type(MediaType.APPLICATION_JSON_TYPE)
                        .entity(ErrorConstants.INVALID_REQUEST)
                        .build();
                containerRequestContext.abortWith(response);
            }

            return;
        }

        String method = containerRequestContext.getMethod();

        if (method.equals("GET")){
            return;
        }
        
        //
        //  Else, carry on!

        ContainerRequest containerRequest = (ContainerRequest) containerRequestContext;
        containerRequest.bufferEntity();

        InputStream inputStream = containerRequest.getEntityStream();
        InputStream cloneInputStream = InputStreamUtility.clone(inputStream);

        String resultString = convertStreamToString(inputStream);
        HashMap<String, String> parametersMap = InputStreamUtility.convertResultStringToHashMap(resultString);

        String api = parametersMap.get("api");

        if (api == null || api.isEmpty()) {
            return;
        } else if (!api.equals(HttpConstants.APIKEY)){
            System.out.println("INVALID API");

            Response response = Response
                    .status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(ErrorConstants.INVALID_API)
                    .build();
            containerRequestContext.abortWith(response);
            return;
        }

        containerRequestContext.setEntityStream(cloneInputStream);

    }

}