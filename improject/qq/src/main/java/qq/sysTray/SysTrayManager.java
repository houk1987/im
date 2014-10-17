package qq.sysTray;

import com.component.ImTray;
import com.component.ImageUtils;
import qq.login.LoginDialog;
import qq.ui.window.PubDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by HK on 2014/10/16.
 */
public class SysTrayManager {
    public static SysTrayManager sysTrayManager;
    private ImTray imTray; //QQ ϵͳ����
    private PubDialog clickShowDialog;

    public static SysTrayManager getInstance() {
        if(sysTrayManager == null){
            sysTrayManager = new SysTrayManager();
        }
        return sysTrayManager;
    }

    private SysTrayManager() {
        clickShowDialog = LoginDialog.getInstance();
    }

    /**
     * ��ʼ��ϵͳ����
     */
    public void initImTray(){
        if(imTray == null){
            imTray = new ImTray(ImageUtils.getInstance("common/").getImageIcon("TitleIcon.png").getImage(),"QQ");
            imTray.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(!clickShowDialog.isVisible()){
                        clickShowDialog.setVisible(true);
                    }
                    clickShowDialog.requestFocus();
                }
            });
        }
    }

    public void setClickShowDialog(PubDialog clickShowDialog) {
        this.clickShowDialog = clickShowDialog;
    }
}
