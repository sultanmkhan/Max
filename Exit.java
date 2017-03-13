import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * 
 * @author (Stars as2127,gs376,smk38) 
 * @version (8/3/17)
 */
public class Exit extends World
{
    GreenfootSound enter1 = new GreenfootSound("Clicked.wav");
    /**
     * Constructor for objects of class Exit.
     * 
     */
    public Exit()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(700, 400, 1); 
    }

    public void act()
    {
        if(Greenfoot.isKeyDown("enter"))
        {
            enter1.play();  
            System.exit(1);
        }

        if(Greenfoot.isKeyDown("up"))
        {
            enter1.play();
            Greenfoot.setWorld(new Restart());
        }
    }

}
