package com.resource;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * Created by HK on 2014/10/1.
 */
public class ConfigurationRes {
    private final static String FILE_PATH = "config/configuration.properties";
    private final static String HOST_NAME="HOST_NAME";
    private final static String PORT="PORT";
    private final static String DOMAIN="DOMAIN";
    private final static String IMAGE_RES_PATH="IMAGE_RES_PATH";   //ͼƬ��Դ�ļ�·��
    private final static String BUTTON_IMAGE_RES_PATH = "BUTTON_IMAGE_RES_PATH";         //��ťͼƬ��Դ�ļ�·��
    private final static String PRESENCE_IMAGE_RES_PATH = "PRESENCE_IMAGE_RES_PATH"; //״̬ͼƬ��·��


    public static String getHostName() {
        return PropertiesTools.readValue(FILE_PATH,HOST_NAME);
    }

    public static int getPort() {
        return Integer.valueOf(PropertiesTools.readValue(FILE_PATH,PORT));
    }

    public static String getDomain() {
        return PropertiesTools.readValue(FILE_PATH,DOMAIN);
    }

    public static String getImageResPath() {
        System.out.println(PropertiesTools.readValue(FILE_PATH,IMAGE_RES_PATH));
        return PropertiesTools.readValue(FILE_PATH,IMAGE_RES_PATH);
    }

    public static String getButtonImageResPath() {
        return PropertiesTools.readValue(FILE_PATH,BUTTON_IMAGE_RES_PATH);
    }

    public static String getPresenceImageResPath() {
         return PropertiesTools.readValue(FILE_PATH,PRESENCE_IMAGE_RES_PATH);
    }
}
