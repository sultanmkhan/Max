import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Shows Game over screen and returns to Start Menu 1
 * 
 * @author (Stars as2127,gs376,smk38) 
 * @version (8/3/17)
 */
public class GameOver extends World
{
    GreenfootSound lost = new GreenfootSound("Lost.wav");
    GreenfootSound clicked = new GreenfootSound("Clicked.wav");
    /**
     * Constructor for objects of class GameOver.
     * 
     */
    public GameOver()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        if(lost.isPlaying()==false)
        {
            lost.playLoop();
        }
    }

    public void act()
    {
        if(Greenfoot.isKeyDown("enter"))
        {
            Greenfoot.delay(10);
            clicked.play(); 
            lost.stop();
            Greenfoot.setWorld(new StartMenu1());
        }
    }

}
