package qq.ui.tip;



import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.RoundedBalloonStyle;

import javax.swing.*;
import java.awt.*;

/**
 * 提示工具
 * 主要是用于显示在输入框旁边
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
        balloonTip.setCloseButton(null, false);   //不显示关闭按钮
        return balloonTip;
    }
}
