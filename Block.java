import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Block extends Actor
{
    private int powerUpChance; //determines the chance of getting each powerup
    private boolean bumped = false; //determines whether the block has been hit or not
    private GreenfootImage imageSpent;
    private int scrolled; //determines how far the main actor has moved
    GreenfootSound bump = new GreenfootSound("Bump.wav");
    int mapX;
    int mapY;
    /**
     * This will define the image for the block
     */
    public Block(int newMapX, int newMapY){
        mapX = newMapX;
        mapY = newMapY;
        imageSpent = new GreenfootImage("SpentBlock.png");
    }

    /**
     * This will run the checkHit method for the block
     */
    public void act() 
    {
        checkHit();
    }    

    /**
     * This will check if the block has been hit by mario
     * If the block was hit by mario or marioG, then the block will create ammo or a heart, based on a random number.
     * If the block was hit by marioS, then the block will create a mushroom or a heart, based on a random number.
     */
    public void checkHit()
    {
        if (getOneObjectAtOffset (0, getImage().getHeight()/2+1, Max.class)!= null && getImage() != imageSpent)
        {
            bumped = true;
        }
        if (getOneObjectAtOffset (0, getImage().getHeight()/2+1, MaxS.class)!= null && getImage() != imageSpent)
        {
            bumped = true;
        }
        if (bumped == true)
        {
            bump.play();
            SWorld sWorld = (SWorld)getWorld();
            scrolled = ((SWorld)getWorld()).getUnivX(getX());
            if(sWorld.maxBig == true)
            {
                powerUpChance = Greenfoot.getRandomNumber(10);
                if(getWorld() instanceof Level1 || getWorld() instanceof Level1){
                    if (powerUpChance <= 7)
                    {
                        ((SWorld)getWorld()).addObject(new Heart(),scrolled, getY() - 32, true);
                    }
                    if (powerUpChance > 7)
                    {
                        ((SWorld)getWorld()).addObject(new Heart(),scrolled, getY() - 32, true);
                    }
                }else{
                    if (powerUpChance > 7)
                    {
                        ((SWorld)getWorld()).addObject(new Heart(),scrolled, getY() - 32, true);
                    }
                }

                bumped = false;
                setImage(imageSpent);
            }
            if(sWorld.maxSmall == true)
            {
                powerUpChance = Greenfoot.getRandomNumber(10);
                if (powerUpChance <= 7)
                {
                    ((SWorld)getWorld()).addObject(new Mushroom(),scrolled, getY() - 32, true);
                }
                if (powerUpChance > 7)
                {
                    ((SWorld)getWorld()).addObject(new Heart(),scrolled, getY() - 32, true);
                }
                bumped = false;
                setImage(imageSpent);
            }
        }
    }
}
