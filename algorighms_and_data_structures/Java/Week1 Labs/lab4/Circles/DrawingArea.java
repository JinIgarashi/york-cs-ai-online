 
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public class DrawingArea extends JPanel {
	
     // You only need to edit the method below.
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      // the size of the circle
      int size = 350;
      
      
      Scanner sc=new Scanner(System.in);
	    
      System.out.println("Enter the number of circle you want to draw ");
      int numberCircles = sc.nextInt();
      
      sc.close();
      
      // You might want to introduce a loop somewhere here.
      int count = 0;
      do {
    	  Random rand = new Random();
    	  float red = rand.nextFloat();
    	  float green = rand.nextFloat();
    	  float blue = rand.nextFloat();
          Color c = new Color(red, green, blue);
          g.setColor(c);
          
          int xOffset = (int)(Math.random()* 201) - 100;
          int yOffset = (int)(Math.random()* 201) - 100;
          
          g.drawOval(200-size/2 + xOffset,200-size/2 + yOffset,size,size);
    	  
          int randomSize = (int)(Math.random()*50)+1;
          
          size -= randomSize;
    	  count++;
      } while (count < numberCircles);

    }// the end of the method....
    
    public static void main(String args[]) {
    	Circles c=new Circles();
    	c.startCircles();
    }
}