package com.comunication.roster;

import com.comunication.connection.ConnectionManager;
import com.san30.pub.tools.SanHttpClient;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

import java.util.*;

/**
 * Created by HK on 2014/10/6.
 */
public class RosterManager {
    private Roster roster;
    private XMPPConnection xmppConnection;

    public RosterManager(){

            xmppConnection = ConnectionManager.getCommunicationConnection();
            this.roster = xmppConnection.getRoster();


    }

    /**
     * ��ȡ���еĺ���
     * @return
     */
    public List<RosterEntry> getRosters() {
        roster.reload();
        List<RosterEntry> EntriesList = new ArrayList<>();

        Collection<RosterEntry> rosterEntry = roster.getEntries();
        System.out.println(rosterEntry.size());
        Iterator<RosterEntry> i = rosterEntry.iterator();
        while (i.hasNext())
            EntriesList.add(i.next());
        return EntriesList;
    }

    /**
     * ��ȡ����������Ϣ
     * @param jid
     * @return
     */
    public  RosterEntry getRosterEntry(String jid) {
        if (jid == null) return null;
        roster.reload();
        return roster.getEntry(jid+"@"+xmppConnection.getServiceName());
    }

    /**
     * ���ͺ�������
     * @param fromUserName  ���ͷ����˺�
     * @param friendUserName ���ܷ����˺�
     * @return
     */
    public boolean sendFriendApply(String fromUserName,String friendUserName){
        try {
            if(friendUserName!=null) {
                HashMap<String, String> paramMap = new HashMap<>();
                paramMap.put("jid", fromUserName);
                paramMap.put("type", "validateAccount");
                paramMap.put("targetAccount", friendUserName);
                String url = "http://" + xmppConnection.getHost() + ":" + 9090 + "/plugins/udpserver/addcontact";
                String rs = SanHttpClient.getDataAsString(url, paramMap);
                return Boolean.valueOf(rs);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return false;
    }

    public void delRoster(String userJid) throws Exception{
        try {
            RosterEntry entry = roster.getEntry(userJid);
            roster.removeEntry(entry);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("ɾ������ʧ��!");
        }
    }

    /**
     * ��ȡ���ѵ�״̬
     * @param userName
     * @return
     */
    public Presence getFriendPresence(String userName){
        return roster.getPresence(userName);
    }
}

