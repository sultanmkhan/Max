import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartMenu3 here.
 * 
 * @author (Dylan Powell and Sean Eastley) 
 * @version (January 21, 2015)
 */
public class StartMenu1 extends World
{
    private int enterDelayCount;
    static GreenfootSound theme = new GreenfootSound("Menu theme.mp3");
    GreenfootSound clicked = new GreenfootSound("Clicked.wav");
    /**
     * Constructor for objects of class StartMenu1.
     * 
     */
    public StartMenu1()
    {    
        super(700, 400, 1);
        if(theme.isPlaying() == false)
        {
            theme.playLoop();
        }
    }
    
    public void act()
    {
        enterDelayCount ++;
        if (Greenfoot.isKeyDown("enter") && enterDelayCount > 15)
        {
            theme.stop();
            clicked.play();
            Greenfoot.setWorld(new Stage1());
        }
        if (Greenfoot.isKeyDown("down"))
        {
            clicked.play();
            Greenfoot.setWorld(new StartMenu2());
        }
    }
}
