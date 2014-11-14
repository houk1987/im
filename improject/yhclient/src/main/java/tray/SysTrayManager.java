package tray;
import com.ui.tray.ImTray;
import images.TrayImageFactory;

import javax.swing.*;
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
        initImTray();
    }

    /**
     * ��ʼ��ϵͳ����
     */
    public void initImTray(){
        if(imTray == null){
            imTray = new ImTray(TrayImageFactory.createBeforeLogin().getImage(),"YM");
            imTray.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

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
                System.exit(0);
            }
        });
    }

    public void setImTrayIcon(Image icon){
        imTray.setImage(icon);
    }
}
