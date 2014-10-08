package qq.ui.login;


import qq.ui.window.PubDialog;

/**
 * Created by lenovo on 2014/9/16.
 */
public class LoginDialog extends PubDialog {

    private LoginPane loginPane;  //登陆窗口内容面板

    /**
     * 构造函数
     * 创建qq 登陆窗口
     */
    public LoginDialog() {
        loginPane = new LoginPane(this);
        setContentPane(loginPane);    //设置内容面板
        setSize(loginPane.getSize());//大小为内容面板大小
        setLocationRelativeTo(null); //居中显示
    }

    public static void main(String[] args) {
        LoginDialog loginDialog = new LoginDialog();
        loginDialog.setVisible(true);
    }
}
