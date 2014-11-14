package images;

import com.resource.ImageUtils;

import javax.swing.*;

/**
 * Created by lenovo on 2014/11/11.
 */
public class TrayImageFactory {
    private static TrayImageFactory trayImageFactory = new TrayImageFactory();
    private static ImageUtils imageUtils;

    static {
        imageUtils = new ImageUtils("tray");
    }

    public static TrayImageFactory getInstance() {
        return trayImageFactory;
    }

    private TrayImageFactory() {
    }

    public static ImageIcon createBeforeLogin() {
        return imageUtils.getImageIcon("offline.gif");
    }

    public static ImageIcon createAfterLogin() {
        return imageUtils.getImageIcon("online.gif");
    }
}
