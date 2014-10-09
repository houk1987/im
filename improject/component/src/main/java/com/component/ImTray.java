package com.component;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/10/9.
 */
public class ImTray {
    private TrayIcon trayIcon; // 托盘图标
    private SystemTray tray = SystemTray.getSystemTray(); // 操作系统托盘的实例

    public ImTray(Image icon,String trayTooltip) {
        trayIcon = new TrayIcon(icon,trayTooltip);
        addSystemTray();
    }

    public ImTray(ImageIcon icon,String trayTooltip,PopupMenu popupMenu) {
        trayIcon = new TrayIcon(icon.getImage(),trayTooltip,popupMenu);
        addSystemTray();
    }

    private void addSystemTray(){
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void setTrayIcon(Image icon){
        trayIcon.setImage(icon);
    }
}
