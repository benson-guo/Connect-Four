/*Benson Guo
Ms.Dyke
June 3,2013
This class animates 3 flying ships
Name:       picture                                 c                                           picture2
Type:       Image                                   reference                                   Image
Description:variable for imported image             Points the variable to console class        variable for imported image    */
import java.applet.*;
import java.awt.*;
import hsa.Console;
import java.lang.*;

public class FlyingShips implements Runnable
{
    private Console c;
    static Image picture;
    static Image picture2;

    /*This method loads the two pictures
    Name:       tracker                                     e
    Type:       reference                                   reference
    Description:reference variable for MediaTracker Class   reference variable for exception
    */
    private static void loadImages ()
    {
	picture = Toolkit.getDefaultToolkit ().getImage ("voidray.png");
	MediaTracker tracker = new MediaTracker (new Frame ());
	tracker.addImage (picture, 0);
	try
	{
	    tracker.waitForAll ();
	}
	catch (InterruptedException e)
	{
	}
	picture2 = Toolkit.getDefaultToolkit ().getImage ("phoenix.png");
	tracker.addImage (picture2, 1);
	try
	{
	    tracker.waitForAll ();
	}
	catch (InterruptedException e)
	{
	}
    }


    /*This method is the animation for the thread, displaying 3 moving ships.
    Name:       e                                   oceanBlue                teal                       x                                           y                                   z                                       coolFont
    Type:       reference                           colour                   colour                     int                                         int                                 int                                     font
    Description:reference variable for exception    colour variable for beam colour variable for beam   for loop counter for number of animations   for loop variable for y-coordinate  for loop variable for drawing triangle  font variable
    The first for loop runs the first picture animation twice, with try structures for delay, and a for loop within with values 0-349 to change the y value of the picture. Another for loop with z=0-19 draws a triangle for the ship beam.
    The second main for loop is the y values 0-349 for the second loaded picture, with a try catch structure for delay.
    */
    public void flyingShips ()
    {
	Color oceanBlue = new Color (65, 87, 241);
	Color teal = new Color (67, 235, 235);
	Font coolFont = new Font ("Papyrus", Font.BOLD, 25);

	loadImages ();

	for (int x = 0 ; x < 2 ; x++)
	{
	    for (int y = 0 ; y < 350 ; y++)
	    {
		try
		{
		    Thread.sleep (13);
		}
		catch (InterruptedException e)
		{
		}
		c.setColor (Color.yellow);
		c.fillRect (0 + 2 * y, 100 + y, 400, 400);

		c.drawImage (picture2, 0 + 2 * y, 100 + y, null);
		c.setColor (oceanBlue);
		for (int z = 0 ; z < 20 ; z++)
		{
		    c.drawLine (135 + 2 * y, 245 + y, 210 + 2 * y, 300 + y + z);
		}
		c.setColor (teal);
		c.fillOval (180 + 2 * y, 270 + y, 100, 100);
		c.setFont (coolFont);
		c.setColor (Color.black);
		if (x == 0)
		    c.drawString ("Made", 195 + 2 * y, 325 + y);
		else
		    c.drawString ("By", 205 + 2 * y, 325 + y);
	    }
	}

	for (int y = 0 ; y < 350 ; y++)
	{
	    try
	    {
		Thread.sleep (15);
	    }
	    catch (InterruptedException e)
	    {
	    }
	    c.setColor (Color.yellow);
	    c.fillRect (0 + 2 * y, 100 + y, 640, 400);
	    c.drawImage (picture, 0 + 2 * y, 100 + y, null);
	    c.setColor (teal);
	    c.fillOval (165 + 2 * y, 248 + y, 50, 50);
	    c.fillRect (185 + 2 * y, 258 + y, 500, 30);
	    c.setFont (coolFont);
	    c.setColor (Color.black);
	    c.drawString ("Benson Guo", 235 + 2 * y, 280 + y);
	}
    }


    //This is the class constructor
    /*Name:       con
    Type:         reference
    Description:  references Console class
    */
    public FlyingShips (Console con)
    {
	c = con;
    }


    //This method runs the flyingShips animation
    public void run ()
    {
	flyingShips ();
    }
}


