package com.imService.service;

import com.imService.connection.ImConnection;

/**
 * ��¼�ͻ��˷���ӿ�
 * Created by HK on 2014/9/14.
 */
public class LoginService {

    private static LoginService loginService;
    private ImConnection imConnection;

    public static LoginService getInstance(ImConnection imConnection) {
        if(null == loginService){
            loginService = new LoginService(imConnection);
        }
        return loginService;
    }

    public LoginService(ImConnection imConnection) {
        this.imConnection = imConnection;
    }


    public void login(String user, String password) throws Exception{
        if(imConnection == null)throw new NullPointerException("�����������쳣!");
        this.imConnection.connection();
        this.imConnection.login(user,password);
    }


    public void loginOut() throws Exception{
        if(imConnection == null)throw new NullPointerException("�����������쳣!");
        imConnection.close();  //�ر�����
    }
}
