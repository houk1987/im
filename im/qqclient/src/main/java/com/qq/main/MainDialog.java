package com.qq.main;




import com.qq.main.tree.QQContactTree;
import com.ui.tools.Tools;
import com.ui.window.PubDialog;

import java.awt.*;

/**
 * Created by lenovo on 2014/9/17.
 */
public class MainDialog extends PubDialog {
    private static MainDialog mainDialog;
    private MainDialogContentPane mainDialogContentPane;

    public static MainDialog getInstance() {
        if(mainDialog == null){
            mainDialog = new MainDialog();
        }
        return mainDialog;
    }

    private MainDialog() throws HeadlessException {
        mainDialogContentPane = new MainDialogContentPane(this);
        setContentPane(mainDialogContentPane);    //�����������
        setSize(mainDialogContentPane.getSize());//��СΪ��������С
        setSize(mainDialogContentPane.getWidth(), mainDialogContentPane.getHeight());
        Tools.setWindowLocationOnScreamRight(this); //������ʾ
    }


    public QQContactTree getFriendsTree(){
        return mainDialogContentPane.getFriendsTree();
    }
}
