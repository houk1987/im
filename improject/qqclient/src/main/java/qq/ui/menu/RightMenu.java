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
        menu.add(new JMenuItem("发送电子邮件"));
        menu.addSeparator();

        menu.add(new JMenuItem("查看资料"));
        menu.add(new JMenuItem("消息记录"));
        menu.addSeparator();
        menu.add(new JMenuItem("会话置顶"));
        menu.add(new JMenuItem("从会话列表移除"));
        menu.add(new JMenuItem("设置权限"));
        menu.add(new JMenuItem("修改备注姓名"));
        menu.add(new JMenuItem("移动联系人至"));
        menu.add(new JMenuItem("删除好友"));

        menu.add(new JMenuItem("举报此用户"));
        menu.add(new JMenuItem("好友管理"));
        menu.addSeparator();
        menu.add(new JMenuItem("会员快捷功能"));
        menu.add(new JMenuItem("进入QQ空间"));
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
