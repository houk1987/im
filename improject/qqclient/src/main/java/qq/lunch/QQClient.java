package qq.lunch;

import com.comunication.connection.ConnectionManager;
import com.comunication.roster.RosterManager;
import org.jivesoftware.smack.XMPPException;
import qq.friends.FriendsManager;
import qq.lister.ConnectionHandle;
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
    private RosterManager rosterManager;
    private FriendsManager friendsManager;  //���ѹ�����
    private PresenceManager presenceManager; //״̬������
    private LoginDialog loginDialog;  //��½����
    private MainDialog mainDialog;   //������
    private String nickName;


    public static QQClient getInstance() {
        return qqClient;
    }

    /**
     * ��ʼ��QQ�ͻ���
     */
    private QQClient() {

    }

    /**
     * ����QQClient
     * @throws XMPPException  ������粻ͨ�����߷�����δ���������ֱ�XMPPException�쳣
     */
    public void lunchStartQQClient() throws XMPPException {
       ConnectionManager.createConnection();
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
        ConnectionManager.closeConnection();
        System.exit(0);
    }

    /**
     * ��½xmppConnection
     * @param userName  �����������˺�
     * @param password  ����
     * @throws XMPPException
     */
    public void loginQQClient(String userName,String password) throws XMPPException {
        ConnectionManager.login(userName,password);  //��½openFire������
        new QqPacketLister();
        ConnectionManager.getConnection().getRoster().addRosterListener(new QqRosterListener());//���״̬����
        new ConnectionHandle();
        rosterManager = new RosterManager(ConnectionManager.getConnection().getRoster());
        friendsManager = new FriendsManager(rosterManager);
        presenceManager = new PresenceManager(rosterManager);
        if(rosterManager.getRosterEntry(userName)!=null){
            nickName = rosterManager.getRosterEntry(userName).getName();  //�ǳ�
        }else{
            nickName = getLoginUserName();
        }
        if(loginDialog!=null&&loginDialog.isVisible()){ //�����½���治Ϊ�գ�����ʾ״̬
            loginDialog.dispose();  //�رյ�½����
            loginDialog=null;
        }
        mainDialog = MainDialog.getInstance();  //��ʼ��������
        mainDialog.setVisible(true);           //��ʾ������

    }

    public LoginDialog getLoginDialog() {
        return loginDialog;
    }

    public MainDialog getMainDialog() {
        return mainDialog;
    }

    public String getLoginUserName() {
        return ConnectionManager.getConnection().getUserName();
    }

    public String getLoginUserNameWithDomain() {
        return ConnectionManager.getConnection().getFullUserName();
    }

    public String getNickName() {
        return nickName;
    }

    public FriendsManager getFriendsManager() {
        return friendsManager;
    }

    public PresenceManager getPresenceManager() {
        return presenceManager;
    }
}
