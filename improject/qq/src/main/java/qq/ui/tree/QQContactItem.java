package qq.ui.tree;

import com.component.rosterTree.ContactItem;
import session.SessionFrame;

/**
 * Created by lenovo on 2014/10/8.
 */
public class QQContactItem extends ContactItem {

    @Override
    public void click(){
        SessionFrame.CreateAndShowSessionFrame(this);
    }
}
