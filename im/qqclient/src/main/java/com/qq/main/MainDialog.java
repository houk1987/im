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
        setContentPane(mainDialogContentPane);    //设置内容面板
        setSize(mainDialogContentPane.getSize());//大小为内容面板大小
        setSize(mainDialogContentPane.getWidth(), mainDialogContentPane.getHeight());
        Tools.setWindowLocationOnScreamRight(this); //居右显示
    }


    public QQContactTree getFriendsTree(){
        return mainDialogContentPane.getFriendsTree();
    }
}
