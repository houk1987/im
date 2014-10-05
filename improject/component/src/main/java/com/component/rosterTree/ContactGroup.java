package com.component.rosterTree;





import com.component.rosterTree.panes.CollapsiblePane;
import com.component.rosterTree.renderer.ContactTableCellRenderer;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 2014/8/28.
 */
public class ContactGroup extends CollapsiblePane {
    private String groupName;
    private MyTable contactItemList;
    private JPanel listPanel;



    public ContactGroup(String groupName){
        this.groupName = groupName;
        contactItemList = new MyTable();

//        contactItemList.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if(e.getButton()==1&&e.getClickCount()==2){
//                    ContactItem item = (ContactItem)contactItemList.getSelectedValue();
//                    item.click();
//
//                }
//            }
//        });
        setTitle(getGroupTitle(groupName));
        this.setContentPane(contactItemList);

    }

    public void addContactItem(ContactItem item){
        contactItemList.addItem(item);
    }

   // public List<ContactItem> getAllContactItem(){
        //return contactItems;
   // }


    /**
     * Returns the "pretty" title of the ContactGroup.
     *
     * @param title the title.
     * @return the new title.
     */
    public String getGroupTitle(String title) {
        int lastIndex = title.lastIndexOf("::");
        if (lastIndex != -1) {
            title = title.substring(lastIndex + 2);
        }
        return title;
    }

   public  class MyTable extends JTable {
        private MyTableModel model;
        private int mouseOnRowIndex = -1;

        MyTable() {
            model = new MyTableModel();
            setModel(model);
//            setShowGrid(false); //去掉表格线
            setShowHorizontalLines(false);
            setRowHeight(40);//设置表格高度
            this.setDefaultRenderer(Object.class, new ContactTableCellRenderer());
            this.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    super.mouseMoved(e);
                    MyTable table = (MyTable) e.getComponent();
                    Point point = e.getPoint();
                    int rowAtPoint = table.rowAtPoint(point);
                    table.setMouseOnRowIndex(rowAtPoint);
                    table.updateUI();
                }
            });
        }

        public void setMouseOnRowIndex(int mouseOnRowIndex) {
            this.mouseOnRowIndex = mouseOnRowIndex;
        }

        public int getMouseOnRowIndex() {
            return mouseOnRowIndex;
        }

        public void addItem(ContactItem contactItem){
            model.addContactItem(contactItem);
            updateContactNumOnGroupTitle();
        }

       private void updateContactNumOnGroupTitle(){
           String groupTitle = getGroupTitle(groupName)+"(0/"+model.getAllContactItems().size()+")";  //计算标签上的总共的人数
           setTitle(groupTitle);
       }
    }

     class MyTableModel extends AbstractTableModel {
         private List<ContactItem> contactItems = new ArrayList<>();
        @Override
        public int getRowCount() {
            return contactItems.size();
        }
        @Override
        public int getColumnCount() {
            return 1;
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return contactItems.get(rowIndex);
        }

        public void addContactItem(ContactItem contactItem){
            if(contactItem == null)return;
            contactItems.add(contactItem);
        }

        public List<ContactItem> getAllContactItems(){
            return contactItems;
        }
    }
}
