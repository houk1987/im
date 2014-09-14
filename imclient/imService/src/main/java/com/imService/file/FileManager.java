package com.imService.file;

import com.imService.connection.ImConnection;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.filetransfer.FileTransfer;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;
import org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer;

import java.io.File;

/**
 * Created by HK on 2014/9/14.
 */
public class FileManager {

    private ImConnection imConnection;

    public FileManager(ImConnection imConnection) {
        this.imConnection = imConnection;
    }

    /**
     * �����ļ�
     * @param filePath  �ļ�·��
     * @param user
     */
    public void sendFile(String filePath,String user) throws Exception{
        if (filePath == null)throw new NullPointerException("�ļ�·������ȷ!");
        File sendFile = new File(filePath);
        sendFile(sendFile,user);  //�����ļ�
    }

    /**
     * �����ļ�
     * �����ļ�ǰ�������ж�ͨѶ��ϵ�Ƿ��Ѿ��ж�,����жϣ���ʾ�û������������ж�
     * Ĭ�������ļ����ͳ�ʱʱ��Ϊ 1����
     *
     * @param sendFile  �ļ�
     * @param user  �����ļ��Ķ���
     * @throws Exception
     */

    public void sendFile(File sendFile,String user) throws Exception{
        if(imConnection == null || imConnection.getXMPPConnection().isConnected())throw new XMPPException("�����������ж�!");

        XMPPConnection connection = imConnection.getXMPPConnection();   //������ͨ�����Ӷ���

        FileTransferManager manager = new FileTransferManager(connection);  //����һ���ļ�������������

        OutgoingFileTransfer transfer = manager.createOutgoingFileTransfer(user); //����һ�������ļ�����

        long timeOut = 60000;   //�ļ����ͳ�ʱʱ��
        long sleepMin = 3000;
        long spTime = 0;
        int rs = 0;

        transfer.sendFile(sendFile, "jjjj");  //�����ļ�   �ļ��� �ļ�����
        rs = transfer.getStatus().compareTo(FileTransfer.Status.complete);  //�ļ�����״̬
        while(rs!=0){
            rs = transfer.getStatus().compareTo(FileTransfer.Status.complete);
            spTime = spTime + sleepMin;
            if(spTime>timeOut){return ;}
            Thread.sleep(sleepMin);
        }
    }

    public static void main(String[] args) {

    }
}
