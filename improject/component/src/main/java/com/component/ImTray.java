package com.component;

import java.awt.*;

/**
 * Created by lenovo on 2014/10/9.
 */
public class ImTray extends TrayIcon{
    private SystemTray tray = SystemTray.getSystemTray(); // ����ϵͳ���̵�ʵ��

    public ImTray(Image icon,String trayTooltip) {
        super(icon,trayTooltip);
        addSystemTray();
    }

    public ImTray(Image icon,String trayTooltip,PopupMenu popupMenu) {
        super(icon,trayTooltip,popupMenu);
        addSystemTray();
    }

    private void addSystemTray(){
        try {
            tray.add(this);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
