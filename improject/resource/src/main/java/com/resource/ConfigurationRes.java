package com.resource;

/**
 * Created by HK on 2014/10/1.
 */
public class ConfigurationRes {
    private final static String HOST_NAME="HOST_NAME";
    private final static String PORT="PORT";
    private final static String DOMAIN="DOMAIN";


    public static String getHostName() {
        return "192.168.1.105";
    }

    public static int getPort() {
        return 5222;
    }

    public static String getDomain() {
        return "30san";
    }
}
