package com.ui.table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by hq on 2014/11/2.
 */
public class ExtendTable extends JTable {
    private DefaultTableModel tableModel;
    public ExtendTable( Object[] columnNames) {
        tableModel = new DefaultTableModel(columnNames, 0); // 初始化表头
        setModel(tableModel);
    }

    /**
     * 添加行
     * @param rowData
     */
    public void addRow(Object [] rowData){
        if(rowData != null){
            tableModel.addRow(rowData);
        }
    }

    /**
     * 删除一行
     * @param row 行号
     */
    public void removeRow(int row){
        tableModel.removeRow(row);
    }

    /**
     * 清除所有的行
     */
    public void cleanAllRows(){
        tableModel.setRowCount(0);
    }
}
