package qq.friends;

import qq.images.CommonImagesFactory;
import qq.ui.window.PubFrame;

import java.awt.*;

/**
 * Created by lenovo on 2014/10/17.
 */
public class AddFriendFrame extends PubFrame{

    private AddFriendFrameContentPane addFriendFrameContentPane;
    private String jid;
    private String name;

    public AddFriendFrame(String jid,String name) throws HeadlessException {
        this.jid = jid;
        this.name = name;
        addFriendFrameContentPane = new AddFriendFrameContentPane(this);
        setContentPane(addFriendFrameContentPane);
        setSize(addFriendFrameContentPane.getSize());
        setLocationRelativeTo(null);
        setIconImage(CommonImagesFactory.getInstance().createIcon().getImage());
    }

    public String getJid() {
        return jid;
    }

    public String getName() {
        return name;
    }
}
