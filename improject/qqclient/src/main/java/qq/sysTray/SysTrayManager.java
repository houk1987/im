package qq.sysTray;


import com.ui.tray.ImTray;
import qq.images.CommonImagesFactory;
import qq.lunch.QQClient;
import qq.ui.window.PubDialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by HK on 2014/10/16.
 */
public class SysTrayManager {
    public static SysTrayManager sysTrayManager;
    private ImTray imTray; //QQ ϵͳ����

    public static SysTrayManager getInstance() {
        if(sysTrayManager == null){
            sysTrayManager = new SysTrayManager();
        }
        return sysTrayManager;
    }

    private SysTrayManager() {

    }

    /**
     * ��ʼ��ϵͳ����
     */
    public void initImTray(){
        if(imTray == null){
            imTray = new ImTray(CommonImagesFactory.getInstance().createTitleIcon().getImage(),"QQ");
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

        //��Ӳ˵�
        PopupMenu popupMenu = new PopupMenu();
        MenuItem menuItem = new MenuItem("�˳�");
        popupMenu.add(menuItem);
        imTray.setPopupMenu(popupMenu);  //�������̲˵�
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QQClient.getInstance().closeQQClient();
            }
        });
    }
}
