package qq.manager;

import com.component.ImTray;
import com.component.ImageUtils;
import com.component.PicUtil;
import qq.ui.login.LoginDialog;
import qq.ui.main.MainDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by lenovo on 2014/10/10.
 */
public class QQManager {

    private static QQManager qqManager;
    private ImTray imTray; //QQ œµÕ≥Õ–≈Ã
    private LoginDialog loginDialog;
    private MainDialog mainDialog;

    public static QQManager getInstance() {
        if(qqManager == null){
            qqManager = new QQManager();
        }
        return qqManager;
    }

    private QQManager() {
        imTray =  new ImTray(ImageUtils.getImageIcon("TitleIcon.png").getImage(),"QQ");
        addImTrayHandel();
    }

    private void addImTrayHandel(){
        ImTrayHandel imTrayHandel = new ImTrayHandel();
        imTray.addMouseListener(imTrayHandel);
    }

    public void createAndShowLoginDialog(){
        loginDialog = LoginDialog.getInstance();
        if(!loginDialog.isVisible()) loginDialog.setVisible(true);
        loginDialog.requestFocus();
    }

    public void createAndShowMainDialog(){
        mainDialog = MainDialog.getInstance();
        if(!mainDialog.isVisible()) mainDialog.setVisible(true);
    }


    class ImTrayHandel extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            if(mainDialog != null && loginDialog == null){
                createAndShowMainDialog();
            }else if(mainDialog == null && loginDialog != null){
                createAndShowLoginDialog();
            }
        }
    }
}
