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
 * Ⱥ�������
 * ������ɾ�����޸ġ�Ⱥ��
 * Created by hq on 2014/11/2.
 */
public class GroupManager {
    private static Logger logger = Logger.getLogger(GroupManager.class);

    /**
     * ����Ⱥ��
     *
     * @param groupChatInfo
     */
    public static void createGroup(GroupChatInfo groupChatInfo, List<UserInfo> userInfoList) throws XMPPException {
        logger.info("����Ⱥ��");
        if (groupChatInfo == null && groupChatInfo.getName().length() == 0) return;
        GroupChatManager.createGroupChat(groupChatInfo.getName(), getUserIdList(userInfoList)); //�ڷ������ϴ�����������Ϣ
        groupChatInfo.setJid(GroupChatManager.createGroupChatId(groupChatInfo.getName()));
        DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(groupChatInfo);
        DtClientWindow.getInstance().getCustomTree().addNode(defaultMutableTreeNode);
    }

    /**
     * �޸�Ⱥ��
     */
    public static void modifyGroup(GroupChatInfo groupChatInfo, String newRoomName, List<UserInfo> memberList) throws XMPPException {
        GroupChatManager.modifyGroupChatInfo(groupChatInfo.getJid(), newRoomName, getUserIdList(memberList));
        groupChatInfo.setName(newRoomName);
        DtClientWindow.getInstance().getCustomTree().updateGroupNode(groupChatInfo);
    }

    /**
     * ɾ��Ⱥ��
     */
    public static void deleteGroup(GroupChatInfo groupChatInfo) throws XMPPException {
        logger.info("ɾ��Ⱥ��");
        GroupChatManager.deleteGroupChat(groupChatInfo.getJid());

    }

    /**
     * ��ʾȺ����Ϣ����
     *
     * @param groupChatInfo Ⱥ����Ϣ����
     * @param title         ���ڱ���
     */
    public static void showGroupWindow(GroupChatInfo groupChatInfo, String title) {
        if (groupChatInfo != null) {
            GroupWindow groupWindow = new GroupWindow(groupChatInfo);
            groupWindow.setTitle(title);
            groupWindow.setVisible(true);
        }
    }

    /**
     * ��ȡȺ���Ա�˺��б�
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
     * ��ȡȺ������Ա��Ϣ
     *
     * @param groupId Ⱥ����
     * @return
     */
    public static List<UserInfo> getUserInfo(String groupId) {
        if (groupId == null) return null;
        List<UserInfo> userInfoList = new ArrayList<>();
        List<UserInfo> synUserInfoList = SynDataService.getInstance().synUsers(); //��ȡͬ������Ա��Ϣ
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
