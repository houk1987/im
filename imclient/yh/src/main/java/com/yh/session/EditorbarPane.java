package com.yh.session;

import com.yh.button.CustomButtonFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by a on 2014/8/22.
 */
public class EditorbarPane extends JPanel implements ActionListener{
    private JButton emoticonButton; //���鰴ť
    private JButton audiblesButton; //��ɫ��ť
    private JButton fontButton; //���尴ť
    private JButton buzzButton;//��������
    private JButton fileButton; //�����ļ�
    private JButton screenCaptureButton; //����
    private JButton disCloseButton; //�رո������
    private JButton disOpenButton; //�����������

    public EditorbarPane() {
        setBackground(new Color(118,36,110));
        this.initComponent();
        this.layoutComponent();

    }

    private void initComponent(){
        emoticonButton = CustomButtonFactory.createFaceButton();
        audiblesButton =CustomButtonFactory.createAudiblesButton();
        fontButton = CustomButtonFactory.createFontButton();
        buzzButton = CustomButtonFactory.createBuzzButton();
        fileButton = CustomButtonFactory.createFileTransferButton();
        screenCaptureButton = CustomButtonFactory.createScreenButton();
        disCloseButton =  CustomButtonFactory.createDisCloseButton();
        disOpenButton = CustomButtonFactory.createDisOpenButton();
        disCloseButton.setVisible(false);//Ĭ���ǹرյ�
    }

    private void layoutComponent(){
        setLayout(new BorderLayout());
        JPanel leftPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPane.setBackground(new Color(118,36,110));
        leftPane.add(emoticonButton);
        leftPane.add(audiblesButton);
        leftPane.add(fontButton);
        leftPane.add(buzzButton);
        leftPane.add(fileButton);
        leftPane.add(screenCaptureButton);
        JPanel rightPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rightPane.setBackground(new Color(118,36,110));
        rightPane.add(disCloseButton);
        rightPane.add(disOpenButton);
        add(leftPane, BorderLayout.WEST);
        add(rightPane,BorderLayout.EAST);
        JLabel expandableImageLabel = new JLabel();
        expandableImageLabel.setBackground(new Color(118, 36, 110));
        add(expandableImageLabel, BorderLayout.CENTER);
        emoticonButton.addActionListener(this);
        disCloseButton.addActionListener(this);
        disOpenButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(emoticonButton == e.getSource()){

        }else if(disCloseButton == e.getSource()){
            disOpenButton.setVisible(true);
            disCloseButton.setVisible(false);
        }else if(disOpenButton == e.getSource()){
            disOpenButton.setVisible(false);
            disCloseButton.setVisible(true);
        }
    }
}
