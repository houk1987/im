package qq.ui.menu;

import com.resource.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Created by lenovo on 2014/10/21.
 */
public class RightMenu extends JPopupMenu  {
    private Font font             = new Font("Dialog", Font.BOLD,
            13);
    private ImageIcon         imageIcon        = null;

    public RightMenu(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public RightMenu(String text) {
        this.imageIcon = createImage(text);
    }

    private ImageIcon createImage(String text) {
        BufferedImage bi = new BufferedImage(30, 1000,
                BufferedImage.TYPE_INT_ARGB);
        ImageIcon image = new ImageIcon(bi);
        Graphics2D g2d = bi.createGraphics();

        GradientPaint paint = new GradientPaint(0, 0, Color.yellow, 30, 10,
                Color.red, true);
        g2d.setPaint(paint);

        g2d.fillRect(0, 0, bi.getWidth(), bi.getHeight());

        AffineTransform at = new AffineTransform();
        at.rotate(-Math.PI / 2);

        g2d.setTransform(at);
        g2d.setColor(Color.white);
        g2d.setFont(font);
        g2d.drawString(text, -180, bi.getWidth() / 2);

        return image;
    }

    @Override
    public Insets getInsets() {
        Insets insets = (Insets) super.getInsets().clone();
        insets.left += imageIcon.getIconWidth();
        return insets;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (imageIcon != null) {
            Insets insets = getInsets();
            g.drawImage(imageIcon.getImage(),
                    insets.left - imageIcon.getIconWidth(), insets.top, null);
        }
    }
}
