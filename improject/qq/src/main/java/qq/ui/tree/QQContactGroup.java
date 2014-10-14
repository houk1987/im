package qq.ui.tree;

import com.component.rosterTree.ContactGroup;
import com.component.rosterTree.renderer.ContactTableCellRenderer;

import java.awt.*;

/**
 * Created by lenovo on 2014/10/10.
 */
public class QQContactGroup extends ContactGroup {

    private final static Font titleFont = new Font("ËÎÌו",Font.PLAIN,10);


    public QQContactGroup(String groupName) {
        super(groupName,Color.BLACK,titleFont);
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
