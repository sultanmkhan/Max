import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;


public class ScoreBoard extends Actor
{
    private static final String livesLabel = "Lives: "; //This will be displayed on the scoreboard before the number of lives
    
    private GreenfootImage box; //This is the background for the scoreboard
    
    /**
     * This will create the scoreboard background box
     */
    public ScoreBoard ()
    {
        box = new GreenfootImage (60, 30);
        
        box.setColor(new Color (0f,0f,0f,0.5f));
        box.fillRect (0,0, 60, 30);
        updateScore (0);
    }
    
    /**
     * This will update the score on the scoreboard.
     * This makes the scoreboard display yellow text with the lives label and the lives as well as the ammunition label and the ammunition below it
     */
    public void updateScore (int lives)
    {
        GreenfootImage img = new GreenfootImage (box);
        
        img.setColor (Color.YELLOW);
        img.drawString(livesLabel + lives, 5, 12);
       
        
        setImage (img);
    }
}
