import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Gives user choice to exit or restart the game
 * 
 * @author (Stars as2127,gs376,smk38) 
 * @version (8/3/17)
 */
public class Restart extends World
{
    static GreenfootSound themeMusic = new GreenfootSound("Menu theme.mp3");
    GreenfootSound enter1 = new GreenfootSound("Clicked.wav");
    /**
     * Constructor for objects of class Restart.
     * 
     */
    public Restart()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(700, 400, 1); 
        if(themeMusic.isPlaying()==false)
        {
            themeMusic.playLoop();
        }
    }

    public void act()
    {
        if(Greenfoot.isKeyDown("enter"))
        {
            Greenfoot.delay(10);
            enter1.play(); 
            themeMusic.stop();
            Greenfoot.setWorld(new StartMenu1());
        }

        if(Greenfoot.isKeyDown("down"))
        {
            enter1.play();
            Greenfoot.setWorld(new Exit());
        }
    }

}
