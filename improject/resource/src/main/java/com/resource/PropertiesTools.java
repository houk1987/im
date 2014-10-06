package com.resource;

import java.io.*;
import java.util.Properties;

/**
 * properties �ļ���������
 * Created by a on 2014/7/22.
 */
public class PropertiesTools {

    public static String readValue(String filePath,String key){
        Properties props = new Properties();
       try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
            String value = props.getProperty (key);
            System.out.println(key+value);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeValue(String filePath,String parameterName,String parameterValue){
        Properties prop = new Properties();
        try {
            InputStream fis = new FileInputStream(filePath);
            //���������ж�ȡ�����б�����Ԫ�ضԣ�
            prop.load(fis);
            //���� Hashtable �ķ��� put��ʹ�� getProperty �����ṩ�����ԡ�
            //ǿ��Ҫ��Ϊ���Եļ���ֵʹ���ַ���������ֵ�� Hashtable ���� put �Ľ����
            OutputStream fos = new FileOutputStream(filePath);
            prop.setProperty(parameterName, parameterValue);
            //���ʺ�ʹ�� load �������ص� Properties ���еĸ�ʽ��
            //���� Properties ���е������б�����Ԫ�ضԣ�д�������
            prop.store(fos, "Update '" + parameterName + "' value");
        } catch (IOException e) {
            System.err.println("Visit "+filePath+" for updating "+parameterName+" value error");
        }
    }
}
