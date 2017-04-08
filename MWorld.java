import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Main Functionality of Marios Game World
 * 
 * @author (Stars as2127,gs376,smk38) 
 * @version (8/3/17)
 */
public class MWorld extends World
{
    private int scrollingWidth, scrollingHeight; //defines where the main actor can move in the universal coordinates
    private int actorMinX, actorMaxX, actorMinY, actorMaxY; //defines where the main actor can move in the world coordinates
    private int scrolledX, scrolledY; //how much the world has scrolled
    private int scrollType; //determines direction of scrolling
    Actor mainActor = null; //The actor the screen centers on
    private List<Actor>genActors=new ArrayList(); //lists all the actors
    private GreenfootImage background = null;
    public static int lives = 3; //defines how many lives the player has
    private ScoreBoard scoreBoard;
    public boolean maxL = false; //determines whether max is moving left or right

    static boolean maxBig = false; //determines what kind of max is in the world
    static boolean maxSmall = true; //determines what kind of max is in the world

    /**
     * Defines the variables needed for creating the scrolling world
     */
    public MWorld(int wide, int high, int cellSize, int scrollWide, int scrollHigh)
    {
        super(cellSize==1?wide:(wide/2)*2+1, cellSize==1?high:(high/2)*2+1, cellSize, false);
        scrollType=(scrollWide>wide?1:0)+(scrollHigh>high?2:0);
        scrollingWidth=scrollType%2==1?scrollWide:wide;
        scrollingHeight=scrollType/2==1?scrollHigh:high;
    }

    /**
     * Makes the world a horizontal scroller
     */
    public MWorld(int wide, int high, int cellSize, int scrollWide)
    {
        this(wide, high, cellSize, scrollWide, high);
    }

    /**
     * This will set the appropriate variables for max existing
     */
    public void maxIsBig()
    {
        maxBig = true;
        maxSmall = false;

    }

    /**
     * This will set the appropriate variables for maxS existing
     */
    public void maxIsSmall()
    {
        maxBig = false;
        maxSmall = true;

    }

    /**
     * When called, sets maxL to false
     */
    public void maxRight()
    {
        maxL = false;
    }

    /**
     * When called, sets maxL to true
     */
    public void maxLeft()
    {
        maxL = true;
    }

    /**
     * When called, decreases the player's lives
     */
    public void decreaseLives()
    {
        lives = lives - 1;
    }

    /**
     * When called, increases the player's lives
     */
    public void increaseLives()
    {
        lives = lives + 1;
    }

    /**
     * When called, returns the player's lives
     */
    public int getLives()
    {
        return lives;
    }

    public void updateScoreboard()
    {
        scoreBoard.updateScore(lives);
    }

    /**
     * Adds the main actor into the world at the center of the screen
     * Sets the coordinate for the screen to scroll within the world, and determines how far the actor can move
     */
    public void addMaxToWorld(Actor main, int xLoc, int yLoc, int xRange, int yRange){
        super.addObject(main, xLoc-scrolledX, yLoc-scrolledY);
        mainActor = main;
        xRange= 0;
        yRange=(int)Math.min(yRange, getHeight());
        actorMinX=getWidth()/2-xRange/2;
        actorMaxX=getWidth()/2+xRange/2;
        actorMinY=getHeight()/2-yRange/2;
        actorMaxY=getHeight()/2+yRange/2;
        act();
    }

    /**
     * Adds a scrolling background to the world based off a picture
     */
    public void setScrollingBackground(GreenfootImage scrollingBackground)    
    {
        background = new GreenfootImage(scrollingBackground);
        background.scale(scrollingWidth*getCellSize(), scrollingHeight*getCellSize());
        scrollBackground();
    }

    /**
     * Makes the background just the picture by stretching it where appropriate
     */
    public void fillScrollingBackground(GreenfootImage fillImage)
    {
        if (fillImage.getWidth()<getWidth() && fillImage.getHeight()<getHeight())
        {
            setBackground(new GreenfootImage(fillImage));
            fillImage = getBackground();
        }
        World world = new World(scrollingWidth*getCellSize(), scrollingHeight*getCellSize(), 1){};
        world.setBackground(fillImage);
        background = new GreenfootImage(world.getBackground());
        scrollBackground();
    }

    /**
     * Adds an object into the world and determines whether or not the object will scroll or not
     */
    public void addObject(Actor obj, int xLoc, int yLoc, boolean scroller)
    {
        if (!scroller)
        {
            super.addObject(obj, xLoc, yLoc);
            if (obj == mainActor)
            {
                act();
            }
            return;
        }
        super.addObject(obj, xLoc-scrolledX, yLoc-scrolledY);
        genActors.add(obj);
    }

    /**
     * Removes objects from the world
     */
    public void removeObject(Actor obj)
    {
        if(obj==null)
        {
            return;
        }
        if(obj.equals(mainActor))
        {
            mainActor=null;
        }
        else 
        {
            genActors.remove(obj);
        }
        super.removeObject(obj);
    }

    /**
     * Adds a scrollable actor to the world based on an x and y location
     */
    public void addObject(Actor obj, int xLoc, int yLoc)
    {
        addObject(obj, xLoc, yLoc, true);
    }

    /**
     * Runs the scrolling.
     */
    public void act()
    {
        scrollObjects();
        scrollBackground();
        if (lives < 0)
        {
            Greenfoot.setWorld(new GameOver());
        }
    }

    /**
     * Scrolls the background image.
     */
    private void scrollBackground()
    {
        if (background==null) 
        {
            return;
        }
        int c = getCellSize();
        getBackground().drawImage(background, -scrolledX*c, -scrolledY*c);
    }

    /**
     * Scrolls all objects which hae been defined as scrollable when they were added in based on where the main actor is in the window
     */
    private void scrollObjects()
    {
        if (mainActor==null) 
        {
            return;
        }
        int dx=0, dy=0;
        if(mainActor.getX()<actorMinX) 
        {
            dx=actorMinX-mainActor.getX();
        }
        if(mainActor.getX()>actorMaxX) 
        {
            dx=actorMaxX-mainActor.getX();
        }
        if(mainActor.getY()<actorMinY) 
        {
            dy=actorMinY-mainActor.getY();
        }
        if(mainActor.getY()>actorMaxY) 
        {
            dy=actorMaxY-mainActor.getY();
        }
        if(dx==0 && dy==0) 
        {
            return;
        }
        int dxSum = dx, dySum = dy;
        scrolledX-=dx; scrolledY-=dy;
        mainActor.setLocation(mainActor.getX()+dx, mainActor.getY()+dy);
        dx=0; dy=0;
        if(scrolledX > scrollingWidth-getWidth())
        {
            dx=scrolledX-(scrollingWidth-getWidth());
        }
        if(scrolledX < 0) 
        {
            dx=scrolledX;
        }
        if(scrolledY > scrollingHeight-getHeight()) 
        {
            dy=scrolledY-(scrollingHeight-getHeight());
        }
        if(scrolledY < 0) 
        {
            dy=scrolledY;
        }
        dxSum+=dx; dySum+=dy;
        scrolledX-=dx; scrolledY-=dy;
        mainActor.setLocation(mainActor.getX()+dx, mainActor.getY()+dy);
        for(Object obj : genActors)
        {
            Actor actor=(Actor)obj;
            actor.setLocation(actor.getX()+dxSum, actor.getY()+dySum);
        }
        dx=0; dy=0;
        if(mainActor.getX() < 0) 
        {
            dx=0-mainActor.getX();
        }
        if(mainActor.getX() > getWidth()-1) 
        {
            dx=(getWidth()-1)-mainActor.getX();
        }
        if(mainActor.getY() < 0) 
        {
            dy=0-mainActor.getY();
        }
        if(mainActor.getY() > getHeight()-1) 
        {
            dy=(getHeight()-1)-mainActor.getY();
        }
        if(dx==0 && dy==0) 
        {
            return;
        }
        mainActor.setLocation(mainActor.getX()+dx, mainActor.getY()+dy);
    }

    /**
     * We had to edit this code to make our objects spawn in properly. Determines how far the main actor has moved overall
     */
    public int getUnivX(int worldX){
        return worldX+scrolledX;
    }

    /**
     * When called, returns the scrolling area width
     */
    public int getScrollingWidth(){
        return scrollingWidth;
    }
}