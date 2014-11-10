package com.dt.start;

import com.comunication.chat.GroupChatInfo;
import com.comunication.chat.GroupChatManager;
import com.comunication.connection.ConnectionManager;
import com.dt.main.DtClientWindow;
import com.dt.vo.UserInfo;

import java.util.List;

/**
 * Created by lenovo on 2014/10/31.
 */
public class StartClient {

    private  static UserInfo userInfo = new UserInfo();
    public static void main(String[] args) {
        userInfo.setId("10000"+"@"+ConnectionManager.getConnection().getServiceName());
        ConnectionManager.createConnection();
        boolean isLoginSuccess = ConnectionManager.login(userInfo.getId().split("@")[0], "123");
        if(isLoginSuccess){
            DtClientWindow dtClientWindow = DtClientWindow.getInstance();
            dtClientWindow.setUserInfo(userInfo);
            dtClientWindow.setVisible(true);
//            List<GroupChatInfo> list = GroupChatManager.getAllGroupChat();
//            for(GroupChatInfo groupChatInfo : list){
//                GroupChatManager.joinGroupChatByGroupId(groupChatInfo.getJid());
//            }
        }
    }

    public static UserInfo getUserInfo() {
        return userInfo;
    }
}
