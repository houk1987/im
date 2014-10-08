package session.message;

/**
 * Created by a on 2014/9/5.
 */
public class BasicHtml {

    public static String getBasicHtml() {
        StringBuffer sb = new StringBuffer("");
        sb.append("<tr> <td style=\"padding-top:2pt\" align=\"#algin#\"><table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"float:right\" >");
        sb.append("<td width=\"16\" height=\"21\" background=\"#top1url#\"  ></td>");
        sb.append("<td bubbleid=\"#messageid#\"  msgtype=\"#msgtype#\"  background=\"#top2url#\"   style=\"line-height:21pt; color:#333; font-size:12pt\">");
        sb.append("#msgtip#").append("</td>");
        sb.append("<td width=\"2pt\" background=\"#top3url#\"></td>  </tr> ");
        sb.append("<tr> <td valign=\"top\" width=\"16\"  background=\"#center1url#\" style=\"background-repeat:repeat-y\"><div background=\"#center2url#\"   style=\"width:16pt; height:13pt; background-repeat:no-repeat;\"></div></td>");
        sb.append("<td style=\"WORD-BREAK: break-all; WORD-WRAP: break-word\">#content#</td>");
        sb.append("</tr>");
        return sb.toString();
    }
}
