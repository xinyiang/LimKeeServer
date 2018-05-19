package com.limkee.controller;

import com.limkee.constant.HttpConstants;
import com.limkee.constant.PrefConstants;
import com.limkee.model.ServerMode;
import com.oss.steve.http.Database;
import com.oss.steve.util.LogFilter;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class Initializer implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String url = "jdbc:mysql://localhost/LimKee";
        String user = "root";
        String testPwd = "5HHJwZH]X+2Gy44s";
        String productionPwd = "5HHJwZH]X+2Gy44s";
        String localdbpwd = "5HHJwZH]X+2Gy44s";

        // grab environment variable to check if we are on production environment
        String username = System.getProperty("os.name");

        String pwd;

        if (!username.equals("Linux")) {
            // in local environment, use localdbpwd
            pwd = localdbpwd;
        } else {

            if (PrefConstants.CURRENT_OPERATING_MODE == ServerMode.PRODUCTION){
                pwd = productionPwd;
            } else {
                pwd = testPwd;
            }
        }

        Database.setUrl(url);
        Database.setUser(user);
        Database.setPwd(pwd);

        //Load HTTPConstants
        new HttpConstants();


        loadLoggingFilter();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private void loadLoggingFilter(){
        Client client = ClientBuilder.newClient();
        client.register(new LogFilter());
    }
}