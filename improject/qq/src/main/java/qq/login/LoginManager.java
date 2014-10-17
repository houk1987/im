package qq.login;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;
import org.smackservice.RosterManager;
import org.smackservice.SmackConnection;

/**
 * Created by lenovo on 2014/10/10.
 */
public class LoginManager {

    private static LoginManager loginManager;
    private String loginAccount; //µÇÂ½µÄÕËºÅ
    private String loginAccountUserName; //µÇÂ½µÄÕËºÅµÄÃû³Æ

    public static LoginManager getInstance() {
        if (loginManager == null){
            loginManager = new LoginManager();
        }
        return loginManager;
    }

    public void loginClient(String account,String password)throws Exception{
        try {
            SmackConnection.getInstance().connect();
        } catch (XMPPException e) {
            throw new Exception("·þÎñÆ÷Á´½ÓÊ§°Ü");
        }

        try {
            SmackConnection.getInstance().login(account,password);
        } catch (XMPPException e) {
            throw new Exception("ÕËºÅ»òÃÜÂë´íÎó");
        }
        loginAccount = account+"@"+SmackConnection.getInstance().getServiceName();
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public String getLoginAccountUserName() {
        RosterEntry rosterEntry = RosterManager.getRosterEntry(loginAccount);
        if(rosterEntry == null){
            loginAccountUserName = loginAccount;
        }else{
            loginAccountUserName = rosterEntry.getName();
        }
        return loginAccountUserName;
    }
}
