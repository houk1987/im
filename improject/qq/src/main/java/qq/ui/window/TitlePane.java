package qq.ui.window;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lenovo on 2014/9/11.
 */
public class TitlePane extends JPanel implements ActionListener {
    protected JButton closeButton;
    protected JButton maxButton;
    protected JButton minButton;
    protected JButton restoreButton;
    protected JLabel titleIconLabel;
    protected JLabel titleTxtLabel;
    private Title title;
    private WindowButtonHandel windowButtonHandel;

    public TitlePane(Title title, WindowButtonHandel windowButtonHandel) {
        setLayout(new BorderLayout());
        this.windowButtonHandel = windowButtonHandel;
        setTitle(title);
        setOpaque(false);
    }

    public void setTitle(Title title) {
        this.title = title;
        initWindowTitlePane();
        initRightWindowButtonPane();
    }

    private void initRightWindowButtonPane() {
       // closeButton = ImageButtonFactory.createButton(this.title.getBtnImagePath(), "�ر�", "close.png");
      //  maxButton = ImageButtonFactory.createButton(this.title.getBtnImagePath(), "���", "max.png");
      //  minButton = ImageButtonFactory.createButton(this.title.getBtnImagePath(), "��С��", "min.png");
      //  restoreButton = ImageButtonFactory.createButton(this.title.getBtnImagePath(), "��ԭ", "restore.png");
        initButtonShowState();  //���ݴ���״̬ ��ʾ��ť
        JPanel titleRightPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titleRightPane.setOpaque(false);
        add(titleRightPane, BorderLayout.EAST);
        titleRightPane.add(minButton);
        titleRightPane.add(maxButton);
        titleRightPane.add(restoreButton);
        titleRightPane.add(closeButton);
        closeButton.addActionListener(this);
        maxButton.addActionListener(this);
        minButton.addActionListener(this);
        restoreButton.addActionListener(this);
    }

    private void initWindowTitlePane() {
        JPanel titleLeftPane = new JPanel(new FlowLayout());
        titleLeftPane.setOpaque(false);
        add(titleLeftPane, BorderLayout.WEST);
        if (this.title.getTitleIcon()!=null){
         //   titleIconLabel = JLabelFactory.createJLabel(this.title.getTitleIcon());
            titleLeftPane.add(titleIconLabel);
        }
        if(this.title.getTxt()!=null){
         //   titleTxtLabel = JLabelFactory.createJLabel(this.title.getTxt(), this.title.getFont(), this.title.getColor());
            titleLeftPane.add(titleTxtLabel);
        }
    }

    private void initButtonShowState(){
        if(windowButtonHandel instanceof JFrame){
            int state = ((JFrame)windowButtonHandel).getExtendedState(); //��ȡ���ڵ�״̬
            if(state == JFrame.MAXIMIZED_BOTH){ //���
                restoreButton.setVisible(true);
                maxButton.setVisible(false);
            }else{
                restoreButton.setVisible(false);
                maxButton.setVisible(true);
            }
        }else if(windowButtonHandel instanceof JDialog){
            restoreButton.setVisible(false);
            maxButton.setVisible(false);
            minButton.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(closeButton)) {
            windowButtonHandel.close();
        } else if (source.equals(maxButton)) {
            windowButtonHandel.max();
            initButtonShowState();
        } else if (source.equals(minButton)) {
            windowButtonHandel.min();
        }else if(source.equals(restoreButton)){
            windowButtonHandel.restore();
            initButtonShowState();
        }
    }


}
