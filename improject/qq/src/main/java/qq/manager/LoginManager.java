package qq.manager;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.smackservice.SmackConnection;

/**
 * Created by lenovo on 2014/10/10.
 */
public class LoginManager {

    private static LoginManager loginManager;

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
            throw new Exception("∑˛ŒÒ∆˜¡¥Ω” ß∞‹");
        }

        try {
            SmackConnection.getInstance().login(account,password);
        } catch (XMPPException e) {
            throw new Exception("’À∫≈ªÚ√‹¬Î¥ÌŒÛ");
        }

    }

}
