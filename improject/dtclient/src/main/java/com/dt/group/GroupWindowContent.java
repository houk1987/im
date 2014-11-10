package com.dt.group;

import com.comunication.chat.GroupChatInfo;
import com.dt.main.DtClientWindow;
import com.dt.start.StartClient;
import com.dt.vo.UserInfo;
import com.ui.table.ExtendTable;
import org.apache.log4j.Logger;
import org.jivesoftware.smack.XMPPException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hq on 2014/11/2.
 */
class GroupWindowContent extends JPanel implements ActionListener {
    private Logger logger = Logger.getLogger(GroupWindowContent.class);
    private JLabel groupNameLabel;
    private JLabel groupMemberNameLabel;
    private JButton cancelButton;
    private JButton okButton;
    private JButton addMemberButton;
    private JButton delMemberButton;
    private ExtendTable groupMemberTable;
    private JTextField groupNameJTextField;
    private List<UserInfo> userList = new ArrayList<>();
    private GroupChatInfo groupChatInfo;
    private GroupWindow groupWindow;
    public GroupWindowContent(GroupWindow groupWindow) {
        this.groupWindow = groupWindow;
        this.groupChatInfo = groupWindow.getGroup();
        setLayout(new BorderLayout());
        initComponents();
        layoutComponents();
    }

    /**
     * 初始化组件
     */
    private void initComponents() {
        groupNameLabel = new JLabel("自定义群组名称：");
        groupMemberNameLabel = new JLabel("自定义组成员：");
        groupNameJTextField = new JTextField();
        groupNameJTextField.requestFocus();
        cancelButton = new JButton("取消");
        okButton = new JButton("确定");
        addMemberButton = new JButton("添加");
        delMemberButton = new JButton("删除");
        Object [] header = {"姓名","编号"};
        groupMemberTable = new ExtendTable(header);
    }

    /**
     * 进行布局，给组件添加事件监听
     */
    private void layoutComponents() {
        //添加中间面板
        JPanel centerPane = new JPanel(null);
        centerPane.setOpaque(false);
        add(centerPane, BorderLayout.CENTER);

        groupNameLabel.setBounds(15, 15, 120, 24);
        groupNameJTextField.setBounds(15, 45, 230, 24);
        groupMemberNameLabel.setBounds(15, 75, 120, 24);
        addMemberButton.setBounds(260, 115, 60, 30);
        delMemberButton.setBounds(260, 155, 60, 30);

        //添加已在群组成员表格
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBounds(15, 105, 230, 220);
        jScrollPane.setViewportView(groupMemberTable);

        centerPane.add(groupNameLabel); // 添加群组名称
        centerPane.add(groupNameJTextField);//群组名称输入框
        centerPane.add(groupMemberNameLabel);//群组人员名称
        centerPane.add(jScrollPane);//添加右边表格
        centerPane.add(addMemberButton);//添加添加人员按钮
        centerPane.add(delMemberButton);//删除人员按钮

        //底部确定，取消按钮所在面板
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(okButton);
        bottomPanel.add(cancelButton);
        //添加监听
        addMemberButton.addActionListener(this);
        delMemberButton.addActionListener(this);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
        initGroupData();
    }

    /**
     * 初始化界面数据
     */
    private void initGroupData() {
        if (groupChatInfo == null) {
            return;
        }
        groupNameJTextField.setText(groupChatInfo.getName());
        initTable(GroupManager.getUserInfo(groupChatInfo.getJid()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.okButton) {
            String groupName = this.groupNameJTextField.getText();
            if(groupName.length() != 0){
                if(groupChatInfo.getJid() == null){
                    try {
                        groupChatInfo.setName(groupName);
                        GroupManager.createGroup(groupChatInfo,userList);
                        JOptionPane.showMessageDialog(this,"创建群组成功!","提示",JOptionPane.INFORMATION_MESSAGE);
                        groupWindow.dispose();
                    } catch (XMPPException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(this,"创建群组失败!","提示",JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    try {
                        GroupManager.modifyGroup(groupChatInfo,groupName,userList);
                        JOptionPane.showMessageDialog(this,"修改群组成功!","提示",JOptionPane.INFORMATION_MESSAGE);
                        groupWindow.dispose();
                    } catch (XMPPException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(this,"修改群组失败!","提示",JOptionPane.INFORMATION_MESSAGE);
                    }

                }
            }else{
                JOptionPane.showMessageDialog(this,"群组名称不能为空!","提示",JOptionPane.INFORMATION_MESSAGE);
                this.groupNameJTextField.requestFocus();
            }
        } else if (e.getSource() == this.cancelButton) {
            groupWindow.dispose();
        } else if (e.getSource() == this.addMemberButton) {
            SelectMemberDialog selectMemberDialog = new SelectMemberDialog(groupWindow);
            selectMemberDialog.loadSelectedUserTable(userList);
            selectMemberDialog.setVisible(true);
        } else if (e.getSource() == this.delMemberButton) {
            int[] rows = groupMemberTable.getSelectedRows();
            int offset = 0;
            for (int i = 0; i < rows.length; i++) {
                Object objects =groupMemberTable.getModel().getValueAt(rows[i - offset],1);
                if(objects.toString().equals(DtClientWindow.getInstance().getUserInfo().getId()))return;
                groupMemberTable.removeRow(rows[i - offset]);
                userList.remove(i - offset);
                offset++;
            }
        }
    }

    public void initTable(List<UserInfo> users) {
        this.userList = users;
        if(users!=null && users.size()>0){
            groupMemberTable.cleanAllRows();
            for (UserInfo userInfo : users){
                if(userInfo.getId().equals(StartClient.getUserInfo().getId()))continue;
                Object[] rowData = new Object[2];
                rowData[0] = userInfo.getUsername();
                rowData[1] = userInfo.getId();
                groupMemberTable.addRow(rowData);
            }
        }
    }
}
