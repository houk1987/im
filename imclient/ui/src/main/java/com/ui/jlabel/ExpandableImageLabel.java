package com.ui.jlabel;


import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * һ���JLabel��Ȼ��������ͼƬ�����ǲ�������ߴ�仯����չ��������Խ��������
 * @author xly
 *
 */
public class ExpandableImageLabel extends JLabel {
	private static final long serialVersionUID = 1L;

    private ImageIcon imageIcon;
	public ExpandableImageLabel(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon){
        this.imageIcon = imageIcon;
    }
	

	

	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = null;
		if( g instanceof Graphics2D)
			 g2d = (Graphics2D) g;
		else 
			return;
        if(imageIcon == null)return;
		g2d.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
	    super.paint(g);
	}
}
