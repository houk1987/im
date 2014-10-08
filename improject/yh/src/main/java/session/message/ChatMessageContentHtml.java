package session.message;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by a on 2014/9/5.
 */
public class ChatMessageContentHtml{
    public String getContentHtml() {
        String html = BasicHtml.getBasicHtml();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateString = formatter.format(sessionMessage.getSendTime());
//        html = html
//                .replaceAll("#algin#", "left")
//                .replaceAll("#msgtip#", sessionMessage.getFrom() + "  " + dateString)
//                .replaceAll("#content#", sessionMessage.getContent());
//        replaceEmotion(sessionMessage.getContent());
//        System.out.println(sessionMessage.getContent());
        return html;
    }

    /**
     * Ìæ»»±íÇé
     */
    public void replaceEmotion(String content){
        String currentDirPath = null;
        try {
            currentDirPath = new File(".").getCanonicalPath();
            String path =new File(currentDirPath).toURI().toURL().toString();
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
