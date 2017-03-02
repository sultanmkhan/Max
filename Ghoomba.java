import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Ghoomba extends MovingEnemies
{
    private int stability; //stores how much health the ghoomba has
    private GreenfootImage imageL;
    private GreenfootImage imageR;
    private int imageChangeTime; //defines how long to wait before the image changes
    private int imageChangeDelayCount; //ticks up to change the image
    private boolean imminentDeath; //determines if the ghoomba is about to die
    private boolean hitEdge = false; //initialize value for hitEdge. Determines whether the ghoomba has hit the edge
    int mapX;
    int mapY;
    /**
     * Initializes values and images for the ghoomba
     */
    public Ghoomba(int newMapX, int newMapY)
    {
        mapX = newMapX;
        mapY = newMapY;
        imageL = new GreenfootImage("GhoombaL.png");
        imageR = new GreenfootImage("GhoombaR.png");
        setImage(imageL);
        stability = 1;
        imageChangeTime = 5;
        imageChangeDelayCount = 0;
    }

    /**
     * Calls the necessary methods to move the ghoomba, animate it and decide whether or not it has been hit
     */
    public void act() 
    {
        moveGhoomba();
        switchImage();
    }  

    /**
     * Will move the ghoomba and turn it around if it hits a brick
     */
    public void moveGhoomba()
    {
        if (getOneObjectAtOffset (getImage().getWidth()/2+1, 0, Brick.class)!= null)
        {
            move(-2);
            hitEdge = true;
        }
        if (getOneObjectAtOffset (-getImage().getWidth()/2-1, 0, Brick.class)!= null)
        {
            move(2);
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

    /**
     * When this method is called, the stability of the ghoomba will be reduced by the damage that the bullet imflicts.
     * If the stability of the ghoomba is less than or equal to zero, the ghoomba will be removed.
     */
    public void hit(int damage) 
    {
        stability = stability - damage;
        if(stability <= 0) 
        {
            getWorld().removeObject(this);
        }
    }

    /**
     * This will animate the ghoomba based on which image it has and whether or not the delay count has reached the change time.
     * If the delay count has not reached the change time, the delay count will tick up.
     */
    public void switchImage()
    {
        if(imageChangeDelayCount >= imageChangeTime)
        {
            if(getImage() == imageL)
            {
                setImage(imageR);
                imageChangeDelayCount = 0;
            }
            else
            {
                setImage(imageL);
                imageChangeDelayCount = 0;
            }
        }
        else
        {
            imageChangeDelayCount++;
        }
    }
}
