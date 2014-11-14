package qq.main.tree;
import com.ui.rosterTree.ContactGroup;
import com.ui.rosterTree.renderer.ContactTableCellRenderer;

import java.awt.*;

/**
 * Created by lenovo on 2014/10/10.
 */
public class QQContactGroup extends ContactGroup {

    public QQContactGroup(String groupName) {
        super(groupName,Color.BLACK,new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,12));
    }

    @Override
    protected void initContactTableCellRenderer() {
        contactTableCellRenderer = new ContactTableCellRenderer(ContactTableCellRenderer.ContactTableCellRendererType.qq);
    }

    @Override
    protected void setBackGround() {
        contactItemList.setBackground(new Color(231,243,250));
        getTitlePane().setBackground(new Color(231,243,250));
    }
}
