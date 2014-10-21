package qq.sysTray;

import com.component.ImTray;
import com.component.ImageUtils;
import qq.login.LoginDialog;
import qq.lunch.QQClient;
import qq.ui.window.PubDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by HK on 2014/10/16.
 */
public class SysTrayManager {
    public static SysTrayManager sysTrayManager;
    private ImTray imTray; //QQ 系统托盘

    public static SysTrayManager getInstance() {
        if(sysTrayManager == null){
            sysTrayManager = new SysTrayManager();
        }
        return sysTrayManager;
    }

    private SysTrayManager() {

    }

    /**
     * 初始化系统托盘
     */
    public void initImTray(){
        if(imTray == null){
            imTray = new ImTray(ImageUtils.getInstance("common/").getImageIcon("TitleIcon.png").getImage(),"QQ");
            imTray.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    PubDialog dialog = QQClient.getInstance().getLoginDialog();
                    if(dialog==null){
                        dialog = QQClient.getInstance().getMainDialog();
                    }
                    if(dialog!=null){
                        if(!dialog.isVisible()){
                            dialog.setVisible(true);
                        }
                        dialog.requestFocus();
                    }
                }
            });
        }
    }
}
