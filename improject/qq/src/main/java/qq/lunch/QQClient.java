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
    private ConnectionManager connectionManager;   //链接管理
    private RosterManager rosterManager;     //底层花名册管理类  获取openFire 服务器上的好友的名册
    private FriendsManager friendsManager;  //好友管理类
    private PresenceManager presenceManager; //状态管理类
    private ChatManager chatManager;
    private XMPPConnection xmppConnection;  //链接对象
    private LoginDialog loginDialog;  //登陆窗口
    private MainDialog mainDialog;   //主界面
    private String loginUserName;   //登陆账号  123
    private String loginUserNameWithDomain; //带域名的登陆账号  例如:123@qq.com
    private String nickName;


    public static QQClient getInstance() {
        return qqClient;
    }

    /**
     * 初始化QQ客户端
     */
    private QQClient() {
        connectionManager = new ConnectionManager();  //创建链接管理对象
        xmppConnection = connectionManager.createXmppConnection(ConfigurationRes.getHostName(),ConfigurationRes.getPort(),ConfigurationRes.getDomain());  //创建xmppConnection 链接对象
        rosterManager = new RosterManager(xmppConnection);
        friendsManager = new FriendsManager(rosterManager);
        presenceManager = new PresenceManager(rosterManager);
        chatManager = new ChatManager(xmppConnection);
    }

    /**
     * 启动QQClient
     * @throws XMPPException  如果网络不通，或者服务器未开，则会出现报XMPPException异常
     */
    public void lunchStartQQClient() throws XMPPException {
       connectionManager.openXMPPException(xmppConnection);   //打开xmppConnection
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
        connectionManager.closeXMPPConnection(xmppConnection);  //关闭xmppConnection 链接
        System.exit(0);
    }

    /**
     * 登陆xmppConnection
     * @param userName  不带域名的账号
     * @param password  密码
     * @throws XMPPException
     */
    public void loginQQClient(String userName,String password) throws XMPPException {
        connectionManager.login(xmppConnection,userName,password);  //登陆openFire服务器
        xmppConnection.addPacketListener(new QqPacketLister(),new PacketTypeFilter(Message.class));  //添加消息监听
        xmppConnection.getRoster().addRosterListener(new QqRosterListener());//添加状态监听
        loginUserName = userName;  //不带域名的账号
        loginUserNameWithDomain = loginUserName + "@"+xmppConnection.getServiceName();  //设置带域名的账号
        nickName = rosterManager.getRosterEntry(loginUserName).getName();  //昵称
        if(loginDialog!=null&&loginDialog.isVisible()){ //如果登陆界面不为空，且显示状态
            loginDialog.dispose();  //关闭登陆界面
            loginDialog=null;
        }
        mainDialog = MainDialog.getInstance();  //初始化主界面
        mainDialog.setVisible(true);           //显示主界面
    }

    /**
     * 登出客户端
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
