package com.imMsgProService;

import com.pubTools.http.HttpTools;

import java.util.HashMap;

/**
 * Created by a on 2014/9/4.
 * ȷ�ϼ�Ϊ����
 * ͨ�����ʷ��������ڷ������������
 */
public class ConfirmAddFriend {

    public enum ResponseRS{
        SERVERERROR,//����������
        SUCCESS,   //��ӳɹ�
        FAILURE;  //���ʧ��
    }



    public static String requestConfirmAddFriend(String host,int port,String url,HashMap<String,String> requestParam,boolean is_ssl){
        String requestUrl = getRequestUrl(host,port,url,is_ssl);
        String responseRs = HttpTools.executeRequest(requestUrl,requestParam);
        return "-1";
    }



    private static String getRequestUrl(String host,int port,String url,boolean is_ssl){
        StringBuffer requestUrl = new StringBuffer("");
        requestUrl.append(is_ssl?"https":"http");
        requestUrl.append("://");
        requestUrl.append(host);
        requestUrl.append(":");
        requestUrl.append(port);
        requestUrl.append("/");
        requestUrl.append(url);
        return requestUrl.toString();
    }
}
