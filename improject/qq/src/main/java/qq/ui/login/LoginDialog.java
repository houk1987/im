package qq.ui.login;


import qq.ui.window.PubDialog;

/**
 * Created by lenovo on 2014/9/16.
 */
public class LoginDialog extends PubDialog {

    private LoginPane loginPane;  //��½�����������

    /**
     * ���캯��
     * ����qq ��½����
     */
    public LoginDialog() {
        loginPane = new LoginPane(this);
        setContentPane(loginPane);    //�����������
        setSize(loginPane.getSize());//��СΪ��������С
        setLocationRelativeTo(null); //������ʾ
    }

    public static void main(String[] args) {
        LoginDialog loginDialog = new LoginDialog();
        loginDialog.setVisible(true);
    }
}
