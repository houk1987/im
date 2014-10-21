package qq.friends;

import com.component.ImageUtils;
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
        setIconImage(ImageUtils.getInstance("common/").getImageIcon("icon.png").getImage());
    }

    public String getJid() {
        return jid;
    }

    public static void main(String[] args) {
        new AddFriendFrame("1").setVisible(true);
    }
}
