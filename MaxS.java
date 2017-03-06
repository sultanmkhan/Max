import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.util.List;


public class MaxS extends AllMax
{
    private int invincibilityTime; //this is the number that the invincibilityDelayCount must pass before invincibility runs out
    private int invincibilityDelayCount; //this number ticks up to determine how long you are invincible
    private int imageTime; //this is the number that the imagedDelayCount must pass before the image switches
    private int imageDelayCount; //this number ticks up to time the image switch
    final int jSpeed = 23; //this number determines how high you jump
    private int ySpeed = 0; //this number determines how fast you are moving up and down
    private int xSpeed; //this int determines how fast mario is moving in the x direction
    private boolean onGround; //this boolean shows whether or not you are on the ground
    private boolean gotHit; //this boolean shows whether or not mario was hit
    private boolean mLeft; //this boolean shows whether or not mario is left
   
    public boolean MaxSetAsSmall = false; //this boolean shows whether or not sWorld has had its variables set for mario being small
    private boolean fell = false; //this boolean determines whether or not mario has fallen off of the world
    private int scrolled; //this int determines how far mario has moved in the x direction
    private int livesLeft; //this int stores how many lives the player has left
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
    
    
    public MaxS()
    {
        imageL = new GreenfootImage("marioSL.png");
        imageR = new GreenfootImage("marioSR.png");
        imageML = new GreenfootImage("marioSML.png");
        imageMR = new GreenfootImage("marioSMR.png");
        invincibilityTime = 50;
        invincibilityDelayCount = 0;
        imageTime = 8;
        imageDelayCount = 0;
        xSpeed = 0;
    }
    
     
    public void act() 
    {
        invincibilityDelayCount++;
        imageDelayCount++;
        moveVertically();
        moveHorizontally();
        animateMax();
        if (MaxSetAsSmall == false)
        {
            setMaxAsSmall();
        }
        removeMario();
    }    
    
   
    private void setMaxAsSmall()
    {
        MWorld sWorld = (MWorld)getWorld();
        sWorld.maxIsSmall();
        MaxSetAsSmall= true;
    }
    
    
    public void moveHorizontally()
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
        if (Greenfoot.isKeyDown("right"))
        {
            if (xSpeed < 0)
            {
                xSpeed = 0;
            }
            if (xSpeed < 5)
            {
                xSpeed = xSpeed +1;
            }
            xSpeed = 5;
            MWorld sWorld = (MWorld)getWorld();
            sWorld.maxL = false;
        }
        if (Greenfoot.isKeyDown("left"))
        {
            if (xSpeed > 0)
            {
                xSpeed = 0;
            }
            if (xSpeed > - 5)
            {
                xSpeed = xSpeed -1;
            }
            MWorld sWorld = (MWorld)getWorld();
            sWorld.maxL = true;
        }
        if (!Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right"))
        {
            xSpeed = 0;
        }
        if (Greenfoot.isKeyDown("up"))
        {
            jump();
        }
    }
    
   
    private void moveVertically()
    {
        ySpeed ++;
        setLocation(getX(), getY()+ySpeed/2);
        onGround = false;
        while (getOneObjectAtOffset (0, getImage().getHeight()/2, Ground.class)!= null)
        {
            setLocation(getX(), getY()-1);
            onGround = true;
            ySpeed = 0;
        }
        while (getOneObjectAtOffset (0, getImage().getHeight()/2, Brick.class)!= null)
        {
            setLocation(getX(), getY()-1);
            onGround = true;
            ySpeed = 0;
        }
        while (getOneObjectAtOffset (0, getImage().getHeight()/2, Block.class)!= null)
        {
            setLocation(getX(), getY()-1);
            onGround = true;
            ySpeed = 0;
        }
       
        while (getOneObjectAtOffset (0, getImage().getHeight()/2, Pipe.class)!= null)
        {
            if (Greenfoot.isKeyDown("down"))
            {
                down.play();
                
            }
            else
            {
                setLocation(getX(), getY()-1);
                onGround = true;
                ySpeed = 0;
            }
        }
        while (getOneObjectAtOffset (0, -getImage().getHeight()/2, Block.class)!= null)
        {
            setLocation(getX(), getY()+1);
            ySpeed = 0;
        }
        while (getOneObjectAtOffset (0, -getImage().getHeight()/2, Brick.class)!= null)
        {
            setLocation(getX(), getY()+1);
            ySpeed = 0;
        }
       
       
        if(getY() >= 395)
        {
            fell = true;
            MWorld sWorld = (MWorld)getWorld();
            sWorld.maxIsSmall();
            sWorld.decreaseLives();
        }
        Actor ghoomba = getOneObjectAtOffset (0, getImage().getHeight()/2, Ghoomba.class);
        if(ghoomba != null)
        {
            ySpeed = -20;
            squish.play();
            getWorld().removeObject(ghoomba);
        }
        
    }
    
    //After eating Mushroom MaxS would grow into Max
    
    public void grow()
    {
        grow.play();
        MWorld world = (MWorld)getWorld();    
        Actor futureMain = new Max();
        world.addObject(futureMain, getX(), getY(), false); 
        world.mainActor = futureMain;
        setLocation(getX(), getY());
        gotHit = false;
        invincibilityDelayCount = 0;
        world.removeObject(this);
    }
    
   
    private void animateMax()
    {
        MWorld sWorld = (MWorld)getWorld();
        mLeft = sWorld.maxL;
        if (mLeft == true && Greenfoot.isKeyDown("left"))
        {
            if (getImage() == imageL && imageDelayCount >= imageTime)
            {
                setImage(imageML);
                imageDelayCount = 0;
            }
            if (getImage() == imageML && imageDelayCount >= imageTime)
            {
                setImage(imageL);
                imageDelayCount = 0;
            }
            if (getImage() == imageR)
            {
                setImage(imageL);
                imageDelayCount = 0;
            }
            if (getImage() == imageMR)
            {
                setImage(imageL);
                imageDelayCount = 0;
            }
        }
        if (mLeft == true && Greenfoot.isKeyDown("left") == false)
        {
            setImage(imageL);
        }
        
        if (mLeft == false && Greenfoot.isKeyDown("right"))
        {
            if (getImage() == imageR && imageDelayCount >= imageTime)
            {
                setImage(imageMR);
                imageDelayCount = 0;
            }
            if (getImage() == imageMR && imageDelayCount >= imageTime)
            {
                setImage(imageR);
                imageDelayCount = 0;
            }
            if (getImage() == imageL)
            {
                setImage(imageR);
                imageDelayCount = 0;
            }
            if (getImage() == imageML)
            {
                setImage(imageR);
                imageDelayCount = 0;
            }
        }
        if (mLeft == false && Greenfoot.isKeyDown("right") == false)
        {
            setImage(imageR);
        }
    }
    
    
    public void removeMario()
    {
        if (getOneObjectAtOffset (-getImage().getWidth()/2, 0, Enemies.class)!= null && invincibilityDelayCount >= invincibilityTime)
        {
            MWorld sWorld = (MWorld)getWorld();
            sWorld.maxIsSmall();
            sWorld.decreaseLives();
            gotHit = true;
        }
        
        if (getOneObjectAtOffset (getImage().getWidth()/2, 0, Enemies.class)!= null && invincibilityDelayCount >= invincibilityTime)
        {
            MWorld sWorld = (MWorld)getWorld();
            sWorld.maxIsSmall();
            sWorld.decreaseLives();
            gotHit = true;
        }
        
        
        if (gotHit == true)
        {
            MWorld sWorld = (MWorld)getWorld();
            die.play();
            if (getWorld() instanceof Stage1)
            {
                Stage1 level1 = (Stage1)getWorld();
               // Stage1.rebuildWorld();
            }
           
        }
        if (fell == true)
        {
            MWorld sWorld = (MWorld)getWorld();
            die.play();
            if (getWorld() instanceof Stage1)
            {
                Stage1 level1 = (Stage1)getWorld();
                level1.rebuildWorld();
            }
            
        }
    }
    
    // Max would jump.
    
        private void jump()
    {
        if (onGround == true)
        {
            jump.play();
            ySpeed -= jSpeed;
        }
    }
    
    
    public int getXCoord()
    {
        return getX();
    }
}