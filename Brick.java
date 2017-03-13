import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * 
 * 
 * @author (Stars as2127,gs376,smk38) 
 * @version (8/3/17)
 */
public class Brick extends Actor
{
    private GreenfootImage bricks;
    private boolean changed = false; //This determines whether the brick has changed images or not
    int mapX;
    int mapY;
    /**
     * Defines the image for the brick
     */
    public Brick(int newMapX, int newMapY){
        mapX = newMapX;
        mapY = newMapY;
        bricks = new GreenfootImage("castlebrick.png");
    }
    public Brick(){
        bricks = new GreenfootImage("castlebrick.png");    
    }
    
    /**
     * If the world is world four or five, the image will be changed to the bricks image.
     */
    public void act() 
    {
      
    }    
}
