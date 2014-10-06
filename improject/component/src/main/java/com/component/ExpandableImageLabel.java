package com.component;


import java.awt.*;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * һ���JLabel��Ȼ��������ͼƬ�����ǲ�������ߴ�仯����չ��������Խ��������
 * @author xly
 *
 */
public class ExpandableImageLabel extends JLabel {
    private static final long serialVersionUID = 1L;
    private String imageFile = null;
    public ExpandableImageLabel(String imageFile) {
        setImageFile(imageFile);
    }

    public boolean setImageFile(String imageFile){
        this.imageFile = imageFile;
        if (null == imageFile) return false;
        if (imageFile.isEmpty())return false;
        File file = new File(imageFile);
        if (!file.exists())return false;
        if (!file.isFile())return false;
        ImageIcon icon = new ImageIcon(imageFile);
        if (null == icon) return false;
        this.setIcon(icon);
        Dimension size = new Dimension(icon.getIconWidth(),icon.getIconHeight());
        this.setPreferredSize(size);
        return true;
    }

    public String getImageFile(){
        return imageFile;
    }


    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Image image = ((ImageIcon)this.getIcon()).getImage();
        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        super.paint(g);
    }
}
