package com.dt.group;

import com.comunication.chat.GroupChat;
import com.comunication.chat.GroupChatInfo;
import com.comunication.chat.GroupChatManager;
import com.comunication.connection.ConnectionManager;
import com.dt.main.DtClientWindow;
import com.dt.main.tree.SynDataService;
import com.dt.start.StartClient;
import com.dt.vo.UserInfo;
import org.apache.log4j.Logger;
import org.jivesoftware.smack.XMPPException;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 群组管理类
 * 创建、删除、修改、群组
 * Created by hq on 2014/11/2.
 */
public class GroupManager {
    private static Logger logger = Logger.getLogger(GroupManager.class);

    /**
     * 创建群组
     *
     * @param groupChatInfo
     */
    public static void createGroup(GroupChatInfo groupChatInfo, List<UserInfo> userInfoList) throws XMPPException {
        logger.info("创建群组");
        if (groupChatInfo == null && groupChatInfo.getName().length() == 0) return;
        GroupChatManager.createGroupChat(groupChatInfo.getName(), getUserIdList(userInfoList)); //在服务器上创建会议室信息
        groupChatInfo.setJid(GroupChatManager.createGroupChatId(groupChatInfo.getName()));
        DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(groupChatInfo);
        DtClientWindow.getInstance().getCustomTree().addNode(defaultMutableTreeNode);
    }

    /**
     * 修改群组
     */
    public static void modifyGroup(GroupChatInfo groupChatInfo, String newRoomName, List<UserInfo> memberList) throws XMPPException {
        GroupChatManager.modifyGroupChatInfo(groupChatInfo.getJid(), newRoomName, getUserIdList(memberList));
        groupChatInfo.setName(newRoomName);
        DtClientWindow.getInstance().getCustomTree().updateGroupNode(groupChatInfo);
    }

    /**
     * 删除群组
     */
    public static void deleteGroup(GroupChatInfo groupChatInfo) throws XMPPException {
        logger.info("删除群组");
        GroupChatManager.deleteGroupChat(groupChatInfo.getJid());

    }

    /**
     * 显示群组信息窗口
     *
     * @param groupChatInfo 群组信息对象
     * @param title         窗口标题
     */
    public static void showGroupWindow(GroupChatInfo groupChatInfo, String title) {
        if (groupChatInfo != null) {
            GroupWindow groupWindow = new GroupWindow(groupChatInfo);
            groupWindow.setTitle(title);
            groupWindow.setVisible(true);
        }
    }

    /**
     * 获取群组成员账号列表
     *
     * @param userInfoList
     * @return
     */
    private static List<String> getUserIdList(List<UserInfo> userInfoList) {
        if (userInfoList == null) return null;
        List<String> userIdList = new ArrayList<>();
        for (UserInfo userInfo : userInfoList) {
                if(userInfo.getId().equals(StartClient.getUserInfo().getId()))continue;
                userIdList.add(userInfo.getId());
        }
        return userIdList;
    }

    /**
     * 获取群组中人员信息
     *
     * @param groupId 群组编号
     * @return
     */
    public static List<UserInfo> getUserInfo(String groupId) {
        if (groupId == null) return null;
        List<UserInfo> userInfoList = new ArrayList<>();
        List<UserInfo> synUserInfoList = SynDataService.getInstance().synUsers(); //获取同步的人员信息
        List<String> memberIdList = GroupChatManager.getMemberIdList(groupId);
        if (synUserInfoList != null && synUserInfoList.size() > 0) {
            for (UserInfo userInfo : synUserInfoList) {
                String id = userInfo.getId();
                if ((memberIdList != null && memberIdList.contains(id)) || id.equals(DtClientWindow.getInstance().getUserInfo().getId())) {
                    userInfoList.add(userInfo);
                }
            }
        }
        return userInfoList;
    }
}
