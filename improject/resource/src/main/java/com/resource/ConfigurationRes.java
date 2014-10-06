package com.resource;

/**
 * Created by HK on 2014/10/1.
 */
public class ConfigurationRes {
    private final static String FILE_PATH = "config/configuration.properties";
    private final static String HOST_NAME="HOST_NAME";
    private final static String PORT="PORT";
    private final static String DOMAIN="DOMAIN";


    public static String getHostName() {
        return PropertiesTools.readValue(FILE_PATH,HOST_NAME);
    }

    public static int getPort() {
        return Integer.valueOf(PropertiesTools.readValue(FILE_PATH,PORT));
    }

    public static String getDomain() {
        return PropertiesTools.readValue(FILE_PATH,DOMAIN);
    }
}
