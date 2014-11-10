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
            Form form = muc.getConfigurationForm(); // ��������ҵ����ñ�
            Form submitForm = form.createAnswerForm();// ����ԭʼ������һ��Ҫ�ύ���±���
            for (Iterator<FormField> fields = form.getFields(); fields.hasNext(); ) {// ��Ҫ�ύ�ı����Ĭ�ϴ�
                FormField field = (FormField) fields.next();
                if (!FormField.TYPE_HIDDEN.equals(field.getType()) && field.getVariable() != null) {
                    submitForm.setDefaultAnswer(field.getVariable());// ����Ĭ��ֵ��Ϊ��
                }
            }
            // ���������ҵ���ӵ����
            List<String> owners = new ArrayList<String>();
            owners.add(connection.getFullUserName());// �û�JID
            submitForm.setAnswer("muc#roomconfig_roomowners", owners);
            // �����������ǳ־������ң�����Ҫ����������
            submitForm.setAnswer("muc#roomconfig_persistentroom", true);
            // ������Գ�Ա����
            submitForm.setAnswer("muc#roomconfig_membersonly", true);
            // ����ռ��������������
            submitForm.setAnswer("muc#roomconfig_allowinvites", true);
            // ��¼����Ի�
            submitForm.setAnswer("muc#roomconfig_enablelogging", true);
            // ������ע����ǳƵ�¼
            submitForm.setAnswer("x-muc#roomconfig_reservednick", true);
            // ����ʹ�����޸��ǳ�
            submitForm.setAnswer("x-muc#roomconfig_canchangenick", false);
            // �����û�ע�᷿��
            submitForm.setAnswer("x-muc#roomconfig_registration", false);
            // ��������ɵı�����Ĭ��ֵ����������������������
            submitForm.setAnswer("muc#roomconfig_passwordprotectedroom", true);
            if (groupMembers != null && groupMembers.size() > 0) {
                for (String jid : groupMembers) {
                    muc.grantAdmin(jid);
                }
            }
            // ��������ɵı�����Ĭ��ֵ����������������������
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
                Form submitForm = form.createAnswerForm();  // ����ԭʼ������һ��Ҫ�ύ���±���
                submitForm.setAnswer("muc#roomconfig_roomname", newGroupName); //���÷����������
                List<Affiliate> delMembers = (List<Affiliate>) muc.getMembers(); //��ɾ�����Ե�Ⱥ��Ա
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
            logger.error("�㲻�Ǹ�Ⱥ���Ա������ʧ��");
            e.printStackTrace();
            return null;
        }
        return multiUserChat;
    }

    public static List<GroupChatInfo> getAllGroupChat() {
        List<GroupChatInfo> list = new ArrayList<>();
        Collection<HostedRoom> roomInfoList = null; //��ȡ���л�����
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
                            groupChatInfo.setName(j.getName());//�����ҵ�����
                            groupChatInfo.setJid(j.getJid());//������JID
                            groupChatInfo.setOwner(getOwnerId(j.getJid()));
                            groupChatInfo.setOccupants(info2.getOccupantsCount());//��������ռ��������
                            groupChatInfo.setDescription(info2.getDescription());//�����ҵ�����
                            groupChatInfo.setSubject(info2.getSubject());//�����ҵ�����
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
            logger.error("Ⱥ������Ϊ��");
            return null;
        }
        return groupName + "@conference." + connection.getServiceName();
    }

}
