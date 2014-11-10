package com.dt.group;


import com.dt.main.tree.OrgTree;
import com.dt.start.StartClient;
import com.dt.vo.UserInfo;
import com.ui.table.ExtendTable;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectMemberDialog extends JDialog implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextField searchText;
    private JScrollPane left;
    private JScrollPane right;
    private JPanel contentPane = null;
    private JButton choosedButton;
    private JButton searchButton;
    private JButton removeButton;
    private JButton cancelButton;
    private JButton okButton;
    private GroupWindow groupWindow;
    private ExtendTable selectedMemberTable;
    private OrgTree orgTree;
    private List<UserInfo> userList = new ArrayList<>();

    public SelectMemberDialog(GroupWindow owner) {
        super();
        setLocationRelativeTo(owner);
        init();
        this.setLocationRelativeTo(owner);
        this.groupWindow = owner;
    }

    private void init() {
        setModal(true);
        initComponents();
        layoutComponents();
    }

    /**
     * ��ʼ�����
     */
    private void initComponents() {
        searchText = new JTextField("��������");
        searchButton = new JButton("����");
        searchButton.setSize(60, 30);
        choosedButton = new JButton("ѡ��");
        choosedButton.setSize(60, 30);
        removeButton = new JButton("�Ƴ�");
        removeButton.setSize(60, 30);
        cancelButton = new JButton("ȡ��");
        okButton = new JButton("ȷ��");

        left = new JScrollPane();
        left.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        orgTree = new OrgTree();
        orgTree.loadData();
        left.setBorder(new LineBorder(new Color(170, 187, 207)));
        left.setViewportView(orgTree);

        right = new JScrollPane();
        right.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        Object[] header = {"����", "���"};
        selectedMemberTable = new ExtendTable(header);
        right.setViewportView(selectedMemberTable);
        right.setBorder(new LineBorder(new Color(170, 187, 207)));
    }

    /**
     * ���в��֣����������¼�����
     */
    private void layoutComponents() {
        JPanel contentPane = new JPanel(null);
        contentPane.setOpaque(false);
        contentPane.setBackground(new Color(249, 251, 254));
        add(contentPane, BorderLayout.CENTER);

        left.setBounds(23, 71, 220, 280);
        right.setBounds(326, 71, 220, 280);
        searchText.setBounds(23, 43, 150, 23);
        searchButton.setLocation(179, 42);
        choosedButton.setLocation(251, 144);
        removeButton.setLocation(251, 222);
        contentPane.add(choosedButton);
        contentPane.add(searchButton);
        contentPane.add(removeButton);
        contentPane.add(searchText);
        contentPane.add(left);
        contentPane.add(right);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(okButton);
        bottomPanel.add(cancelButton);

        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
        removeButton.addActionListener(this);
        searchButton.addActionListener(this);
        choosedButton.addActionListener(this);
    }

    @Override
    protected void dialogInit() {
        super.dialogInit();
        setSize(585, 445);
    }

    /**
     * ������ѡ��Ա�б�
     *
     * @param userList
     */
    public void loadSelectedUserTable(List<UserInfo> userList) {
        this.userList = userList;
        if (this.userList == null) {
            this.userList = new ArrayList<>();
        }else{
            for (UserInfo userInfo : userList) {
                if(userInfo.getId().equals(StartClient.getUserInfo().getId()))continue;
                Object[] rowData = new Object[2];
                rowData[0] = userInfo.getUsername();
                rowData[1] = userInfo.getId();
                selectedMemberTable.addRow(rowData);
            }
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            //searchButtonPressed();
        }

        if (e.getSource() == choosedButton) {
            selectedMember();
        }

        if (e.getSource() == removeButton) {
            delete();
        }

        if (e.getSource() == cancelButton) {
            this.dispose();
        }

        if (e.getSource() == okButton) {
            dispose();
            groupWindow.getGroupWindowContent().initTable(userList);
        }
    }

    private void selectedMember() {
        //���ѡ��Ľڵ�
        int[] selectedRow = orgTree.getSelectionRows();
        //ѭ������ȡ�ýڵ��е�User`
        for (int i = 0; i < selectedRow.length; i++) {
            TreePath treePath = orgTree.getPathForRow(selectedRow[i]);
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
            Object userObject = selectedNode.getUserObject();
            if (userObject instanceof UserInfo) {
                UserInfo user = (UserInfo) userObject;
                if (isSelected(user)) {
                    Object[] object = {user.getUsername(), user.getId()};
                    selectedMemberTable.addRow(object);
                    userList.add(user);
                }
            }
        }
    }

    private void delete() {
        int[] rows = selectedMemberTable.getSelectedRows();
        int offset = 0;
        for (int i = 0; i < rows.length; i++) {
            selectedMemberTable.removeRow(rows[i - offset]);
            userList.remove(i - offset);
            offset++;
        }
    }

    private boolean isSelected(UserInfo user) {
        for (int j = 0; j < userList.size(); j++) {
            if (user.getId().equals(userList.get(j).getId())) {
                JOptionPane.showMessageDialog(this, "�û�" + user.getUsername() + "����ӣ������ظ����!");
                return false;
            }
        }
        return true;
    }
}
