package com.limkee.service;

import javax.ws.rs.core.Response;

import com.oneshop.constant.ErrorConstants;
import com.oneshop.dao.*;
import com.oneshop.model.*;

public class AccountService {
	public static Response doLogin (String username, String password) throws Exception{

        if (username == null || username.equals("")){
            throw new Exception(ErrorConstants.MISSING_USERNAME.getErrorCode());
        }

        if (password == null || password.equals("")){
            throw new Exception(ErrorConstants.MISSING_PASSWORD.getErrorCode());
        }
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        return Response.status(200).entity(user).build();
    }
	
	public static Response doGetDevice (String token) throws Exception{

        if (token == null || token.equals("")){
            throw new Exception(ErrorConstants.MISSING_TOKEN.getErrorCode());
        }
        
        Device device = DeviceDAO.getDevice(token);
        
        if(device == null){
        	//create budget
        	Budget budget = BudgetDAO.createBudget("0");
        	device = DeviceDAO.createDevice(token, "" + budget.getId());
        }

        return Response.status(200).entity(device).build();
    }
}
