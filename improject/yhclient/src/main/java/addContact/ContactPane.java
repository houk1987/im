package addContact;

import button.CommonButtonFactory;
import com.comunication.connection.ConnectionManager;
import com.comunication.roster.RosterManager;
import com.san30.pub.tools.SanHttpClient;
import com.ui.jlabel.JLabelFactory;
import com.ui.textField.JTextFieldFactory;
import images.AddContactImageFactory;
import images.CommonImagesFactroy;
import main.MainFrame;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by a on 2014/9/2.
 */
public class ContactPane extends JPanel implements ActionListener{
    private RosterManager rosterManager = new RosterManager(ConnectionManager.getConnection().getRoster());
    private ImageIcon bgFirstImageIcon = AddContactImageFactory.createAddContactBgFirst();
    private ImageIcon bgThird1ImageIcon = AddContactImageFactory.createThridBg();
    private ImageIcon bgSecondImageIcon = AddContactImageFactory.createAddContactSecondBg();
    private ImageIcon currentBgImageIcon = bgFirstImageIcon;
    private JButton nextButton;
    private JButton cancelButton;
    private JButton finishButton;
    private JButton previousButton;
    private JDialog jDialog;
    private JTextField accountJTextField;
    private JPanel userNamePanel;
    private JLabel userName;

    private JPanel contactPanel;
    private JLabel contactUserName;
    private JLabel rsLabel;
    private JLabel lineLabel;

    private ValidateApplyAccountWork validateApplyAccountWork;

    public ContactPane(JDialog jDialog) {
        setOpaque(true);
        this.jDialog  = jDialog;
        setLayout(null);
        previousButton = CommonButtonFactory.getInstance().createPreviousButton();
        previousButton.setDisabledIcon(CommonImagesFactroy.createPreviousDisable());
        previousButton.setEnabled(false);
        previousButton.setLocation(170,362);
        add(previousButton);

        nextButton = CommonButtonFactory.getInstance().createNextButton();
        nextButton.setDisabledIcon(CommonImagesFactroy.createNextDisable());
        nextButton.setEnabled(false);
        nextButton.setLocation(250,362);
        add(nextButton);

        cancelButton = CommonButtonFactory.getInstance().createCancelButton();
        cancelButton.setDisabledIcon(CommonImagesFactroy.createCancelDisable());
        cancelButton.setLocation(329,363);
        add(cancelButton);

        finishButton = CommonButtonFactory.getInstance().createFinishButton();
        finishButton.setVisible(false);
        finishButton.setLocation(329,363);
        add(finishButton);

        previousButton.addActionListener(this);
        nextButton.addActionListener(this);
        cancelButton.addActionListener(this);
        finishButton.addActionListener(this);

        accountJTextField = JTextFieldFactory.createJTextField(270, 20);
        accountJTextField.setBackground(Color.GRAY);
        accountJTextField.setLocation(13, 46);
        Document document = accountJTextField.getDocument();
        document.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                nextButton.setEnabled(!accountJTextField.getText().isEmpty());
                contactUserName.setText(accountJTextField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                nextButton.setEnabled(!accountJTextField.getText().isEmpty());
                contactUserName.setText(accountJTextField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        add(accountJTextField);

        userNamePanel = new JPanel();
        userNamePanel.setBackground(new Color(119,36,111));
        userNamePanel.setLayout(new BorderLayout());
        userNamePanel.setBounds(60, 230, 100, 21);
        userName = new JLabel("asda");
        userName.setForeground(new Color(204,204,204));
        userName.setFont(new Font("宋体",Font.PLAIN,12));
        userNamePanel.add(userName);
        add(userNamePanel);
        userNamePanel.setVisible(false);

        contactPanel = new JPanel();
        contactPanel.setVisible(false);
        contactPanel.setBackground(new Color(119, 36, 111));
        contactPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        contactPanel.setBounds(103, 15, 400, 20);
        contactUserName = new JLabel(accountJTextField.getText());
        contactUserName.setForeground(new Color(204,204,204));
        contactUserName.setFont(new Font("宋体", Font.PLAIN, 12));
        contactPanel.add(contactUserName);
        contactPanel.add(JLabelFactory.createJLabel(AddContactImageFactory.createContactRightBg()));
        add(contactPanel);

        rsLabel = JLabelFactory.createJLabel(null);
        rsLabel.setForeground(Color.WHITE);
        rsLabel.setBounds(5,5,250,30);
        rsLabel.setVisible(false);
        add(rsLabel);

        lineLabel = JLabelFactory.createJLabel(AddContactImageFactory.createLineBg());
        lineLabel.setLocation(5,340);
        lineLabel.setVisible(false);
        add(lineLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Image image = currentBgImageIcon.getImage();
        g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancelButton){
           jDialog.dispose();
        }else if(e.getSource() == nextButton){
            if(currentBgImageIcon.equals(bgFirstImageIcon)){
                currentBgImageIcon =bgSecondImageIcon;
                accountJTextField.setVisible(false);
                userNamePanel.setVisible(true);
                contactPanel.setVisible(true);
                previousButton.setEnabled(true);

            }else if(currentBgImageIcon.equals(bgSecondImageIcon)){
                currentBgImageIcon =bgThird1ImageIcon;
                previousButton.setEnabled(false);
                nextButton.setEnabled(false);
                cancelButton.setEnabled(false);
                contactPanel.setVisible(false);
                userNamePanel.setVisible(false );
                lineLabel.setVisible(true);
                rsLabel.setVisible(true);
                String jid = accountJTextField.getText()+"@"+ ConnectionManager.getConnection().getServiceName(); //添加的账号
                if(accountJTextField.getText().equals(MainFrame.getInstance().getLoginUser())){
                    rsLabel.setText("不能添加自己为好友！");
                    previousButton.setEnabled(true);
                    cancelButton.setEnabled(true);
                }else if(rosterManager.getRosterEntry(jid)!=null){
                    rsLabel.setText("该用户已经是你的好友！");
                    previousButton.setEnabled(true);
                    cancelButton.setEnabled(true);
                }else{
                    validateApplyAccountWork  = new ValidateApplyAccountWork();
                    validateApplyAccountWork.execute();
                }

            }else{
                finishButton.setVisible(true);
                cancelButton.setVisible(false);
            }
            updateUI();
        }else if(e.getSource() == previousButton){
            if(currentBgImageIcon.equals(bgSecondImageIcon)){
                currentBgImageIcon = bgFirstImageIcon;
                previousButton.setEnabled(false);
                accountJTextField.setVisible(true);
                userNamePanel.setVisible(false);
                contactPanel.setVisible(false);
                accountJTextField.requestFocus();
                lineLabel.setVisible(false);
                rsLabel.setVisible(false);
            }else if(currentBgImageIcon.equals(bgThird1ImageIcon)){
                currentBgImageIcon = bgSecondImageIcon;
                userNamePanel.setVisible(true);
                contactPanel.setVisible(true);
                nextButton.setEnabled(true);
                lineLabel.setVisible(false);
                rsLabel.setVisible(false);
            }
            updateUI();
        }else if(finishButton == e.getSource()){
            jDialog.dispose();
        }
    }

    class ValidateApplyAccountWork extends SwingWorker{
        @Override
        protected Object doInBackground() throws Exception {
            HashMap<String,String> paramMap = new HashMap<String, String>();
            paramMap.put("jid", MainFrame.getInstance().getLoginUser());
            paramMap.put("type","validateAccount");
            paramMap.put("targetAccount",accountJTextField.getText());
            String url = "http://" + ConnectionManager.getConnection().getHost() + ":" + 9090 + "/plugins/udpserver/addcontact";
            String rs = SanHttpClient.getDataAsString(url, paramMap);
            if(Boolean.valueOf(rs.trim())){
                rsLabel.setText("系统已经向朋友发出了好友申请。");
                finishButton.setVisible(true);
                cancelButton.setVisible(false);
            }else{
               rsLabel.setText(accountJTextField.getText()+"不是一个正确的 YM ID ,请检查 稍后再试。");
               rsLabel.setToolTipText(accountJTextField.getText()+"不是一个正确的 YM ID ,请检查 稍后再试。");
               cancelButton.setEnabled(true);
            }
            previousButton.setEnabled(true);
            return true;
        }
    }
}
