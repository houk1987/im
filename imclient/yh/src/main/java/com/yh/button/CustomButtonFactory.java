package com.yh.button;

import javax.swing.*;

/**
 * Created by a on 2014/7/31.
 */
public class CustomButtonFactory {

    private static final String windowButtonPath = "res/button/window/";
    private static final String sessionButtonPath = "res/button/session/";
    private static final String contactButtonPath = "res/main/addContact/";

    public static JButton createCloseButton(){return ButtonFactory.createButton(windowButtonPath, "�ر�", "closed.png"); }

    public static JButton createMinButton(){
        return ButtonFactory.createButton(windowButtonPath, "��С��", "min.png");
    }

    public static JButton createMaxButton(){
        return ButtonFactory.createButton(windowButtonPath, "���", "max.png");
    }

    public static JButton createReturnButton(){return ButtonFactory.createButton(windowButtonPath, "��ԭ", "return.png");}

    public static JButton createFontButton() {
        return ButtonFactory.createButton(sessionButtonPath, "����", "font.png");
    }

    public static JButton createFileTransferButton() {return ButtonFactory.createButton(sessionButtonPath, "�ļ�����", "transfer.png");}

    public static JButton createSendButton() {
        return ButtonFactory.createButton(sessionButtonPath, "����", "send.png");
    }

    public static JButton createCloseFrameButton() {return ButtonFactory.createButton(sessionButtonPath, "�ر�", "close.png"); }

    public static JButton createFaceButton() {return ButtonFactory.createButton(sessionButtonPath, "����", "face.png");}

    public static JButton createBoldButton() {
        return ButtonFactory.createButton(sessionButtonPath, "����", "bold.png");
    }

    public static JButton createItalicButton() {return ButtonFactory.createButton(sessionButtonPath, "б��", "italic.png");}

    public static JButton createCancelButton() {
        return ButtonFactory.createButton(contactButtonPath, "ȡ��", "cancel.png");
    }

    public static JButton createNextButton() {
        return ButtonFactory.createButton(contactButtonPath, "��һ��", "next.png");
    }

    public static JButton createPreviousButton() {
        return ButtonFactory.createButton(contactButtonPath, "��һ��", "previous.png");
    }




    public static JButton createAudiblesButton(){
        return ButtonFactory.createButton(sessionButtonPath,"ɫ�D��","audibles.png");
    }

    public static JButton createBuzzButton(){
        return ButtonFactory.createButton(sessionButtonPath,"��������","buzz.png");
    }

    public static JButton createScreenButton(){
        return ButtonFactory.createButton(sessionButtonPath,"","screenCapture.png");
    }

    public static JButton createDisCloseButton() {
        return ButtonFactory.createButton(sessionButtonPath,"","dispImgToggleClose.png");
    }

    public static JButton createDisOpenButton() {
        return ButtonFactory.createButton(sessionButtonPath,"","dispImgToggleOpen.png");
    }

    public static JButton createAddBuddyButton() {
        return ButtonFactory.createButton(contactButtonPath,"","AddBuddy.png");
    }

    public static JButton createFinishButton() {
        return ButtonFactory.createButton(contactButtonPath,"","finish.png");
    }

}
