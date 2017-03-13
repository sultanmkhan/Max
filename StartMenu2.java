import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class shows help menu 
 * 
 * @author (Stars as2127,gs376,smk38) 
 * @version (8/3/17)
 */
public class StartMenu2 extends World
{
    private int enterDelayCount; //Ticks up to determine when the player can press enter - to keep them from accidentally flipping through two worlds
    GreenfootSound clicked = new GreenfootSound("Clicked.wav");
    /**
     * Constructor for objects of class StartMenu2.
     * 
     */
    public StartMenu2()
    {    
        super(700, 400, 1); 
    }
    
    /**
     * Will set the world to the appropriate one based on the user's input and the delayCount reaching 15
     */
    public void act()
    {
        enterDelayCount ++;
        if (Greenfoot.isKeyDown("enter") && enterDelayCount > 15)
        {
            clicked.play();
            Greenfoot.setWorld(new Controls());
        }
        if (Greenfoot.isKeyDown("up"))
        {
            clicked.play();
            Greenfoot.setWorld(new StartMenu1());
        }
    }
}
