package qq.ui.tip;



import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.RoundedBalloonStyle;

import javax.swing.*;
import java.awt.*;

/**
 * ��ʾ����
 * ��Ҫ��������ʾ��������Ա�
 * Created by HK on 2014/10/16.
 */
public class ToolTip {
    private final static RoundedBalloonStyle style = new RoundedBalloonStyle(2,2, Color.WHITE, Color.BLUE);


    public static BalloonTip showBalloonTip(JComponent component,String tipText){
        final BalloonTip balloonTip = new BalloonTip(
                component,
                new JLabel(tipText),
                style,
                BalloonTip.Orientation.LEFT_BELOW,
                BalloonTip.AttachLocation.ALIGNED,
                5, 10,
                false
        );
        balloonTip.setCloseButton(null, false);   //����ʾ�رհ�ť
        return balloonTip;
    }
}
