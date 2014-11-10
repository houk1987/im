package com.ui.headPicture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pricture extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	public Timer timer;
	BufferedImage image;
	int x,y,width,height;
	boolean flag=false;
	boolean id=true;
	int in=0;

   public  Pricture (String path){
		try {
		    image=ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setOpaque(false);
		timer = new Timer(200, this);
		timer.setInitialDelay(190);	
	}
	
	 public void start(){
		  flag=true;
		  timer.start();
		  this.setLocation(1, 3);
		  
	  }
	  
	  public void stop(){
		  flag=false;
		  timer.stop();
		  this.setLocation(1, 2);
	  }
	  
	  public void paint(Graphics g) {
		    super.paintComponent(g);
		    Graphics2D g2 = (Graphics2D) g;
		    width=image.getWidth();
		    height=image.getHeight();
		    //首先计算出图像中间位置的坐标点
		    if(!flag){
		    	x=getWidth()/2-image.getWidth()/2;
		    	y=getHeight()/2-image.getHeight()/2;
		    	g2.drawImage(image, x, y, width, height,null);
		    }else{
		    	int imageX = x,imageY = y;
		    	switch(in){
		    	case 0:
		    		imageX=x-1;
		    		imageY=y;
		    		if(id){
		    			in++;
		    		}else{
		    			id=!id;
		    		}
		    		break;
		    	case 1:
		    		imageX=x;
		    		imageY=y-1;
		        	if(id){
		    			in++;
		    		}else{
		    			in--;
		    		}
		        	break;
		    	case 2:
		    		imageX=x+1;
		    		imageY=y;
		    		if(id){
		    			id=!id;
		    		}else{
		    			in--;
		    		}
		    	}
		    	g2.drawImage(image, imageX, imageY, width, height,null);
		    }
		  }

		  public void actionPerformed(ActionEvent e) {
		     repaint();
		  }
		  
		  public void setUserPicture(String path){
			  try {
				this.removeAll();
				image=ImageIO.read(new File(path));
				this.repaint();
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
}
