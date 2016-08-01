/*
  4/28/15
  William Burns
  CSCI 1302 12:30 PM Class
  Instructor Gita Phelps
  Purpose: Demonstrate usage of Phidgets
  
  I have not given a digital copy or printout of my code to anyone; however, 
  I discussed this problem with the following people: Alex Musser
  
  I am sole author of the assignment; however, I received outside help from 
  the following people: 
  
  I used the following websites as resources:phidgets.com
  Significant chunks of this program are reproduced from TEXTLCDExample.java, owing to my unfamiliarity with using Text LCD phidgets

*/

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import com.phidgets.*;
import com.phidgets.event.*;
import java.io.*;
import java.awt.image.*;
import javax.swing.JOptionPane;
import com.phidgets.TextLCDPhidget;

public class Mazerunner extends JFrame
{
 //InterfacePhidget interface;
 private JLabel label = new JLabel();
 private ImageIcon maze1 = new ImageIcon("maze1.jpg");
 private ImageIcon maze2 = new ImageIcon("maze2.jpg");
 private int xPosition;
 private int yPosition;
 private JPanel panel = new JPanel();
 private int lastDirection = 1;
 private int direction =0;
 private String victoryMessage;
 private int hours = 0;
 private int minutes = 0;
 private int seconds = 0;
 private int maze = 0;
 private Timer timer = new Timer(1000,new TimerListener());
 InterfaceKitPhidget ik;
 TextLCDPhidget output;
 
 
 public Mazerunner()
 {
  try
  {
   ik = new InterfaceKitPhidget();
   ik.openAny();
   System.out.println("Loading...");
   ik.waitForAttachment();
   System.out.println(ik.getDeviceName());
   ik.addSensorChangeListener(new MazeListener());
   
   output = new TextLCDPhidget();
   
   output.addAttachListener(new AttachListener()//code borrowed from TextLCDExample.java
   {
     public void attached(AttachEvent ae) 
     {
       System.out.println("Attachment of " + ae);
     }
   });
   
   output.addDetachListener(new DetachListener()
    {
      public void detached(DetachEvent ae) 
      {
        System.out.println("Detachment of " + ae);
      }
    });
   output.addErrorListener(new ErrorListener()
   {
     public void error(ErrorEvent ee) 
     {
      System.out.println("error event for " + ee);
     }
   });
   output.openAny();
   System.out.println("Waiting for LCD attachment...");
   output.waitForAttachment();
   if (output.getDeviceID() == TextLCDPhidget.PHIDID_TEXTLCD_ADAPTER)
   {
    output.setScreen(0);
    output.setScreenSize(output.PHIDGET_TEXTLCD_SCREEN_2x16);
    output.initialize();
   } 
  }
  catch(PhidgetException e)
  {
   System.out.println("Phidget Exception Generated");
   e.printStackTrace();
  }
  
  setSize(700,700);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  panel.setBorder(BorderFactory.createTitledBorder("Mazerunner"));
  buildMaze();
  panel.add(label);
  add(panel);
   
  setVisible(true);
 }
 public void paint(Graphics g)
 {
  do
  {
  super.paint(g);
  if (direction == 1)
   {
    g.setColor(Color.BLUE);
    g.fillRect(xPosition,yPosition,10,20);
    g.setColor(Color.WHITE);
    g.fillRect(xPosition +2,yPosition +2,6,5);
    yPosition--;
    if (yPosition == 54)
     stopCar();
    if ((yPosition == 94) && (xPosition > 85) && (xPosition < 265))//HW1 section 1 bottom side, horizontal wall 1, top to bottom
     stopCar();
    if ((yPosition == 94) && (xPosition > 350) && (xPosition < 608))//HW1 section 2 bottom side
     stopCar();
    if ((yPosition == 134) && (xPosition > 120) && (xPosition < 473))//HW2 section 1 bottom side
     stopCar();
    if ((yPosition == 134) && (xPosition > 524) && (xPosition < 568))//HW2 section 2 bottom side
     stopCar();
    if ((yPosition == 174) && (xPosition > 160) && (xPosition < 327))//HW3 section 1 bottom side
     stopCar();
    if ((yPosition == 174) && (xPosition > 362) && (xPosition < 528))//HW3 section 2 bottom side
     stopCar();
    if ((yPosition == 214) && (xPosition > 195) && (xPosition < 327))//HW4 section 1 bottom side
     stopCar();
    if ((yPosition == 214) && (xPosition > 362) && (xPosition < 488))//HW4 section 2 bottom side
     stopCar();
    if ((yPosition == 254) && (xPosition > 235) && (xPosition < 448))//HW5 bottom side
     stopCar();
    if ((yPosition == 294) && (xPosition > 250) && (xPosition < 290))//HS1 bottom side, horizontal section 1
     stopCar();
    if ((yPosition == 294) && (xPosition > 373) && (xPosition < 408))//HS2 bottom side
     stopCar();
    if ((yPosition == 327) && (xPosition > 245) && (xPosition < 290))//HS3 bottom side
     stopCar();
    if ((yPosition == 412) && (xPosition > 370) && (xPosition < 488))//HW6 bottom side
     stopCar();
    if ((yPosition == 452) && (xPosition > 248) && (xPosition < 448))//HW7 bottom side
     stopCar();
    if ((yPosition == 492) && (xPosition > 206) && (xPosition < 349))//HW8 bottom side
     stopCar();
    if ((yPosition == 492) && (xPosition > 388) && (xPosition < 488))//HW9 bottom side
     stopCar();
    if ((yPosition == 532) && (xPosition > 168) && (xPosition < 225))//HS4 bottom side
     stopCar();
    if ((yPosition == 532) && (xPosition > 270) && (xPosition < 312))//HS5 bottom side
     stopCar();
    if ((yPosition == 532) && (xPosition > 347) && (xPosition < 528))//HS6 bottom side
     stopCar();
    if ((yPosition == 572) && (xPosition > 128) && (xPosition < 225))//HS7 bottom side
     stopCar();
    if ((yPosition == 572) && (xPosition > 270) && (xPosition < 312))//HS8 bottom side
     stopCar();
    if ((yPosition == 572) && (xPosition > 344) && (xPosition < 568))//HW10 bottom side
     stopCar();
    if ((yPosition == 612) && (xPosition > 88) && (xPosition < 225))//HW11 left side bottom side
     stopCar();
    if ((yPosition == 612) && (xPosition > 270) && (xPosition < 608))//HW11 right side bottom side
     stopCar();
    if ((yPosition == 340) && (xPosition > 330) && (xPosition < 373))//victory box bottom side
    {
     stopCar();
     victoryConditionMet();
    }
    if((yPosition == 220) && (xPosition > 50) && (xPosition < 90))
     timer.start();//start the timer when the player leaves the starting box                           
                            
   }
  else if(direction == 2)
   {
    g.setColor(Color.BLUE);
    g.fillRect(xPosition,yPosition,10,20);
    g.setColor(Color.WHITE);
    g.fillRect(xPosition +2,yPosition +13,6,5);
    yPosition ++;
    if ((yPosition +20) == 655)
     stopCar();
    if (((yPosition +19) == 94) && (xPosition > 85) && (xPosition < 265))//HW1 section 1 top side
     stopCar();
    if (((yPosition +19) == 94) && (xPosition > 350) && (xPosition < 608))//HW1 section 2 top side
     stopCar();
    if (((yPosition +19) == 134) && (xPosition > 120) && (xPosition < 473))//HW2 section 1 top side
     stopCar();
    if (((yPosition +19) == 134) && (xPosition > 524) && (xPosition < 568))//HW2 section 2 top side
     stopCar();
    if (((yPosition +19) == 174) && (xPosition > 160) && (xPosition < 327))//HW3 section 1 top side
     stopCar();
    if (((yPosition +19) == 174) && (xPosition > 362) && (xPosition < 528))//HW3 section 2 top side
     stopCar();
    if (((yPosition +19) == 214) && (xPosition > 195) && (xPosition < 327))//HW4 section 1 top side
     stopCar();
    if (((yPosition +19) == 214) && (xPosition > 362) && (xPosition < 488))//HW4 section 2 top side
     stopCar();
    if (((yPosition +19) == 254) && (xPosition > 235) && (xPosition < 448))//HW5 top side
     stopCar();
    if (((yPosition +19) == 294) && (xPosition > 250) && (xPosition < 290))//HS1 top side, horizontal section 1
     stopCar();
    if (((yPosition +19) == 294) && (xPosition > 373) && (xPosition < 408))//HS2 top side
     stopCar();
    if (((yPosition +19) == 327) && (xPosition > 245) && (xPosition < 290))//HS3 top side
     stopCar();
    if (((yPosition +19) == 412) && (xPosition > 370) && (xPosition < 488))//HW6 top side
     stopCar();
    if (((yPosition +19) == 452) && (xPosition > 248) && (xPosition < 448))//HW7 top side
     stopCar();
    if (((yPosition +19) == 492) && (xPosition > 206) && (xPosition < 349))//HW8 top side
     stopCar();
    if (((yPosition +19) == 492) && (xPosition > 388) && (xPosition < 488))//HW9 top side
     stopCar();
    if (((yPosition +19) == 532) && (xPosition > 168) && (xPosition < 225))//HS4 top side
     stopCar();
    if (((yPosition +19) == 532) && (xPosition > 270) && (xPosition < 312))//HS5 top side
     stopCar();
    if (((yPosition +19) == 532) && (xPosition > 347) && (xPosition < 528))//HS6 top side
     stopCar();
    if (((yPosition +19) == 572) && (xPosition > 128) && (xPosition < 225))//HS7 top side
     stopCar();
    if (((yPosition +19) == 572) && (xPosition > 270) && (xPosition < 312))//HS8 top side
     stopCar();
    if (((yPosition +19) == 572) && (xPosition > 344) && (xPosition < 568))//HW10 top side
     stopCar();
    if (((yPosition +19) == 612) && (xPosition > 88) && (xPosition < 225))//HW11 left side top side
     stopCar();
    if (((yPosition +19) == 612) && (xPosition > 270) && (xPosition < 608))//HW11 right side top side
     stopCar();
    if ((yPosition == 345) && (xPosition > 330) && (xPosition < 373))//victory box bottom side
    {
     stopCar();
     victoryConditionMet();
    }
    if (((yPosition +19) == 270) && (xPosition > 50) && (xPosition < 90))
     timer.start();                           
   }
  else if(direction == 3)
   {
    g.setColor(Color.BLUE);
    g.fillRect(xPosition,yPosition,20,10);
    g.setColor(Color.WHITE);
    g.fillRect(xPosition + 2,yPosition + 2,5,6);
    xPosition --;
    if (xPosition == 50)//intended to establish outer bounds of maze
     stopCar();
    if ((xPosition == 90) && (yPosition > 88) && (yPosition < 614)) //VW1 left side - vertical wall 1, going left to right
     stopCar();
    if ((xPosition == 130) && (yPosition > 128) && (yPosition < 380))//VW2 top section left side
     stopCar();
    if ((xPosition == 130) && (yPosition > 420) && (yPosition < 574))//VW2 bottom section left side
     stopCar();
    if ((xPosition == 170) && (yPosition > 168) && (yPosition < 534))//VW3 left side
     stopCar();
    if ((xPosition == 210) && (yPosition > 208) && (yPosition < 494))//VW4 left side
     stopCar();
    if ((xPosition == 225) && (yPosition > 529) && (yPosition < 614))//VS1 left side - vertical segment 1, left to right
     stopCar();
    if ((xPosition == 272) && (yPosition > 529) && (yPosition < 614))//VS2 left side 
     stopCar();
    if ((xPosition == 250) && (yPosition > 248) && (yPosition < 322))//VW5 top section left side
     stopCar();
    if ((xPosition == 250) && (yPosition > 352) && (yPosition < 454))//VW5 bottom section left side
     stopCar();
    if ((xPosition == 290) && (yPosition > 284) && (yPosition < 414))//VW6 left side
     stopCar();
    if ((xPosition == 330) && (yPosition > 284) && (yPosition < 454))//VW7 left side
     stopCar();
    if ((xPosition == 373) && (yPosition > 284) && (yPosition < 414))//VW8 left side
     stopCar();
    if ((xPosition == 408) && (yPosition > 284) && (yPosition < 414))//VW9 left side
     stopCar();
    if ((xPosition == 327) && (yPosition > 168) && (yPosition < 208))//VS3 left side
     stopCar();
    if ((xPosition == 367) && (yPosition > 168) && (yPosition < 208))//VS4 left side
     stopCar();
    if ((xPosition == 350) && (yPosition > 88) && (yPosition < 128))//VS5 left side
     stopCar();
    if ((xPosition == 312) && (yPosition > 532) && (yPosition < 574))//VS6 left side
     stopCar();
    if ((xPosition == 349) && (yPosition > 484) && (yPosition < 573))//VS7 left side
     stopCar();
    if ((xPosition == 390) && (yPosition > 484) && (yPosition < 533))//VS8 left side
     stopCar();
    if ((xPosition == 448) && (yPosition > 248) && (yPosition < 374))//VW10 left side
     stopCar();
    if ((xPosition == 488) && (yPosition > 208) && (yPosition < 494))//VW11 left side
     stopCar();
    if ((xPosition == 528) && (yPosition > 168) && (yPosition < 534))//VW12 left side
     stopCar();
    if ((xPosition == 568) && (yPosition > 128) && (yPosition < 574))//VW13 left side
     stopCar();
    if ((xPosition == 608) && (yPosition > 88) && (yPosition < 614))//VW14 left side
     stopCar();                         
                  
   }
  else if(direction == 4)
   {
    g.setColor(Color.BLUE);
    g.fillRect(xPosition,yPosition,20,10);
    g.setColor(Color.WHITE);
    g.fillRect(xPosition +13,yPosition +2, 5,6);
    xPosition ++;
    if ((xPosition +20) == 650)
     stopCar();
    if(((xPosition + 18)  == 90) && (yPosition > 88) && (yPosition < 614))//VW1 right side
     stopCar();
    if (((xPosition + 18) == 130) && (yPosition > 128) && (yPosition < 380))//VW2 top section right side
     stopCar();
    if (((xPosition + 18) == 130) && (yPosition > 420) && (yPosition < 574))//VW2 bottom section right side
     stopCar();
    if (((xPosition + 18) == 170) && (yPosition > 168) && (yPosition < 534))//VW3 right side
     stopCar();
    if (((xPosition + 18) == 225) && (yPosition > 529) && (yPosition < 614))//VS1 right side
     stopCar();
    if (((xPosition + 18) == 272) && (yPosition > 529) && (yPosition < 614))//VS2 right side
     stopCar();  
    if (((xPosition + 18) == 210) && (yPosition > 208) && (yPosition < 494))// VW4 right side
     stopCar();
    if (((xPosition + 18) == 250) && (yPosition > 248) && (yPosition < 322))//VW5 top section right side
     stopCar();
    if (((xPosition + 18) == 250) && (yPosition > 352) && (yPosition < 454))//VW5 bottom section right side
     stopCar();
    if (((xPosition + 18) == 290) && (yPosition > 284) && (yPosition < 414))//VW6 right side
     stopCar();
    if (((xPosition + 18) == 330) && (yPosition > 284) && (yPosition < 454))//VW7 right side
     stopCar();
    if (((xPosition + 18) == 373) && (yPosition > 284) && (yPosition < 414))//VW8 right side
     stopCar();
    if (((xPosition + 18) == 408) && (yPosition > 284) && (yPosition < 414))//VW9 right side
     stopCar();
    if (((xPosition + 18) == 327) && (yPosition > 168) && (yPosition < 208))//VS3 right side
     stopCar();
    if (((xPosition + 18) == 367) && (yPosition > 168) && (yPosition < 208))//VS4 right side
     stopCar();
    if (((xPosition + 18) == 350) && (yPosition > 88) && (yPosition < 128))//VS5 right side
     stopCar();
    if (((xPosition + 18) == 312) && (yPosition > 532) && (yPosition < 574))//VS6 right side
     stopCar();
    if (((xPosition + 18) == 349) && (yPosition > 484) && (yPosition < 573))//VS7 right side
     stopCar();
    if (((xPosition + 18) == 390) && (yPosition > 484) && (yPosition < 533))//VS8 right side
     stopCar();
    if (((xPosition + 18) == 448) && (yPosition > 248) && (yPosition < 374))//VW10 right side
     stopCar();
    if (((xPosition + 18) == 488) && (yPosition > 208) && (yPosition < 494))//VW11 right side
     stopCar();
    if (((xPosition + 18) == 528) && (yPosition > 168) && (yPosition < 534))//VW12 right side
     stopCar();
    if (((xPosition + 18) == 568) && (yPosition > 128) && (yPosition < 574))//VW13 right side
     stopCar();
    if (((xPosition + 18) == 608) && (yPosition > 88) && (yPosition < 614))//VW14 right side
     stopCar();                      
   }      
  }while (!(direction == 0));
  if (direction == 0)
  { 
   if(lastDirection == 1)
   {
    g.setColor(Color.BLUE);
    g.fillRect(xPosition,yPosition,10,20);
    g.setColor(Color.WHITE);
    g.fillRect(xPosition +2,yPosition +2,6,5);
   }
   else if(lastDirection == 2)
   {
    g.setColor(Color.BLUE);
    g.fillRect(xPosition,yPosition,10,20);
    g.setColor(Color.WHITE);
    g.fillRect(xPosition +2,yPosition +13,6,5);
   }
   else if(lastDirection == 3)
   {
    g.setColor(Color.BLUE);
    g.fillRect(xPosition,yPosition,20,10);
    g.setColor(Color.WHITE);
    g.fillRect(xPosition + 2,yPosition + 2,5,6);
   }
   else if(lastDirection == 4)
   {
    g.setColor(Color.BLUE);
    g.fillRect(xPosition,yPosition,20,10);
    g.setColor(Color.WHITE);
    g.fillRect(xPosition +13,yPosition +2, 5,6);
   }  
  } 
  
 }
 public void buildMaze()
 {
  if (maze == 0)
   label.setIcon(maze1);
  else if(maze == 1)
   label.setIcon(maze2); 
  xPosition = 65;
  yPosition = 230;
 }
 public void stopCar()//stops car when it hits bounds and moves them back slightly
 {
  direction = 0;
  if(lastDirection == 1)
   yPosition ++;
  else if(lastDirection == 2)
   yPosition --;
  else if(lastDirection == 3)
   xPosition ++;
  else if(lastDirection == 4)
   xPosition --;   
  repaint();
 }
 public void victoryConditionMet()
 {
 timer.stop();
 try
 {
  output.setContrast(90);
  output.setBacklight(true);
  output.setCursorBlink(true);
  output.setCursor(true);
  output.setDisplayString(0,"Congratulations!");
  victoryMessage = JOptionPane.showInputDialog("Congratulations! You completed the maze in " + hours + " hours, " + minutes + " minutes, and " +
   seconds + "seconds. Would you like a more difficult challenge? Enter yes or no.");
  if (victoryMessage.equalsIgnoreCase("Yes"))
  {
   direction = 0;
   lastDirection = 1;
   maze = 1;
   buildMaze();
  }
  else if(victoryMessage.equalsIgnoreCase("no"))
  {
   JOptionPane.showMessageDialog(null,"Thank you for playing. Have a nice day!");
   output.close();
   output = null;
  }
  else 
   throw new Exception(); 
 }
 catch(Exception j)
  {
   JOptionPane.showMessageDialog(null,"Invalid Entry. Please enter yes or no.");
  }  
 }
 private class TimerListener implements ActionListener
 {
  public void actionPerformed(ActionEvent e)
  {
   seconds ++;
   if (seconds == 60)
   {
    minutes++;
    seconds = 0;
    if(minutes == 60)
    {
     hours ++;
     minutes = 0;
     seconds = 0;
    }
   }
  }
 }
 public class MazeListener implements SensorChangeListener
 {
  public void sensorChanged(SensorChangeEvent j)
  {
   int port = j.getIndex();
   int value = j.getValue();
   
   if (port == 0)
   {
    if(value >= 700)
    {
     direction = 4;
     lastDirection = 4;
     repaint();
    } 
    else if (value <= 300)
    {
     direction = 3;
     lastDirection = 3;
     repaint();
    }
    else
    {
    direction = 0;
    repaint();
    } 
   }
   else if (port == 1)
   {
    if(value >= 700)
    {
     direction = 2;
     lastDirection = 2;
     repaint();
    } 
    else if(value <= 300)
    {
     direction = 1;
     lastDirection = 1;
     repaint();
    }
    else
    {
    direction = 0;
    repaint();
    }   
  
   }
  }
 }
/*public Class AttachListener 
{
 public void attached(AttachEvent e)
 {
  System.out.println("Attachment of " + e);
 }
}
public Class DetachListener
{
 public void detached(AttachEvent j)
 {
  System.out.println("Attachment of " + j);
 }
}
public Class ErrorListener
{
 public void error(ErrorEvent k)
 {
  System.out.println("Error event for " + k);
 }
}*/ 
public static void main(String [] args)
 {
  new Mazerunner();
 }
}
