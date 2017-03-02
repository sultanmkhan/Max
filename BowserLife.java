import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;


public class BowserLife extends Actor
{
    private static final String healthLabel = "Health: "; //This will be displayed on the scoreboard before the number of hits left
    private static final String nameLabel = "BOWSER"; //This will be displayed on the scoreboard to show that the lives belong to bowser
    private GreenfootImage box; //This is the background for the scoreboard
    
    /**
     * This will create the scoreboard background box
     */
    public BowserLife ()
    {
        box = new GreenfootImage (60, 30);
        
        box.setColor(new Color (0f,0f,0f,0.5f));
        box.fillRect (0,0, 60, 30);
        updateScore (20);
    }
    
    /**
     * This will update the score on the scoreboard.
     * This makes the scoreboard display yellow text with the lives label and the lives.
     */
    public void updateScore (int hits)
    {
        GreenfootImage img = new GreenfootImage (box);
        
        img.setColor (Color.YELLOW);
        img.drawString(nameLabel, 5, 12);
        img.drawString(healthLabel + hits, 5, 25);
        
        setImage (img);
    }
}