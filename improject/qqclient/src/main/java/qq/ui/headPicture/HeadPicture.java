package qq.ui.headPicture;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import com.resource.ImageUtils;
import com.component.jlabel.JLabelFactory;
import com.resource.ConfigurationRes;

public class HeadPicture extends JLayeredPane {

	private static final long serialVersionUID = 1L;
	public Pricture pic;

	public  HeadPicture (String path){
		pic = new Pricture(path);
		pic.setBounds(1,2, 43, 43);
		this.moveToFront(pic);
		this.add(pic);
        JLabel boxLabel = JLabelFactory.createJLabel(ImageUtils.getInstance("common/").getImageIcon("picbox.png"));
		boxLabel.setLocation(0,0);
		this.add(boxLabel);
		this.moveToBack(boxLabel);
        this.setSize(boxLabel.getSize());
	}
		  
		  public static void main(String[] args) {
				final HeadPicture animation=new HeadPicture(ConfigurationRes.getPath()+"common/headItem1.png");
			    JFrame frame = new JFrame("sdsa");
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
			    frame.add(animation);
			    frame.getContentPane().setBackground(Color.BLACK);
			    JPanel panel=new JPanel();
			    panel.setLayout(new BorderLayout());
			    JButton start=new JButton("start");
			    start.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						animation.pic.start();
					}
				});
			    
			    JButton stop=new JButton("stop");
			    stop.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						animation.pic.stop();
					}
				});
			    
			    panel.add(start,BorderLayout.WEST);
			    panel.add(stop,BorderLayout.EAST);
			    
			    frame.add(panel,BorderLayout.SOUTH);
			    
			    frame.setSize(350, 250);
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);
			  }
}

