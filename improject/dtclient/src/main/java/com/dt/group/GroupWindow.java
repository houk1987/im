package com.dt.group;


import com.comunication.chat.GroupChatInfo;
import com.ui.tools.Tools;

import javax.swing.*;

/**
 * 群组信息窗口
 * Created by hq on 2014/11/2.
 */
 class GroupWindow extends JDialog{
    private GroupChatInfo groupChatInfo;
    private GroupWindowContent groupWindowContent;
    public GroupWindow(GroupChatInfo groupChatInfo) {
        this.groupChatInfo = groupChatInfo;
        groupWindowContent = new GroupWindowContent(this);
        setContentPane(groupWindowContent);
        setSize(350, 400);
        Tools.setWindowOnScreamCenter(this); //设置居中显示
    }

    public GroupChatInfo getGroup() {
        return groupChatInfo;
    }

    public GroupWindowContent getGroupWindowContent() {
        return groupWindowContent;
    }
}
