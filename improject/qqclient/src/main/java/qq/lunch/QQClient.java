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
    private FriendsManager friendsManager;  //好友管理类
    private PresenceManager presenceManager; //状态管理类
    private LoginDialog loginDialog;  //登陆窗口
    private MainDialog mainDialog;   //主界面
    private String nickName;


    public static QQClient getInstance() {
        return qqClient;
    }

    /**
     * 初始化QQ客户端
     */
    private QQClient() {

    }

    /**
     * 启动QQClient
     * @throws XMPPException  如果网络不通，或者服务器未开，则会出现报XMPPException异常
     */
    public void lunchStartQQClient() throws XMPPException {
       ConnectionManager.createConnection();
       loginDialog = new LoginDialog();  //实例化登陆窗口
       loginDialog.setVisible(true); //显示登陆窗口
       SysTrayManager.getInstance().initImTray();  //初始化右下系统托盘
    }

    /**
     * 关闭QQClient
     */
    public void closeQQClient(){
        if (loginDialog!=null){    //关闭登陆窗口
            loginDialog.dispose();
            loginDialog = null;
        }else if(mainDialog!=null){  //关闭主窗口
            mainDialog.dispose();
            mainDialog = null;
        }
        ConnectionManager.closeConnection();
        System.exit(0);
    }

    /**
     * 登陆xmppConnection
     * @param userName  不带域名的账号
     * @param password  密码
     * @throws XMPPException
     */
    public void loginQQClient(String userName,String password) throws XMPPException {
        ConnectionManager.login(userName,password);  //登陆openFire服务器
        new QqPacketLister();
        ConnectionManager.getConnection().getRoster().addRosterListener(new QqRosterListener());//添加状态监听
        new ConnectionHandle();
        rosterManager = new RosterManager(ConnectionManager.getConnection().getRoster());
        friendsManager = new FriendsManager(rosterManager);
        presenceManager = new PresenceManager(rosterManager);
        if(rosterManager.getRosterEntry(userName)!=null){
            nickName = rosterManager.getRosterEntry(userName).getName();  //昵称
        }else{
            nickName = getLoginUserName();
        }
        if(loginDialog!=null&&loginDialog.isVisible()){ //如果登陆界面不为空，且显示状态
            loginDialog.dispose();  //关闭登陆界面
            loginDialog=null;
        }
        mainDialog = MainDialog.getInstance();  //初始化主界面
        mainDialog.setVisible(true);           //显示主界面

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
