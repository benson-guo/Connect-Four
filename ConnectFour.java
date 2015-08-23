/*Benson Guo
Ms.Dyke
June 3,2013
This program is two player connect 4.
Name:       choice                               c                                      line                                    player1                 player2                 ROW
Type:       string                               reference                              String                                  String                  String                  final int
Description:Stores the choice the user wants     Points the variable to console class   Stores the line in the text document    Stores player 1's name  Stores player 2's name  Stores the number of rows of connectboard

Name:       COLUMN                                          connectBoard                        output                                                input
Type:       final int                                       int                                 reference                                             reference
Description:Stores the number of columns in connectboard    2-d integer array for connectboard  Reference variable for PrintWriter class              Reference variable for BufferedReader class
*/
import java.applet.*;
import java.awt.*;
import hsa.*;
import java.io.*;

public class ConnectFour
{
    Console c;
    String line = "";
    String choice;
    String player1;
    String player2;
    static final int ROW = 6;
    static final int COLUMN = 7;
    int[] [] connectBoard = new int [ROW] [COLUMN];
    PrintWriter output;
    BufferedReader input;

    /*This private method clears the screen and displays the title, which is called in every method. Additionally, there is an parameter for the colour of the title.
    Name:        lightGreen                 brown                           forestGreen                     pink                            orange
    Type:        color                      color                           color                           color                           color
    Description: color variable for text    color variable for text         color variable for text         color variable for text         color variable for wave's text

    Name:        lightBlue                  Cyan                            neonGreen                       coolFont                        color
    Type:        Color                      Color                           Color                           Font                            String
    Description: color variable for text    color variable for text         color variable for text         font variable for text          parameter pass for color
    There is an if statement that changes the colour according to the string sent in.
    */
    private void drawTitle (String color)
    {
	Font coolFont = new Font ("Papyrus", Font.BOLD, 25);
	Color lightGreen = new Color (29, 230, 6);
	Color brown = new Color (210, 167, 66);
	Color forestGreen = new Color (120, 185, 111);
	Color pink = new Color (255, 128, 128);
	Color orange = new Color (255, 91, 13);
	Color lightBlue = new Color (81, 81, 255);
	Color cyan = new Color (1, 190, 186);
	Color neonGreen = new Color (128, 255, 0);

	c.clear ();
	c.setColor (Color.yellow);
	c.fillRect (0, 0, 640, 500);
	c.setTextBackgroundColor (Color.yellow);
	if (color.equals ("lightGreen"))
	    c.setColor (lightGreen);
	else if (color.equals ("brown"))
	    c.setColor (brown);
	else if (color.equals ("forestGreen"))
	    c.setColor (brown);
	else if (color.equals ("pink"))
	    c.setColor (pink);
	else if (color.equals ("lightBlue"))
	    c.setColor (lightBlue);
	else if (color.equals ("cyan"))
	    c.setColor (cyan);
	else if (color.equals ("neonGreen"))
	    c.setColor (neonGreen);
	else
	    c.setColor (orange);

	c.setFont (coolFont);
	c.drawString ("Connect Four", 232, 40);
	c.println ();
	c.setCursor (4, 1);
    }


    /* This private method pauses the program until the user presses any key. The program is overloaded different statements can be displayed for different occasions
    Name:         end
    Type:         String
    Description:  parameter pass for pauseprogram sentence
    */
    private void pauseProgram (String end)
    {
	c.println ("Please press any key to " + end + "...");
	c.getChar ();
    }


    /*This private return method checks the game to see if the game has been won. If it has, it will return the player name, if not, it will return "".
    Name:        player1                         player2                             connectBoard                                    x                               y
    Type:        String                          String                              int                                             int                             int
    Description: parameter pass for first player parameter pass for second player    parameter pass for connectboard integer array   for loop variable for column    for loop variable for row
    The two player names are sent in along with the integer array that stores the connect four board information. Checking for a winner is done through a for loop going through the columns
    with variable x=0-6. Nested is a for loop going through every y value for every x value, y=0-5.This will check every position in the 6x7 array.
    There are four if statements in the for loop that will check 4 different win positions; horizontally, vertically, and diagonally (bottom life-top right/top left to bottom right)
    These positions are checked based on the x and y values to make sure it the program doesn't check out of the array range causing an out of bounds error.
    Within each of these if statements, 4 positions in a row are added up. If the sum is 4, the first player has won and it will return the firstplayer name, or if the sum is 20 it will return the second player name.
    This works because a player 1 checker in the array will have a value of 1, so four 1's will have a sum of 4. Player 2 checker's have a value of 5 so it will sum to 20.
    After the for loop has passed, all positions have been checked for a win so the method will return "" because there is no winner.
    */
    private String winCheck (String player1, String player2, int[] [] connectBoard)
    {
	for (int x = 0 ; x < 7 ; x++)
	{
	    for (int y = 0 ; y < 6 ; y++)
	    {
		if (x < 4 && y < 3)
		{
		    if (connectBoard [y] [x] + connectBoard [y + 1] [x + 1] + connectBoard [y + 2] [x + 2] + connectBoard [y + 3] [x + 3] == 4)
			return player1;
		    else if (connectBoard [y] [x] + connectBoard [y + 1] [x + 1] + connectBoard [y + 2] [x + 2] + connectBoard [y + 3] [x + 3] == 20)
			return player2;
		}
		if (x > 2 && y < 3)
		{
		    if (connectBoard [y] [x] + connectBoard [y + 1] [x - 1] + connectBoard [y + 2] [x - 2] + connectBoard [y + 3] [x - 3] == 4)
			return player1;
		    else if (connectBoard [y] [x] + connectBoard [y + 1] [x - 1] + connectBoard [y + 2] [x - 2] + connectBoard [y + 3] [x - 3] == 20)
			return player2;
		}
		if (y > 2)
		{
		    if (connectBoard [y] [x] + connectBoard [y - 1] [x] + connectBoard [y - 2] [x] + connectBoard [y - 3] [x] == 4)
			return player1;
		    else if (connectBoard [y] [x] + connectBoard [y - 1] [x] + connectBoard [y - 2] [x] + connectBoard [y - 3] [x] == 20)
			return player2;
		}
		if (x > 2)
		{
		    if (connectBoard [y] [x] + connectBoard [y] [x - 1] + connectBoard [y] [x - 2] + connectBoard [y] [x - 3] == 4)
			return player1;
		    else if (connectBoard [y] [x] + connectBoard [y] [x - 1] + connectBoard [y] [x - 2] + connectBoard [y] [x - 3] == 20)
			return player2;
		}
	    }
	}
	return "";
    }


    /*adds the FlyingShips thread to MyCreation
    Name:       b
    Type:       reference
    Description points b to FlyingShips
    */
    private void flyingShips ()
    {
	FlyingShips b = new FlyingShips (c);
	b.run ();
    }


    //This method is the introduction screen and it describes the program to the user. The flyingShips thread is executed and the program proceeds to mainMenu when done.
    public void splashScreen ()
    {
	drawTitle ("lightGreen");
	c.println ("In this program, you can play 2-player connect four!");
	flyingShips ();
    }


    /*This method is the mainmenu and the user can choose to either view instructions, highscore, play, or quit based on the string choice.
    Name:        e                                              f
    Type:        reference                                      reference
    Description: reference variable for exception object        reference variable for exception object
    The try catch structure in the beginning opens the file Highscore.txt. If it doesn't exist, IOException will catch it and another try catch structure creates the file.
    If the file does exist, the if statement checks the first line for the correct header. If it's not there, a new file will be created with the correct header.
    The while loop loops asking the question for their choice until they have inputed a valid chocie.
    */
    public void mainMenu ()
    {
	try
	{
	    input = new BufferedReader (new FileReader ("Highscore.txt"));
	    line = input.readLine ();
	    if (!line.equals ("Highscore-Top 10"))
	    {
		try
		{
		    output = new PrintWriter (new FileWriter ("Highscore.txt"));
		    output.println ("Highscore-Top 10");
		    output.close ();
		}
		catch (IOException f)
		{
		}
	    }
	}
	catch (IOException e)
	{
	    try
	    {
		output = new PrintWriter (new FileWriter ("Highscore.txt"));
		output.println ("Highscore-Top 10");
		output.close ();
	    }
	    catch (IOException f)
	    {
	    }
	}
	drawTitle ("orange");
	c.println ("1. Instructions");
	c.println ("2. Play");
	c.println ("3. Top 10 Scores");
	c.println ("4. Exit");
	c.println ();
	while (true)
	{
	    c.setCursor (9, 1);
	    c.println ();
	    c.setCursor (9, 1);
	    c.print ("What would you like to do? ");
	    choice = c.readLine ();
	    if (choice.equals ("1") || choice.equals ("2") || choice.equals ("3") || choice.equals ("4") || checkName (choice) == false)
		break;
	    new Message ("Please enter a valid choice!", "Error!");
	}
    }


    /*This is the instructions method and it uses a for loop (8 times, 0-7) to read of the lines of Instructions.txt (contains isntructions) within a try/catch structure. This user presses any key to continue back to mainMenu.
    Name:        x                                          e
    Type:        int                                        reference
    Description: for loop variable for reading textfile     reference variable for exception object
    */
    public void instructions ()
    {
	drawTitle ("pink");
	try
	{
	    input = new BufferedReader (new FileReader ("Instructions.txt"));
	    for (int x = 0 ; x < 8 ; x++)
	    {
		c.println (input.readLine ());
	    }
	}
	catch (IOException e)
	{
	}
	c.println ();
	pauseProgram ("continue");
    }


    /*This method is the method and it displays the top 10 scores to the user. The user presses any key to go back to mainMenu.
    Name:        picture                            e                                           tracker                         x
    Type:        Image                              reference                                   reference                       int
    Description: image variable for imported image  reference variable for exception object     points to MediaTracker class    for loop variable for reading text document
    The first try catch imports the picture. Then, a try catch reads the Highscore.txt file and displays the highscores from highest to lowest with a for loop
    starting from 1 and ending at 10, breaking if there is no more highscores in the file to display.
    */
    public void highScore ()
    {
	Image picture;

	drawTitle ("neonGreen");

	picture = Toolkit.getDefaultToolkit ().getImage ("grandmaster.png");
	MediaTracker tracker = new MediaTracker (new Frame ());
	tracker.addImage (picture, 1);
	try
	{
	    tracker.waitForAll ();
	}
	catch (InterruptedException e)
	{
	}
	c.drawImage (picture, 160, 335, null);
	try
	{
	    input = new BufferedReader (new FileReader ("Highscore.txt"));
	    line = input.readLine ();
	}
	catch (IOException e)
	{
	}
	c.println (line);
	c.println ();
	for (int x = 1 ; x < 11 ; x++)
	{
	    try
	    {
		line = input.readLine ();
		if (line.equals (""))
		    break;
		c.print (x + ". " + line);
		line = input.readLine ();
		c.println ("-" + line + " points");
	    }
	    catch (IOException e)
	    {
	    }
	    catch (NullPointerException e)
	    {
	    }
	}
	c.println ();
	pauseProgram ("continue");
    }


    /*This return method returns whether or not a string is all spaces or not, checking the string passed as a parameter
    Name:        name                                       x
    Type:        String                                     int
    Description: parameter variable for string to check     for loop variable for each character of string
    The for loop goes through all characters of the string, if an ascii value is not equal to space, it will return false, or, the method will return true after the for loop.*/
    private boolean checkName (String name)
    {
	for (int x = 0 ; x < name.length () ; x++)
	{
	    if ((int) name.charAt (x) != 32)
		return false;
	}
	return true;
    }


    /*
    This method is where the user is asked to enter player 1's name and player 2's name.
    The while loop repeats until the user enter's a valid name. An if statement checks that is not longer than 20 characters long.
    A for loop runs through every letter of the name and if there is a character other then space, it adds to counter. If counter stays at zero, it will ask for a new name.
    The second while loop asks and checks for the second player's name. It cannot be equal to the first player's name.
    */
    public void askData ()
    {
	drawTitle ("cyan");

	c.println ("Please enter the player names (max 20 letters).");
	while (true)
	{
	    c.setCursor (6, 1);
	    c.println ();
	    c.setCursor (6, 1);
	    c.print ("Player 1: ");
	    player1 = c.readLine ();
	    if (checkName (player1) == false && player1.length () < 21)
		break;
	    else if (player1.length () > 20)
		new Message ("Your name must not be longer than 20 characters!", "Error");
	    else
		new Message ("You cannot have a blank name!", "Error");
	}

	while (true)
	{
	    c.setCursor (8, 1);
	    c.println ();
	    c.setCursor (8, 1);
	    c.print ("Player 2: ");
	    player2 = c.readLine ();
	    if (checkName (player2) == false && !player2.equalsIgnoreCase (player1) && player2.length () < 21)
		break;
	    else if (player2.equalsIgnoreCase (player1))
		new Message ("You cannot have the same name as player 1!", "Error");
	    else if (player2.length () > 20)
		new Message ("Your name must not be longer than 20 characters!", "Error");
	    else
		new Message ("You cannot have a blank name!", "Error");
	}
    }


    /*
    This method is where the connect 4 game is played. Players 1 and 2 alternate until one player wins, which is checked with winCheck. Then, the user presses any key to go back to mainMenu.
    Name:        numstr                     columnTracker                               column              columnsFull                 turnCounter         x                                                   y
    Type:        string                     int                                         int                 int                         int                 int                                                 int
    Description: stores the user input      Array for the number of checkers in column  stores column       stores # of columns full    stores turn         for loop variable for column and x-coordinate       for loop variable for row and y-coordinate

    Name:        score                      newFont                 coolFont                oceanBlue                   lightGreen                          red                                                 z                                                   e
    Type:        int                        font                    font                    color                       color                               color                                               int                                                 reference
    Description: stores the score of player font variable for text  font variable for text  color variable for board    color variable for green checker    color variable for red checker                      for loop variable to keep track of players turn     reference variable for exception object
    The nested for loop with x=115-475 and y=115-475 going at an increment of 60 draws the holes of the connectboard. The next nested for loop with x=0-6 and y=0-5 resets all positions of the int array to 0.
    The while loop repeats the two player move process until a player has won. Within the loop is a for loop that goes from 0 to 1, 0 for red players turn and 1 for green.
    In the for loop, a for loop from 0-6 checks all the columns to see if there is a tie game or not (all columns are full). If not, the program proceeds to change the colour to green or red based on the if statement that
    checks the main for loop is at 0 or 1. This is proceeded with a while loop that will ask for a column until the user has entered a valid column that is not full. A try catch checks for number input and if statements error
    trap for blank spaces, and non-existant columns. After, the return method winCheck is called to check for a winner. If there is, the program will break out of the main forloop and the while loop outside.
    */
    public void display ()
    {
	int[] columnTracker = new int [COLUMN];
	int column;
	int columnsFull = 0;
	int turnCounter = 1;
	int score;
	String numstr;
	Font newFont = new Font ("Arial", Font.BOLD, 17);
	Font coolFont = new Font ("Papyrus", Font.BOLD, 25);
	Color oceanBlue = new Color (0, 0, 157);
	Color lightGreen = new Color (29, 230, 6);
	Color red = new Color (255, 4, 4);

	drawTitle ("lightBlue");
	c.setFont (newFont);
	c.setColor (red);
	c.drawString ("P1-" + player1, 0, 17);
	c.setColor (lightGreen);
	c.drawString ("P2-" + player2, 0, 34);
	c.setColor (Color.black);
	c.fillRect (110, 470, 420, 30);
	c.setColor (oceanBlue);
	c.fillRect (110, 110, 420, 360);
	c.setColor (Color.yellow);
	for (int y = 115 ; y < 475 ; y = y + 60)
	{
	    for (int x = 115 ; x < 535 ; x = x + 60)
	    {
		c.fillOval (x, y, 50, 50);
	    }
	}
	c.setFont (coolFont);
	c.drawString ("1", 135, 493);
	c.drawString ("2", 190, 493);
	c.drawString ("3", 250, 493);
	c.drawString ("4", 310, 493);
	c.drawString ("5", 370, 493);
	c.drawString ("6", 430, 493);
	c.drawString ("7", 490, 493);

	for (int x = 0 ; x < 7 ; x++)
	{
	    for (int y = 0 ; y < 6 ; y++)
	    {
		connectBoard [y] [x] = 0;
	    }
	}

	while (true)
	{
	    for (int z = 0 ; z < 2 ; z++)
	    {

		columnsFull = 0;
		for (int x = 0 ; x < 7 ; x++)
		{
		    if (columnTracker [x] == 6)
			columnsFull++;
		}
		if (columnsFull > 6)
		    break;
		if (z == 0)
		    c.setColor (red);
		else
		    c.setColor (lightGreen);
		c.fillOval (25, 440, 60, 60);
		while (true)
		{
		    try
		    {
			c.setCursor (4, 1);
			c.println ();
			c.println ();
			c.setCursor (4, 1);
			if (z == 0)
			    c.print (player1 + ", which column would you like to drop the checker in? ");
			else
			    c.print (player2 + ", which column would you like to drop the checker in? ");
			numstr = c.readLine ();
			column = Integer.parseInt (numstr);
			if (column < 1 || column > 7 || checkName (numstr) == true)
			    new Message ("Please choose a valid column!", "Error");
			else if (columnTracker [column - 1] > 5)
			    new Message ("Please choose a column that isn't full!", "Error");
			else
			{
			    if (z == 0)
				connectBoard [columnTracker [column - 1]] [column - 1] = 1;
			    else
				connectBoard [columnTracker [column - 1]] [column - 1] = 5;
			    columnTracker [column - 1] = columnTracker [column - 1] + 1;
			    c.fillOval (55 + column * 60, 475 - columnTracker [column - 1] * 60, 50, 50);
			    break;
			}
		    }
		    catch (NumberFormatException e)
		    {
			new Message ("Please enter a valid column!", "Error");
		    }
		}
		if (winCheck (player1, player2, connectBoard).equals (player1) || winCheck (player1, player2, connectBoard).equals (player2))
		    break;
		if (columnsFull == 7)
		    break;
	    }
	    if (winCheck (player1, player2, connectBoard).equals (player1) || winCheck (player1, player2, connectBoard).equals (player2))
		break;
	    if (columnsFull == 7)
		break;
	    turnCounter++;
	}
	c.setCursor (4, 1);
	if (columnsFull == 7)
	    c.println ("The game has ended in a draw!");
	else
	{
	    c.println (winCheck (player1, player2, connectBoard) + " has won the game in " + turnCounter + " moves!");
	    score = (22 - turnCounter) * 10;
	    highscoreSorter (winCheck (player1, player2, connectBoard), score);
	}
	pauseProgram ("continue");
    }


    /*  This private method sorts the highscore file. There is a parameter pass for the most recent winner and winnerscore.
	Name:        winner                     score                       highscorePositions                                  scorePosition                                               e
	Type:        string                     int                         String                                              int                                                         reference
	Description: stores winner name         stores the score of winner  Array of strings of name and score for highscore    stores the current position in highscore during sorting     reference variable for exception object

	Name:        x                                      y
	Type:        int                                    int
	Description: for loop variable for array position   for loop variable for reading lines from Highscore.txt
	This method modifies the highscore file so everything is inside a try catch structure to catch for IOEXceptions. A while loop repeats 10 times, constructing the highscore array which alternates between playername and score.
	If the line read is null or blank, there is space on highscores for the current score so it is added to the highscore array.
	If the line is not blank, the winnerScore is compared to the current position in the highscore, replacing it if it is greater. Then a for loop will store the rest of the values for the array.
	If the winnerScore is not greater, the current score and name in the highscore position is added to the array. Finally, the highscore array is printed onto the highscore file, with a for loop going from 0-19, and breaking if there is no value in the array.
    */
    private void highscoreSorter (String winner, int score)
    {
	final int highscorePositions = 20;
	String highscore[] = new String [highscorePositions];
	int scorePosition = 0;

	try
	{
	    input = new BufferedReader (new FileReader ("Highscore.txt"));
	    line = input.readLine ();
	    while (scorePosition < 20)
	    {
		line = input.readLine ();
		if (line == null || line.equals (""))
		{
		    highscore [scorePosition] = winner;
		    highscore [scorePosition + 1] = Integer.toString (score);
		    break;
		}
		highscore [scorePosition] = line;
		line = input.readLine ();
		if (score > Integer.parseInt (line))
		{
		    String previousName = highscore [scorePosition];
		    String previousScore = line;
		    highscore [scorePosition] = winner;
		    highscore [scorePosition + 1] = Integer.toString (score);
		    highscore [scorePosition + 2] = previousName;
		    highscore [scorePosition + 3] = previousScore;
		    for (int y = scorePosition + 4 ; y < 20 ; y = y + 2)
		    {
			line = input.readLine ();
			if (line == null)
			    break;
			highscore [y] = line;
			line = input.readLine ();
			highscore [y + 1] = line;
		    }
		    break;
		}
		highscore [scorePosition + 1] = line;
		scorePosition = scorePosition + 2;
	    }
	    output = new PrintWriter (new FileWriter ("Highscore.txt"));
	    output.println ("Highscore-Top 10");
	    for (int x = 0 ; x < 20 ; x++)
	    {
		if (highscore [x] == null)
		    break;
		output.println (highscore [x]);
	    }
	    output.close ();
	}
	catch (IOException e)
	{
	}
    }


    /*This method is the end screen and the user presses any key to close the window after the animation in the thread is executed.
    Name:        random                             oceanBlue                   lightGreen                          red                             counter                                     boldFont
    Type:        color                              color                       color                               color                           int                                         font
    Description: color variable for clearing screen color variable for board    color variable for green checker    color variable for red checker  keeps track of number of checkers played    font variable for text
    =
    Name:        x                                      y                                         e
    Type:        int                                    int                                       reference
    Description: for loop variable for x-coordinate     for loop variable for y-coordinate        reference variable for exception object
    The animation is a quick game of connect four being player. The nested for loop with x=115-475 and y=115-475 going at an increment of 60 draws the holes of the connectboard.
    A while loop is the animation for the checkers being played, breaking after 7 moves and a try catch for delaying checkers.
    The last for loop clears the screen with a gradient color made of lines, using a changing color variable random.
    */
    public void goodBye ()
    {
	int counter = 0;
	Font boldFont = new Font ("Arial", Font.BOLD, 72);
	Color oceanBlue = new Color (0, 0, 157);
	Color lightGreen = new Color (29, 230, 6);
	Color red = new Color (255, 4, 4);

	drawTitle ("forestGreen");
	c.println ("Thank you for playing connect four!");
	c.println ("This program was made by Benson Guo.");
	c.println ();

	c.setColor (Color.black);
	c.fillRect (110, 470, 420, 30);
	c.setColor (oceanBlue);
	c.fillRect (110, 110, 420, 360);
	c.setColor (Color.yellow);
	for (int y = 115 ; y < 475 ; y = y + 60)
	{
	    for (int x = 115 ; x < 535 ; x = x + 60)
	    {
		c.fillOval (x, y, 50, 50);
	    }
	}
	c.drawString ("1", 135, 493);
	c.drawString ("2", 190, 493);
	c.drawString ("3", 250, 493);
	c.drawString ("4", 310, 493);
	c.drawString ("5", 370, 493);
	c.drawString ("6", 430, 493);
	c.drawString ("7", 490, 493);
	while (true)
	{
	    counter++;
	    c.setColor (red);
	    c.fillOval (115 + counter * 60, 415, 50, 50);
	    try
	    {
		Thread.sleep (700);
	    }
	    catch (Exception e)
	    {
	    }
	    if (counter == 4)
		break;
	    c.setColor (lightGreen);
	    c.fillOval (115 + counter * 60, 355, 50, 50);
	    try
	    {
		Thread.sleep (700);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int y = 250 ; y > 0 ; y--)
	{
	    Color random = new Color (y, 211, 25);
	    c.setColor (random);
	    c.drawLine (0, 2 * y, 640, 2 * y);

	    try
	    {
		Thread.sleep (12);
	    }
	    catch (Exception e)
	    {
	    }
	    c.setColor (random);
	    c.drawLine (0, 2 * y + 1, 640, 2 * y + 1);
	}
	c.setColor (Color.black);
	c.setFont (boldFont);
	c.drawString ("Connect Four!", 75, 220);
	c.setCursor (24, 1);
	pauseProgram ("exit");
	c.close ();
    }


    //This is the class constructor
    public ConnectFour ()
    {
	c = new Console ("Connect Four");
    }


    /*This is the main method and controls the flow of the program
    Name:        a
    Type:        reference
    Description: points variable to PrimeNumber class
    A while loop loops the main menu and the if statement directs the program to the users choice
    */
    public static void main (String[] args)
    {
	ConnectFour a = new ConnectFour ();
	a.splashScreen ();
	while (true)
	{
	    a.mainMenu ();
	    if (a.choice.equals ("1"))
		a.instructions ();
	    else if (a.choice.equals ("2"))
	    {
		a.askData ();
		a.display ();
	    }
	    else if (a.choice.equals ("3"))
		a.highScore ();
	    else if (a.choice.equals ("4"))
		break;
	}
	a.goodBye ();
    }
}


