package qq.friends;

import qq.ui.window.PubFrame;

import java.awt.*;

/**
 * Created by lenovo on 2014/10/17.
 */
public class AddFriendFrame extends PubFrame{

    private AddFriendFrameContentPane addFriendFrameContentPane;
    private String jid;

    public AddFriendFrame(String jid) throws HeadlessException {
        this.jid = jid;
        addFriendFrameContentPane = new AddFriendFrameContentPane(this);
        setContentPane(addFriendFrameContentPane);
        setSize(addFriendFrameContentPane.getSize());
        setLocationRelativeTo(null);
    }

    public String getJid() {
        return jid;
    }
}
