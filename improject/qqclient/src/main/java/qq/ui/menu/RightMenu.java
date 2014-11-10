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

    public static void main(String[] args) {
        final JFrame frame = new JFrame();
        frame.setSize(600, 500);
        frame.setTitle("ImageMenu");
        ImageIcon icon = ImageUtils.getInstance("menu/").getImageIcon("rightMenuBg.png");
        final JPopupMenu menu = new JPopupMenu(
                );

        menu.add(new JMenuItem(ImageUtils.getInstance("menu/").getImageIcon("sendMenu.png")));
        menu.add(new JMenuItem(ImageUtils.getInstance("menu/").getImageIcon("sendMenu.png")));
        menu.add(new JMenuItem("���͵����ʼ�"));
        menu.addSeparator();

        menu.add(new JMenuItem("�鿴����"));
        menu.add(new JMenuItem("��Ϣ��¼"));
        menu.addSeparator();
        menu.add(new JMenuItem("�Ự�ö�"));
        menu.add(new JMenuItem("�ӻỰ�б��Ƴ�"));
        menu.add(new JMenuItem("����Ȩ��"));
        menu.add(new JMenuItem("�޸ı�ע����"));
        menu.add(new JMenuItem("�ƶ���ϵ����"));
        menu.add(new JMenuItem("ɾ������"));

        menu.add(new JMenuItem("�ٱ����û�"));
        menu.add(new JMenuItem("���ѹ���"));
        menu.addSeparator();
        menu.add(new JMenuItem("��Ա��ݹ���"));
        menu.add(new JMenuItem("����QQ�ռ�"));
        JLabel label = new JLabel("Right click me to show image popup menu.");
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    menu.show(frame, e.getPoint().x, e.getPoint().y);
                }
            }
        });
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
