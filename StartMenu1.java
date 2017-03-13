import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Welcome Screen For the Game
 * 
 * @author (Stars as2127,gs376,smk38) 
 * @version (8/3/17)
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

        prepare();
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

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
    }
}
