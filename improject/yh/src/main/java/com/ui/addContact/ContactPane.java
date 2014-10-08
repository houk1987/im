package com.ui.addContact;

import com.component.jlabel.JLabelFactory;
import com.ui.MainFrame;
import com.ui.button.YhButtonFactory;
import com.ui.jtextField.YhTextFiled;
import com.ui.resource.YhImageRes;
import org.smackservice.SmackConnection;

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
    private ImageIcon bgFirstImageIcon = YhImageRes.getImageIcon("addConatactBgFirst.png");
    private ImageIcon bgThird1ImageIcon = YhImageRes.getImageIcon("addcontactBgThrid1.png");
    private ImageIcon bgSecondImageIcon = YhImageRes.getImageIcon("addConatactSecondBg.png");
    private ImageIcon currentBgImageIcon = bgFirstImageIcon;
    private JButton nextButton;
    private JButton cancelButton;
    private JButton finishButton;
    private JButton previousButton;
    private JDialog jDialog;
    private YhTextFiled accountJTextField;
    private JPanel userNamePanel;
    private JLabel userName;

    private JPanel contactPanel;
    private JLabel contactUserName;

    private validateApplyAccountWork validateApplyAccountWork;

    public ContactPane(JDialog jDialog) {
        setOpaque(true);
        this.jDialog  = jDialog;
        setLayout(null);
        previousButton = YhButtonFactory.getInstance().createPreviousButton();
        previousButton.setDisabledIcon(YhImageRes.getButtonImageIcon("previousDisabel.png"));
        previousButton.setEnabled(false);
        previousButton.setLocation(170,362);
        add(previousButton);

        nextButton = YhButtonFactory.getInstance().createNextButton();
        nextButton.setDisabledIcon(YhImageRes.getButtonImageIcon("nextDisabel.png"));
        nextButton.setEnabled(false);
        nextButton.setLocation(250,362);
        add(nextButton);

        cancelButton = YhButtonFactory.getInstance().createCancelButton();
        cancelButton.setDisabledIcon(YhImageRes.getButtonImageIcon("cancelDisabel.png"));
        cancelButton.setLocation(329,363);
        add(cancelButton);

        finishButton = YhButtonFactory.getInstance().createFinishButton();
        finishButton.setVisible(false);

        previousButton.addActionListener(this);
        nextButton.addActionListener(this);
        cancelButton.addActionListener(this);
        finishButton.addActionListener(this);

        accountJTextField = new YhTextFiled();
        accountJTextField.setBounds(13,46,270, 20);
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
        userName = new JLabel("test111111111111111");
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
        contactPanel.add(JLabelFactory.createJLabel(YhImageRes.getImageIcon("contactRightbg.png")));
        add(contactPanel);
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
            }
            updateUI();
        }else if(e.getSource() == previousButton){

        }else if(finishButton == e.getSource()){
            jDialog.dispose();
        }
    }

    class validateApplyAccountWork extends SwingWorker{
        @Override
        protected Object doInBackground() throws Exception {
            HashMap<String,String> paramMap = new HashMap<String, String>();
            paramMap.put("jid", MainFrame.getInstance().getLoginUser());
            paramMap.put("type","validateAccount");
            paramMap.put("targetAccount",accountJTextField.getText());
            String url = "http://" + SmackConnection.getInstance().getHost() + ":" + 9090 + "/plugins/udpserver/addcontact";
          //  String rs = SanHttpClient.getDataAsString(url, paramMap);
//            if(Boolean.valueOf(rs.trim())){
//                finishButton.setVisible(true);
//                cancelButton.setVisible(false);
//            }else{
//               // thridPane.setTipText(firstPane.accountJTextField.getText()+"不是一个正确的 YM ID ,请检查 稍后再试。");
//            }
            previousButton.setEnabled(true);
            return true;
        }
    }
}
