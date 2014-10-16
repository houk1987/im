package qq.main;



import qq.ui.window.PubDialog;

import java.awt.*;

/**
 * Created by lenovo on 2014/9/17.
 */
public class MainDialog extends PubDialog {
    private static MainDialog mainDialog;
    private MainPane mainPane;

    public static MainDialog getInstance() {
        if(mainDialog == null){
            mainDialog = new MainDialog();
        }
        return mainDialog;
    }


    private MainDialog() throws HeadlessException {
        mainPane = new MainPane();
        setContentPane(mainPane);    //设置内容面板
        setSize(mainPane.getSize());//大小为内容面板大小
        setLocationRelativeTo(null); //居右显示
    }

    public static void main(String[] args) {
        MainDialog.getInstance().setVisible(true);
    }
}
