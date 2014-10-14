package tree;

import com.component.rosterTree.ContactGroup;
import com.component.rosterTree.renderer.ContactTableCellRenderer;

import java.awt.*;

/**
 * Created by lenovo on 2014/10/11.
 */
public class YhContactGroup extends ContactGroup {

    public YhContactGroup(String groupName, Color titleColor, Font titleFont) {
        super(groupName, titleColor, titleFont);
    }

    @Override
    protected void initContactTableCellRenderer() {
        contactTableCellRenderer = new ContactTableCellRenderer(ContactTableCellRenderer.ContactTableCellRendererType.yh);
    }

    @Override
    protected void setBackGround() {
        contactItemList.setBackground(Color.WHITE);
        //getTitlePane().setBackground(new Color(119,36,111));
    }
}
