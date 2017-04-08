import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * 
 * 
 * @author (Stars as2127,gs376,smk38) 
 * @version (8/3/17)
 */
public class SmallMax extends Actor
{
    private int imageTime; //this is the number that the imagedDelayCount must pass before the image switches
    private int imageCount; //this number ticks up to time the image switch
    final int speedJumb = 23; //this number determines how high you jump
    private int ySpeed = 0; //this number determines how fast you are moving up and down
    private int xSpeed; //this int determines how fast max is moving in the x direction
    private boolean onGround; //this boolean shows whether or not you are on the ground
    private boolean isHit; //this boolean shows whether or not max was hit
    private boolean isMoveLeft; //this boolean shows whether or not max is left
    public boolean maxSetAsSmall = false; //this boolean shows whether or not sWorld has had its variables set for max being small
    private boolean hasFallen = false; //this boolean determines whether or not max has fallen off of the world
    private GreenfootImage imageL;
    private GreenfootImage imageR;
    private GreenfootImage imageML;
    private GreenfootImage imageMR;
    GreenfootSound grow = new GreenfootSound("Grow.wav");
    GreenfootSound die = new GreenfootSound("Downed.wav");
    GreenfootSound jump = new GreenfootSound("Jump.wav");
    GreenfootSound squish = new GreenfootSound("Squish.wav");
    GreenfootSound bounce = new GreenfootSound("Shell.wav");
    GreenfootSound down = new GreenfootSound("Pipe.wav");
    private int pause = 25; //provide some protection after max got hit

    /**
     * This will define variables for maxS.
     */
    public SmallMax()
    {
        imageL = new GreenfootImage("marioSL.png");
        imageR = new GreenfootImage("marioSR.png");
        imageML = new GreenfootImage("marioSML.png");
        imageMR = new GreenfootImage("marioSMR.png");
        imageTime = 8;
        imageCount = 0;
        xSpeed = 0;
    }

    /**
     * Ticks up the DelayCounts and calls the methods for max
     */
    public void act() 
    { 
        imageCount++;
        moveY();
        moveX();
        if (maxSetAsSmall == false)
        {
            setmaxAsSmall();
        }
        if(pause > 0){pause--;}
        if(pause == 0){removeMax();}
    }    

    /**
     * Sets the MWorld booleans to the appropriate ones for max being small
     */
    private void setmaxAsSmall()
    {
        MWorld sWorld = (MWorld)getWorld();
        sWorld.maxIsSmall();
        maxSetAsSmall= true;
    }

    /**
     * This will move max based on whether or not there are objects around max and keys are pressed.
     * If the w key is pressed while an object is underneath max, he will jump. 
     * If the d key is pressed and there is no block to the right of max, he will move right.
     * If the a key is pressed and there is no block to the left of max, he will move left.
     * If the s key is pressed and there is a pipe below max, the world will change.
     */
    public void moveX()
    {
        setLocation(getX()+xSpeed, getY());
        while (getOneObjectAtOffset (getImage().getWidth()/2, 0, Block.class)!= null)
        {
            setLocation(getX()-1, getY());
            xSpeed = 0;
        }
        while (getOneObjectAtOffset (-getImage().getWidth()/2, 0, Block.class)!= null)
        {
            setLocation(getX()+1, getY());
            xSpeed = 0;
        }
        while (getOneObjectAtOffset (getImage().getWidth()/2, 0, Brick.class)!= null)
        {
            setLocation(getX()-1, getY());
            xSpeed = 0;
        }
        while (getOneObjectAtOffset (-getImage().getWidth()/2, 0, Brick.class)!= null)
        {
            setLocation(getX()+1, getY());
            xSpeed = 0;
        }
             
        
        while (getOneObjectAtOffset (getImage().getWidth()/2, 0, VerticalPlatform.class)!= null)
        {
            setLocation(getX()-1, getY());
            xSpeed = 0;
        }
        while (getOneObjectAtOffset (-getImage().getWidth()/2, 0, VerticalPlatform.class)!= null)
        {
            setLocation(getX()+1, getY());
            xSpeed = 0;
        }
        

        while (getOneObjectAtOffset (getImage().getWidth()/2, 0, Pipe.class)!= null)
        {
            setLocation(getX()-1, getY());
            xSpeed = 0;
        }
        while (getOneObjectAtOffset (-getImage().getWidth()/2, 0, Pipe.class)!= null)
        {
            setLocation(getX()+1, getY());
            xSpeed = 0;
        }
        MWorld sWorld = (MWorld)getWorld();
        if (Greenfoot.isKeyDown("right"))
        {
            if (xSpeed < 0)
            {
                xSpeed = 0;
            }
            if (xSpeed < 5)
            {
                xSpeed++;
            }
            xSpeed = 5;
            sWorld.maxL = false;
            if (getImage() == imageR && imageCount >= imageTime)
            {
                setImage(imageMR);
                imageCount = 0;
            }
            if (getImage() == imageMR && imageCount >= imageTime)
            {
                setImage(imageR);
                imageCount = 0;
            }
            if (getImage() == imageL)
            {
                setImage(imageR);
                imageCount = 0;
            }
            if (getImage() == imageML)
            {
                setImage(imageR);
                imageCount = 0;
            }
        }
        if (sWorld.maxL == false && Greenfoot.isKeyDown("right") == false)
        {
            setImage(imageR);
        }

        if (Greenfoot.isKeyDown("left"))
        {
            if (xSpeed > 0)
            {
                xSpeed = 0;
            }
            if (xSpeed > - 5)
            {
                xSpeed--;
            }
            sWorld.maxL = true;
            if (getImage() == imageL && imageCount >= imageTime)
            {
                setImage(imageML);
                imageCount = 0;
            }
            if (getImage() == imageML && imageCount >= imageTime)
            {
                setImage(imageL);
                imageCount = 0;
            }
            if (getImage() == imageR)
            {
                setImage(imageL);
                imageCount = 0;
            }
            if (getImage() == imageMR)
            {
                setImage(imageL);
                imageCount = 0;
            }
        }

        if (sWorld.maxL == true && Greenfoot.isKeyDown("left") == false)
        {
            setImage(imageL);
        }

        if (!Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right"))
        {
            xSpeed = 0;
        }
        if (Greenfoot.isKeyDown("up"))
        {
            if (onGround == true)
            {
                jump.play();
                ySpeed -= speedJumb;
            }
        }
    }

    /**
     * This method will move max vertically and then check if he should be removed because he is out of the world.
     * It will also check if max should kill an enemy which he is standing on.
     */
    private void moveY()
    {
        ySpeed ++;
        setLocation(getX(), getY()+ySpeed/2);
        onGround = false;
        while (getOneObjectAtOffset (0, getImage().getHeight()/2-1, Ground.class)!= null)
        {
            setLocation(getX(), getY()-1);
            onGround = true;
            ySpeed = 0;
        }
        while (getOneObjectAtOffset (0, getImage().getHeight()/2-1, Brick.class)!= null)
        {
            setLocation(getX(), getY()-1);
            onGround = true;
            ySpeed = 0;
        }
        while (getOneObjectAtOffset (0, getImage().getHeight()/2-1, Block.class)!= null)
        {
            setLocation(getX(), getY()-1);
            onGround = true;
            ySpeed = 0;
        }
              
        while (getOneObjectAtOffset (0, getImage().getHeight()/2-1, VerticalPlatform.class)!= null)
        {
            setLocation(getX(), getY()-1);
            onGround = true;
            ySpeed = 0;
        }
               
        while (getOneObjectAtOffset (0, getImage().getHeight()/2-1, Pipe.class)!= null)
        {
            if (Greenfoot.isKeyDown("down"))
            {
                down.play();
                if (getWorld() instanceof Stage1)
                {
                    Stage1 level1 = (Stage1)getWorld();
                    level1.stopMusic();   
                    Greenfoot.setWorld(new Stage2());
                }
               if (getWorld() instanceof Stage2)
                {
                    Stage2 worldTwo = (Stage2)getWorld();
                    worldTwo.stopMusic();
                   Greenfoot.setWorld(new Restart());
                }
              
            }
            else
            {
                setLocation(getX(), getY()-1);
                onGround = true;
                ySpeed = 0;
            }
        }
        while (getOneObjectAtOffset (0, -getImage().getHeight()/2+1, Block.class)!= null)
        {
            setLocation(getX(), getY()+1);
            ySpeed = 0;
        }
        while (getOneObjectAtOffset (0, -getImage().getHeight()/2+1, Brick.class)!= null)
        {
            setLocation(getX(), getY()+1);
            ySpeed = 0;
        }
     
        if(getY() >= 395)
        {
            hasFallen = true;
            MWorld sWorld = (MWorld)getWorld();
            sWorld.maxIsSmall();
            sWorld.decreaseLives();
            die.play();
        }
        if(getOneObjectAtOffset (0, getImage().getHeight()/2, Ghoomba.class) != null)
        {
            Actor ghoomba = getOneObjectAtOffset (0, getImage().getHeight()/2, Ghoomba.class);
            ySpeed = -20;
            squish.play();
            getWorld().removeObject(ghoomba);
        }
        
      
    }

    /**
     * This will remove max if he is hit by an enemy or an enemy projectile. It will also add a regular max in the same place as maxG was at.
     * If max is at the bottom of the world, it will call the rebuildWorld method in whatever world max is currently in.
     * This will also decrease the lives of the player
     */
    public void removeMax()
    {
        MWorld sWorld = (MWorld)getWorld();
        if (getOneObjectAtOffset (-getImage().getWidth()/2, 0, Ghoomba.class)!= null )
        {
            sWorld.maxIsSmall();
            sWorld.decreaseLives();
            Actor tmp = getOneObjectAtOffset (-getImage().getWidth()/2, 0, Ghoomba.class);
            tmp.move(10);
            isHit = true;
        }
        if (getOneObjectAtOffset (getImage().getWidth()/2, 0, Ghoomba.class)!= null )
        {
            sWorld.maxIsSmall();
            sWorld.decreaseLives();
            isHit = true;
        }

            
      
        if (isHit == true)
        {
            die.play();
            if (getWorld() instanceof Stage1)
            {
                Stage1 level1 = (Stage1)getWorld();
                level1.rebuildWorld();
            }
            if (getWorld() instanceof Stage2)
            {
                Stage2 worldTwo = (Stage2)getWorld();
                 worldTwo.rebuildWorld(); 
            }

           
        }
        if (hasFallen == true)
        {
            die.play();
            if (getWorld() instanceof Stage1)
            {
                Stage1 level1 = (Stage1)getWorld();
                level1.rebuildWorld();
            }
            if (getWorld() instanceof Stage2)
            {
                Stage2 worldTwo = (Stage2)getWorld();
                worldTwo.rebuildWorld(); 
            }
           
        }
    }

   
    /**
     * When called, this method will replace small max with big max
     */
    public void grow()
    {
        grow.play();
        MWorld world = (MWorld)getWorld();    
        Actor futureMain = new Max();
        world.addObject(futureMain, getX(), getY(), false); 
        world.mainActor = futureMain;
        setLocation(getX(), getY());
        isHit = false;
        world.removeObject(this);
    }

    /**
     * When called, the X coordinate of max will be sent
     */
    public int getXCoord()
    {
        return getX();
    }
}