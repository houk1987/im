package qq.session;

import com.component.ButtonFactory;

import javax.swing.*;

/**
 * ��½�����а�ť������
 * �ṩ��½�����а�ť����
 * Created by lenovo on 2014/10/16.
 */
public class SessionFrameButtonFactory extends ButtonFactory{

    protected SessionFrameButtonFactory() {
        super("session/button/");
    }

    public JButton createMinWindowButton() {
        return createButton("minWindow.png");
    }

    public JButton createMaxWindowButton(){
        return createButton("maxWindow.png");
    }

    public JButton createCloseWindowButton(){
        return createButton("closeWindow.png");
    }

    public JButton createSendButton(){
        return createButton("send.png");
    }

    public JButton createCloseSessionFrameButton() {
        return createButton("closeSessionFrame.png");
    }
}
