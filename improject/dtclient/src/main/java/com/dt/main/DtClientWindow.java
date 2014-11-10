package com.dt.main;


import com.dt.interceptor.ChatMessageHandler;
import com.dt.main.tree.CustomTree;
import com.dt.main.tree.OrgTree;
import com.dt.vo.UserInfo;
import com.ui.tools.Tools;
import org.apache.log4j.Logger;

import javax.swing.*;

/**
 * Created by hq on 2014/11/1.
 */
public class DtClientWindow extends JFrame{
    private static Logger logger = Logger.getLogger(DtClientWindow.class);
    private final static DtClientWindow dtClientWindow = new DtClientWindow();
    private DtClientWindowContent dtClientWindowContent;
    private UserInfo userInfo;
    private DtClientWindow(){
        setSize(350,700);
        dtClientWindowContent = new DtClientWindowContent();
        setContentPane(dtClientWindowContent);
        Tools.setWindowLocationOnScreamRight(this);  //╬ссротй╬
        new ChatMessageHandler();
    }

    public static DtClientWindow getInstance() {
        return dtClientWindow;
    }

    public CustomTree getCustomTree(){
        return dtClientWindowContent.getCustomTree();
    }

    public OrgTree getOrgTree(){
        return dtClientWindowContent.getOrgTree();
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
