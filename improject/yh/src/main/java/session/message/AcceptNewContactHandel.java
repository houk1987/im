package session.message;


import org.jivesoftware.smack.packet.Message;

/**
 * Created by HK on 2014/9/8.
 */
public class AcceptNewContactHandel {


    public void handel(Message message) {
        String subject = message.getSubject();
        if("∫√”—…Í’à".equals(subject)){
           // AcceptNewContactDialog acceptNewContactDialog = new AcceptNewContactDialog(message.getFrom());
           // acceptNewContactDialog.showNotifyWindow();
        }
    }
}
