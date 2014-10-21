package qq.lunch;

import com.resource.ConfigurationRes;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.smackservice.ChatManager;
import org.smackservice.ConnectionManager;
import org.smackservice.RosterManager;
import qq.friends.FriendsManager;
import qq.lister.QqPacketLister;
import qq.lister.QqRosterListener;
import qq.login.LoginDialog;
import qq.main.MainDialog;
import qq.manager.PresenceManager;
import qq.sysTray.SysTrayManager;

/**
 * Created by lenovo on 2014/10/21.
 */
public class QQClient {
    public final static QQClient qqClient = new QQClient();
    private ConnectionManager connectionManager;   //���ӹ���
    private RosterManager rosterManager;     //�ײ㻨���������  ��ȡopenFire �������ϵĺ��ѵ�����
    private FriendsManager friendsManager;  //���ѹ�����
    private PresenceManager presenceManager; //״̬������
    private ChatManager chatManager;
    private XMPPConnection xmppConnection;  //���Ӷ���
    private LoginDialog loginDialog;  //��½����
    private MainDialog mainDialog;   //������
    private String loginUserName;   //��½�˺�  123
    private String loginUserNameWithDomain; //�������ĵ�½�˺�  ����:123@qq.com
    private String nickName;


    public static QQClient getInstance() {
        return qqClient;
    }

    /**
     * ��ʼ��QQ�ͻ���
     */
    private QQClient() {
        connectionManager = new ConnectionManager();  //�������ӹ������
        xmppConnection = connectionManager.createXmppConnection(ConfigurationRes.getHostName(),ConfigurationRes.getPort(),ConfigurationRes.getDomain());  //����xmppConnection ���Ӷ���
        rosterManager = new RosterManager(xmppConnection);
        friendsManager = new FriendsManager(rosterManager);
        presenceManager = new PresenceManager(rosterManager);
        chatManager = new ChatManager(xmppConnection);
    }

    /**
     * ����QQClient
     * @throws XMPPException  ������粻ͨ�����߷�����δ���������ֱ�XMPPException�쳣
     */
    public void lunchStartQQClient() throws XMPPException {
       connectionManager.openXMPPException(xmppConnection);   //��xmppConnection
       loginDialog = new LoginDialog();  //ʵ������½����
       loginDialog.setVisible(true); //��ʾ��½����
       SysTrayManager.getInstance().initImTray();  //��ʼ������ϵͳ����
    }

    /**
     * �ر�QQClient
     */
    public void closeQQClient(){
        if (loginDialog!=null){    //�رյ�½����
            loginDialog.dispose();
            loginDialog = null;
        }else if(mainDialog!=null){  //�ر�������
            mainDialog.dispose();
            mainDialog = null;
        }
        connectionManager.closeXMPPConnection(xmppConnection);  //�ر�xmppConnection ����
        System.exit(0);
    }

    /**
     * ��½xmppConnection
     * @param userName  �����������˺�
     * @param password  ����
     * @throws XMPPException
     */
    public void loginQQClient(String userName,String password) throws XMPPException {
        connectionManager.login(xmppConnection,userName,password);  //��½openFire������
        xmppConnection.addPacketListener(new QqPacketLister(),new PacketTypeFilter(Message.class));  //�����Ϣ����
        xmppConnection.getRoster().addRosterListener(new QqRosterListener());//���״̬����
        loginUserName = userName;  //�����������˺�
        loginUserNameWithDomain = loginUserName + "@"+xmppConnection.getServiceName();  //���ô��������˺�
        nickName = rosterManager.getRosterEntry(loginUserName).getName();  //�ǳ�
        if(loginDialog!=null&&loginDialog.isVisible()){ //�����½���治Ϊ�գ�����ʾ״̬
            loginDialog.dispose();  //�رյ�½����
            loginDialog=null;
        }
        mainDialog = MainDialog.getInstance();  //��ʼ��������
        mainDialog.setVisible(true);           //��ʾ������
    }

    /**
     * �ǳ��ͻ���
     */
    public void loginOutQQClient(){

    }

    public void changeClientPresence(Packet packet){
        xmppConnection.sendPacket(packet);
    }

    public LoginDialog getLoginDialog() {
        return loginDialog;
    }

    public MainDialog getMainDialog() {
        return mainDialog;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public String getLoginUserNameWithDomain() {
        return loginUserNameWithDomain;
    }

    public String getNickName() {
        return nickName;
    }

    public FriendsManager getFriendsManager() {
        return friendsManager;
    }

    public String getDomain(){
        return xmppConnection.getServiceName();
    }

    public PresenceManager getPresenceManager() {
        return presenceManager;
    }

    public ChatManager getChatManager() {
        return chatManager;
    }
}
