package com.limkee.controller;

import io.swagger.annotations.*;

import com.limkee.model.*;
import com.limkee.service.*;
import com.limkee.utility.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "Account", description = "Login by User")
@Path("/account")
@Produces({"application/json"})
public class MainController {
	@POST
    @Path("/login")
    @ApiOperation(value = "Login User - Normal",
            notes = "Login user using username and password. Returns a User object.",
            response = User.class)
    @ApiParam
    @ApiResponses(value = {
            @ApiResponse(code = 101, message = "Missing Username/Email"),
            @ApiResponse(code = 102, message = "Missing Password"),
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response doUserLoginNormal(@ApiParam(value = "Username/Email of account to be logged in", required = true) @FormParam("userName") String username,
                                      @ApiParam(value = "Password of account to be logged in", required = true) @FormParam("password") String password) {

        try {
            return AccountService.doLogin(username, password);
        } catch (Exception e){
            return ExceptionHandler.handleException(e);
        }
    }
	
	@POST
    @Path("/get-device")
    @ApiOperation(value = "Retrieval and registration of device",
            notes = "Retrieving of device and registering a new device. Returns a Device object.",
            response = Device.class)
    @ApiParam
    @ApiResponses(value = {
            @ApiResponse(code = 102, message = "Missing Token")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response doGetDevice(@ApiParam(value = "Token of device", required = true) @FormParam("token") String token) {

        try {
            return AccountService.doGetDevice(token);
        } catch (Exception e){
            return ExceptionHandler.handleException(e);
        }
    }
}