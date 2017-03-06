import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class AllMax extends Actor
{
     private int xSpeed;
     final int jSpeed = 23; 
     private int ySpeed = 0;
     private boolean onGround;
     private boolean fell = false;
      private boolean mLeft;
        private int imageDelayCount;
         private int imageTime;
     private GreenfootImage imageL;
    private GreenfootImage imageR;
    private GreenfootImage imageML;
    private GreenfootImage imageMR;
      GreenfootSound jump = new GreenfootSound("Jump.wav");
      GreenfootSound squish = new GreenfootSound("Squish.wav");
       GreenfootSound die = new GreenfootSound("Downed.wav");
        GreenfootSound down = new GreenfootSound("Pipe.wav");
    public void act() 
    {
        // Add your action code here.
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
                if (getWorld() instanceof Stage1)
                {
                    Stage1 myWorld = (Stage1)getWorld();
                   // myWorld.stopMusic();/////need
                    Greenfoot.setWorld(new Stage1());
                }
                
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
    
    
     private void jump()
    {
        if (onGround == true)
        {
            jump.play();
            ySpeed -= jSpeed;
        }
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

    



}



