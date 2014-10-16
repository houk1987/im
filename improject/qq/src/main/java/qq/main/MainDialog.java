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
        setContentPane(mainPane);    //�����������
        setSize(mainPane.getSize());//��СΪ��������С
        setLocationRelativeTo(null); //������ʾ
    }

    public static void main(String[] args) {
        MainDialog.getInstance().setVisible(true);
    }
}
