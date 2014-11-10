package com.comunication.chat;

import com.comunication.connection.CommunicationConnection;
import com.comunication.connection.ConnectionManager;
import org.apache.log4j.Logger;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.FormField;
import org.jivesoftware.smackx.muc.Affiliate;
import org.jivesoftware.smackx.muc.HostedRoom;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.RoomInfo;

import java.util.*;

/**
 * Created by hq on 2014/11/8.
 */
public class GroupChatManager {

    private static Logger logger = Logger.getLogger(GroupChatManager.class);
    private static CommunicationConnection connection = ConnectionManager.getConnection();
    private static HashMap<String, MultiUserChat> multiUserChatHashMap = new HashMap<>();

    public static void createGroupChat(String groupName, List<String> groupMembers) {
        MultiUserChat muc = joinGroupChatByGroupName(groupName);
        try {
            Form form = muc.getConfigurationForm(); // 获得聊天室的配置表单
            Form submitForm = form.createAnswerForm();// 根据原始表单创建一个要提交的新表单。
            for (Iterator<FormField> fields = form.getFields(); fields.hasNext(); ) {// 向要提交的表单添加默认答复
                FormField field = (FormField) fields.next();
                if (!FormField.TYPE_HIDDEN.equals(field.getType()) && field.getVariable() != null) {
                    submitForm.setDefaultAnswer(field.getVariable());// 设置默认值作为答复
                }
            }
            // 设置聊天室的新拥有者
            List<String> owners = new ArrayList<String>();
            owners.add(connection.getFullUserName());// 用户JID
            submitForm.setAnswer("muc#roomconfig_roomowners", owners);
            // 设置聊天室是持久聊天室，即将要被保存下来
            submitForm.setAnswer("muc#roomconfig_persistentroom", true);
            // 房间仅对成员开放
            submitForm.setAnswer("muc#roomconfig_membersonly", true);
            // 允许占有者邀请其他人
            submitForm.setAnswer("muc#roomconfig_allowinvites", true);
            // 登录房间对话
            submitForm.setAnswer("muc#roomconfig_enablelogging", true);
            // 仅允许注册的昵称登录
            submitForm.setAnswer("x-muc#roomconfig_reservednick", true);
            // 允许使用者修改昵称
            submitForm.setAnswer("x-muc#roomconfig_canchangenick", false);
            // 允许用户注册房间
            submitForm.setAnswer("x-muc#roomconfig_registration", false);
            // 发送已完成的表单（有默认值）到服务器来配置聊天室
            submitForm.setAnswer("muc#roomconfig_passwordprotectedroom", true);
            if (groupMembers != null && groupMembers.size() > 0) {
                for (String jid : groupMembers) {
                    muc.grantAdmin(jid);
                }
            }
            // 发送已完成的表单（有默认值）到服务器来配置聊天室
            muc.sendConfigurationForm(submitForm);
        } catch (XMPPException e) {
            e.printStackTrace();
        }
    }

    public static void modifyGroupChatInfo(String jid, String newGroupName, List<String> memberList) {
        MultiUserChat muc = joinGroupChatByGroupId(jid);
        if (muc != null) {
            try {
                Form form = muc.getConfigurationForm();
                Form submitForm = form.createAnswerForm();  // 根据原始表单创建一个要提交的新表单。
                submitForm.setAnswer("muc#roomconfig_roomname", newGroupName); //设置房间的新名称
                List<Affiliate> delMembers = (List<Affiliate>) muc.getMembers(); //先删除所以的群成员
                if (delMembers != null && delMembers.size() > 0) {
                    for (Affiliate affiliate : delMembers) {
                        muc.revokeMembership(affiliate.getJid());
                    }
                }
                if (memberList != null && memberList.size() > 0) {
                    for (String member : memberList) {
                        muc.grantMembership(member);
                    }
                }
                muc.sendConfigurationForm(submitForm);
            } catch (XMPPException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteGroupChatByGroupName(String groupName) throws XMPPException {
        deleteGroupChat(createGroupChatId(groupName));
    }

    public static void deleteGroupChat(String groupJid) throws XMPPException {
        MultiUserChat multiUserChat = joinGroupChatByGroupId(groupJid);
        multiUserChat.destroy("1", "1");
    }

    public static MultiUserChat joinGroupChatByGroupName(String groupName) {
        String groupChatId = createGroupChatId(groupName);
        MultiUserChat multiUserChat = joinGroupChatByGroupId(groupChatId);
        return multiUserChat;
    }

    public static MultiUserChat joinGroupChatByGroupId(String groupChatId) {
        if (groupChatId == null) return null;
        MultiUserChat multiUserChat = createMultiUserChat(groupChatId);
        try {
            multiUserChat.join(connection.getUser());
        } catch (XMPPException e) {
            logger.error("你不是改群组成员，加入失败");
            e.printStackTrace();
            return null;
        }
        return multiUserChat;
    }

    public static List<GroupChatInfo> getAllGroupChat() {
        List<GroupChatInfo> list = new ArrayList<>();
        Collection<HostedRoom> roomInfoList = null; //获取所有会议室
        try {
            roomInfoList = MultiUserChat.getHostedRooms(connection, connection.getServiceName());
            if (!roomInfoList.isEmpty()) {
                for (HostedRoom k : roomInfoList) {
                    for (HostedRoom j : MultiUserChat.getHostedRooms(connection, k.getJid())) {
                        RoomInfo info2 = MultiUserChat.getRoomInfo(connection, j.getJid());
                        if (j.getJid().indexOf("@") > 0) {
                            MultiUserChat multiUserChat = joinGroupChatByGroupId(j.getJid());
                            multiUserChatHashMap.put(j.getJid(), multiUserChat);
                            GroupChatInfo groupChatInfo = new GroupChatInfo();
                            groupChatInfo.setName(j.getName());//聊天室的名称
                            groupChatInfo.setJid(j.getJid());//聊天室JID
                            groupChatInfo.setOwner(getOwnerId(j.getJid()));
                            groupChatInfo.setOccupants(info2.getOccupantsCount());//聊天室中占有者数量
                            groupChatInfo.setDescription(info2.getDescription());//聊天室的描述
                            groupChatInfo.setSubject(info2.getSubject());//聊天室的主题
                            groupChatInfo.setMemberList(getMemberIdList(j.getJid()));
                            list.add(groupChatInfo);

                        }
                    }
                }
            }
        } catch (XMPPException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static List<String> getMemberIdList(String jid) {
        try {
            MultiUserChat muc = joinGroupChatByGroupId(jid);
            if(muc == null)return null;
            List<String> listUser = new ArrayList<>();
            Collection<Affiliate> collection = muc.getAdmins();
            if (collection != null && collection.size() > 0) {
                for (Affiliate affiliate : collection) {
                    listUser.add(affiliate.getJid());
                }
                return listUser;
            }
        } catch (XMPPException e) {
            return null;
        }
        return null;
    }

    private static String getOwnerId(String jid) {
        try {
            MultiUserChat muc = joinGroupChatByGroupId(jid);
            if (muc == null) return "";
            Collection<Affiliate> collection = muc.getOwners();
            if (collection != null && collection.size() > 0) {
                for (Affiliate affiliate : collection) {
                    return affiliate.getJid();
                }
            }
        } catch (XMPPException e) {
            return "";
        }
        return "";
    }


    private static MultiUserChat createMultiUserChat(String groupChatId) {
        MultiUserChat multiUserChat = multiUserChatHashMap.get(groupChatId);
        if (multiUserChat == null) {
            multiUserChat = new MultiUserChat(connection, groupChatId);
            multiUserChatHashMap.put(groupChatId, multiUserChat);
        }
        return multiUserChat;
    }

    public static String createGroupChatId(String groupName) {
        if (groupName == null) {
            logger.error("群聊名称为空");
            return null;
        }
        return groupName + "@conference." + connection.getServiceName();
    }

}
