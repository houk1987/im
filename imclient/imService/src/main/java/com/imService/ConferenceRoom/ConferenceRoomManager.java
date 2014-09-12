package com.imService.ConferenceRoom;

import com.imService.connection.ImConnection;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.FormField;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.muc.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by a on 2014/7/24.
 */
public class ConferenceRoomManager {

    public static MultiUserChat createConferenceRoom(ImConnection talkConnection, String roomName, List<String> memberList) throws XMPPException {
        try {
            MultiUserChat muc = getMultiUserChat(talkConnection, roomName);
            if (muc != null) {
                // ����������
                muc.join(talkConnection.getXMPPConnection().getUser());
//                muc.create(roomName); // roomName���������
                // ��������ҵ����ñ�
                Form form = muc.getConfigurationForm();
                // ����ԭʼ������һ��Ҫ�ύ���±���
                Form submitForm = form.createAnswerForm();
                // ��Ҫ�ύ�ı����Ĭ�ϴ�
                for (Iterator<FormField> fields = form.getFields(); fields
                        .hasNext(); ) {
                    FormField field = (FormField) fields.next();
                    if (!FormField.TYPE_HIDDEN.equals(field.getType())
                            && field.getVariable() != null) {
                        // ����Ĭ��ֵ��Ϊ��
                        submitForm.setDefaultAnswer(field.getVariable());
                    }
                }
                // ���������ҵ���ӵ����
                List<String> owners = new ArrayList<String>();
                owners.add(talkConnection.getXMPPConnection().getUser());// �û�JID
                submitForm.setAnswer("muc#roomconfig_roomowners", owners);
                // �����������ǳ־������ң�����Ҫ����������
                submitForm.setAnswer("muc#roomconfig_persistentroom", true);
                // ������Գ�Ա����
                submitForm.setAnswer("muc#roomconfig_membersonly", false);
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
                //���Ⱥ��Ա
                for (String tempMember : memberList) {
                    muc.grantMembership(tempMember);
                }
                // ��������ɵı�����Ĭ��ֵ����������������������
                muc.sendConfigurationForm(submitForm);

            }
            return muc;
        } catch (XMPPException e) {
            e.printStackTrace();
            throw new XMPPException("");
        }
    }

    /**
     * �޸Ļ�����
     * ��ǰ�ú���ֻ���޸Ļ��������ƺͳ�Ա
     *
     * @param roomName
     * @param memberList
     */
    public static boolean modifyRoom(ImConnection talkConnection, String roomName, String newRoomName, List<String> memberList) {
        try {
            MultiUserChat muc = getMultiUserChat(talkConnection, roomName);
            if (muc != null) {
                muc.join(talkConnection.getXMPPConnection().getUser());
                // ��������ҵ����ñ�
                Form form = muc.getConfigurationForm();
                // ����ԭʼ������һ��Ҫ�ύ���±���
                Form submitForm = form.createAnswerForm();
                submitForm.setAnswer("muc#roomconfig_roomname", newRoomName); //���÷����������
                //form.setAnswer();
                //��ɾ�����Ե�Ⱥ��Ա
                List<Affiliate> delMembers = (List<Affiliate>)muc.getMembers();
                if(delMembers!=null && delMembers.size()>0){
                    for(Affiliate affiliate : delMembers){
                        muc.revokeMembership(affiliate.getJid());
                    }
                }

                if(memberList!= null && memberList.size()>0){
                    for (String member: memberList){
                        muc.grantMembership(member);
                    }
                }

                muc.sendConfigurationForm(submitForm);
            }
        } catch (XMPPException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * ɾ��������
     *
     * @param roomName       ����������
     * @param talkConnection ���������Ӷ���
     * @return true ɾ���ɹ�  false ɾ��ʧ��
     */
    public static boolean deleteRoom(String roomName, ImConnection talkConnection) {
        try {
            MultiUserChat muc = getMultiUserChat(talkConnection, roomName);
            if (muc != null) {
                muc.join(talkConnection.getXMPPConnection().getUser());
                muc.destroy("test", null);
                return true;
            }
        } catch (XMPPException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static MultiUserChat joinRoom(ImConnection talkConnection, String roomName, String user) {
        try {
            // ʹ��XMPPConnection����һ��MultiUserChat����
            MultiUserChat muc = getMultiUserChat(talkConnection, roomName);
            // �����ҷ��񽫻����Ҫ���ܵ���ʷ��¼����
            DiscussionHistory history = new DiscussionHistory();
            history.setMaxStanzas(0);
            // �û�����������
            muc.join(user);
            System.out.println("�����Ҽ���ɹ�........");
            return muc;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("�����Ҽ���ʧ��........");
            return null;
        }
    }

    /**
     * ��ȡ�����������л�����
     *
     * @return
     * @throws org.jivesoftware.smack.XMPPException
     */
    public static List<FriendRooms> getConferenceRoom(ImConnection talkConnection) throws XMPPException {
        if (talkConnection == null) return null;
        XMPPConnection connection = talkConnection.getXMPPConnection();
        if (connection == null) return null;
        List<FriendRooms> list = new ArrayList<FriendRooms>();
        new ServiceDiscoveryManager(connection);
        if (!MultiUserChat.getHostedRooms(connection,
                connection.getServiceName()).isEmpty()) {
            for (HostedRoom k : MultiUserChat.getHostedRooms(connection,
                    connection.getServiceName())) {

                for (HostedRoom j : MultiUserChat.getHostedRooms(connection,
                        k.getJid())) {
                    RoomInfo info2 = MultiUserChat.getRoomInfo(connection,
                            j.getJid());
                    if (j.getJid().indexOf("@") > 0) {
                        FriendRooms friendrooms = new FriendRooms();
                        friendrooms.setName(j.getName());//�����ҵ�����
                        friendrooms.setJid(j.getJid());//������JID
                        friendrooms.setOccupants(info2.getOccupantsCount());//��������ռ��������
                        friendrooms.setDescription(info2.getDescription());//�����ҵ�����
                        friendrooms.setSubject(info2.getSubject());//�����ҵ�����
                        list.add(friendrooms);
                    }
                }
            }
        }
        return list;
    }

    private static MultiUserChat getMultiUserChat(ImConnection talkConnection, String roomName) throws XMPPException {
        if (talkConnection == null) return null;
        XMPPConnection connection = talkConnection.getXMPPConnection();
        if (connection == null) return null;
        MultiUserChat muc = new MultiUserChat(connection, roomName + "@conference." + connection.getServiceName());
        return muc;
    }
}
