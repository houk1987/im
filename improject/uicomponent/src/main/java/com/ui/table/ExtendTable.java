package com.ui.table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by hq on 2014/11/2.
 */
public class ExtendTable extends JTable {
    private DefaultTableModel tableModel;
    public ExtendTable( Object[] columnNames) {
        tableModel = new DefaultTableModel(columnNames, 0); // ��ʼ����ͷ
        setModel(tableModel);
    }

    /**
     * �����
     * @param rowData
     */
    public void addRow(Object [] rowData){
        if(rowData != null){
            tableModel.addRow(rowData);
        }
    }

    /**
     * ɾ��һ��
     * @param row �к�
     */
    public void removeRow(int row){
        tableModel.removeRow(row);
    }

    /**
     * ������е���
     */
    public void cleanAllRows(){
        tableModel.setRowCount(0);
    }
}
