package com.component;

import java.awt.*;

/**
 * Created by lenovo on 2014/10/9.
 */
public class ImTray extends TrayIcon{
    private SystemTray tray = SystemTray.getSystemTray(); // 操作系统托盘的实例

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
