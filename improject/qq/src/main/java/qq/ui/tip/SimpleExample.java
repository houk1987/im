package qq.ui.tip;



import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.RoundedBalloonStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by HK on 2014/10/16.
 */
public class SimpleExample {
    /**
     * Main method
     * @param args		command-line arguments (unused)
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Setup the application's window
                JFrame frame = new JFrame("Simple BalloonTip example");
               // frame.setIconImage(new ImageIcon(SimpleExample.class.getResource("/net/java/balloontip/images/frameicon.png")).getImage());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Setup the content pane
                JPanel contentPane = new JPanel();
                contentPane.setLayout(new GridBagLayout());
                frame.setContentPane(contentPane);

                // Add a button
                final JButton button = new JButton("Show balloon tip");
                contentPane.add(button, new GridBagConstraints(0,0,1,1,1,1, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, new Insets(0,0,60,160), 0, 0));

                /*** Balloon tip creation - START ***/

                // Create the look for our balloon tip
                RoundedBalloonStyle style = new RoundedBalloonStyle(2,2,Color.WHITE, Color.BLUE);

                // Now construct the balloon tip
                final BalloonTip balloonTip = new BalloonTip(
                        button,
                        new JLabel("«Î ‰»Î√‹¬Î‘Ÿµ«¬º"),
                        style,
                        BalloonTip.Orientation.LEFT_BELOW,
                        BalloonTip.AttachLocation.ALIGNED,
                        5, 10,
                        false
                );

                // Add a close button that hides the balloon tip, rather than permanently close it
               balloonTip.setCloseButton(null, false);

                /*** Balloon tip creation - END ***/

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        balloonTip.setVisible(true);
                    }
                });

                // Dislay the window
                frame.pack();
                frame.setSize(320, 240);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
