import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * 
 * @author (Stars as2127,gs376,smk38) 
 * @version (8/3/17)
 */
public class Mushroom extends Powerups
{
    private int ySpeed; //determines how fast the Mushroom is falling
    private boolean terrainLeft = false; //determines if the terrain below mario is moving left or right
    private boolean hitEdge = false; //determines if the Mushroom should be moving left or right
    
    /**
     * Calls the methods to move the Mushroom and see if it is colliding with mario.
     */
    public void act() 
    {
        moveMushroom();
        checkHit();
    }   
    
    /**
     * Do something if the Mushroom Collides with mario, dependent on which type of mario is in the world.
     * If the Mushroom collides with marioG, remove the mushroom
     * If the Mushroom collides with mario, remove the mushroom
     * If the Mushroom collides with marioS, call a method that will turn marioS into mario
     */
    private void checkHit()
    {
       Max m = (Max) getOneIntersectingObject(Max.class);
        SmallMax s = (SmallMax) getOneIntersectingObject(SmallMax.class);
       
        if(m != null)
        {
            getWorld().removeObject(this);
        }
        if(s != null)
        {
            s.grow();
            getWorld().removeObject(this);
        }
    }
    
    /**
     * This method will move the Mushroom.
     * If there is a block to the right of the Mushroom, it will move left until it hits another object.
     * If there is a block to the left of the Mushroom, it will move left until it hits another object.
     * If there is no object below the Mushroom, it will fall until there is an object underneath the Mushroom.
     */
    public void moveMushroom()
    {
        ySpeed ++;
        setLocation(getX(), getY()+ySpeed/2);
        while (getOneObjectAtOffset (0, getImage().getHeight()/2-2, Ground.class)!= null)
        {
            setLocation(getX(), getY()-1);
            ySpeed = 0;
        }
        while (getOneObjectAtOffset (0, getImage().getHeight()/2-2, Brick.class)!= null)
        {
            setLocation(getX(), getY()-1);
            ySpeed = 0;
        }
        
        while (getOneObjectAtOffset (0, getImage().getHeight()/2-2, Block.class)!= null)
        {
            setLocation(getX(), getY()-1);
            ySpeed = 0;
        }
       
        
        while (getOneObjectAtOffset (getImage().getWidth()/2+1, 0, Block.class)!= null)
        {
            move(-1);
            hitEdge = true;
        }
        while (getOneObjectAtOffset (-getImage().getWidth()/2-1, 0, Block.class)!= null)
        {
            move(1);
            hitEdge = false;
        }
        while (getOneObjectAtOffset (getImage().getWidth()/2+1, 0, Brick.class)!= null)
        {
            move(-1);
            hitEdge = true;
        }
        while (getOneObjectAtOffset (-getImage().getWidth()/2-1, 0, Brick.class)!= null)
        {
            move(1);
            hitEdge = false;
        }
       	
        if (hitEdge == false)
        {
            move(1);
        }
        else if (hitEdge == true)
        {
            move(-1);
        }
    }
}
