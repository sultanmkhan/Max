import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * 
 * 
 * @author (Stars as2127,gs376,smk38) 
 * @version (8/3/17)
 */
public class Heart extends Powerups
{
    private int ySpeed; //This will determine how fast the heart is falling
    private boolean hitEdge = false; //This will determine whether the heart should move right or left
    private boolean terrainLeft; //This will determine whether the platform below the heart is moving right or left
    GreenfootSound oneup = new GreenfootSound("Heart.wav");
    
    /**
     * This will call the methods for moving the heart and checking if it was hit.
     */
    public void act() 
    {
        moveHeart();
        checkHit();
    }  
    
    /**
     * This method will check if the heart is colliding with mario.
     * If it is colliding with mario, the heart will be removed, the sound effect will be played and the lives counter will be updated.
     * The scoreboard will also be updated based on which world the heart is in.
     */
    private void checkHit()
    {
        Max m = (Max) getOneIntersectingObject(Max.class);
        SmallMax s = (SmallMax) getOneIntersectingObject(SmallMax.class);
        
        if(m != null)
        {
            oneup.play();
            MWorld sWorld = (MWorld)getWorld();
            sWorld.increaseLives();
            if (getWorld() instanceof Stage1)
            {
                Stage1 level1 = (Stage1)sWorld;
                level1.updateScoreboard();
            }
           
            
            
            getWorld().removeObject(this);
        }
        if(s != null)
        {
            oneup.play();
            MWorld sWorld = (MWorld)getWorld();
            sWorld.increaseLives();
            if (getWorld() instanceof Stage1)
            {
                Stage1 level1 = (Stage1)sWorld;
                level1.updateScoreboard();
            }
            
            getWorld().removeObject(this);
        }
    }
    
    /**
     * This method will move the heart.
     * If there is a block to the right of the heart, it will move left until it hits another object.
     * If there is a block to the left of the heart, it will move left until it hits another object.
     * If there is no object below the heart, it will fall until there is an object underneath the heart.
     */
    public void moveHeart()
    {
        ySpeed ++;
        setLocation(getX(), getY()+ySpeed/2);
        while (getOneObjectAtOffset (0, getImage().getHeight()/2, Ground.class)!= null)
        {
            setLocation(getX(), getY()-1);
            ySpeed = 0;
        }
        while (getOneObjectAtOffset (0, getImage().getHeight()/2, Brick.class)!= null)
        {
            setLocation(getX(), getY()-1);
            ySpeed = 0;
        }
        while (getOneObjectAtOffset (0, getImage().getHeight()/2, Block.class)!= null)
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
