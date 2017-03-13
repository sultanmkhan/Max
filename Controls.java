import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *Assigns keyboard keys to user
 * 
 * @author (Stars as2127,gs376,smk38) 
 * @version (8/3/17)
 */
public class Controls extends World
{
    private int enterDelayCount; //Ticks up to determine when the player can press enter - to keep them from accidentally flipping through two worlds
    GreenfootSound clicked = new GreenfootSound("Clicked.wav");
    /**
     * Constructs the controls world
     */
    public Controls()
    {    
        super(700, 400, 1);
    }
    
    /**
     * Ticks up the enterDelayCount and switches the world when the enter key is pressed
     */
    public void act()
    {
        enterDelayCount ++;
        if (Greenfoot.isKeyDown("space") && enterDelayCount > 15)
        {
            clicked.play();
            Greenfoot.setWorld(new StartMenu2());
        }
    }
}
