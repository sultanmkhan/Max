import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
//Author Stars

/**
 * 
 * 
 * @author (Stars as2127,gs376,smk38) 
 * @version (8/3/17)
 */
public class Stage1 extends MWorld
{
    private ScoreBoard scoreBoard;
    static GreenfootSound theme = new GreenfootSound("World1 theme.mp3");
    static GreenfootSound start = new GreenfootSound("Stage.wav");

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Stage1()
    {    
        super(700, 400, 1, 3800);
        GreenfootImage bg = new GreenfootImage("mariobackground.png");
        setScrollingBackground(bg);
        lives = 3;
        maxSmall = true;
        if (!theme.isPlaying())
        {
            theme.playLoop();
        }
        rebuildWorld();
        if(!start.isPlaying())
        {
            start.play();
        }
    }

    /**
     * When called, removes all objects in the world and rebuilds it.
     * If lives are less than 0, the world is set to the game over world.
     */
    public void rebuildWorld()
    {
        if (lives < 0)
        {
            stopMusic();
            Greenfoot.setWorld(new GameOver());
        }
        List objects = getObjects(null);
        removeObjects(objects);
        buildWorld();
    }

    /**
     * Stops the music when called
     */
    public void stopMusic()
    {
        theme.stop();
    }

    /**
     * Updates the scoreboard in the world
     */
    public void updateScoreboard()
    {
        scoreBoard.updateScore(lives);
    }

    /**
     * Calls all the methods to add the objects to the world
     */
    private void buildWorld()
    {
        addScoreboard();
        area1();
        area2();
        area3();
        area4();
    }

    /**
     * Adds the scoreboard to the world
     */
    private void addScoreboard()
    {
        scoreBoard = new ScoreBoard();
        addObject(scoreBoard, 30, 15, false);
        scoreBoard.updateScore(lives);
    }

    private void area1()
    {
        for(int i=16;i<595;i+=17)
        {
            addObject(new Ground(),i, 390);
        }

        addObject(new Ghoomba(), 182, 358);
        addObject(new Ghoomba(), 332, 358);

        addObject(new Brick(), 150, 290);
        addObject(new Block(), 182, 290);
        addObject(new Brick(), 214, 290);

        addObject(new Brick(), 300, 290);
        addObject(new Block(), 332, 290);
        addObject(new Brick(), 364, 290);

        addObject(new Brick(), 497, 358);
        addObject(new Brick(), 529, 358);
        addObject(new Brick(), 561, 358);
        addObject(new Brick(), 593, 358);
        addObject(new Brick(), 497, 326);
        addObject(new Brick(), 529, 326);
        addObject(new Brick(), 561, 326);
        addObject(new Brick(), 593, 326);
        addObject(new Brick(), 529, 294);
        addObject(new Brick(), 561, 294);
        addObject(new Brick(), 593, 294);
        addObject(new Brick(), 561, 262);
        addObject(new Brick(), 593, 262);
        addObject(new Brick(), 593, 230);

        if (maxSmall == true)
        {
            addMaxToWorld(new SmallMax(), 50, 352, 250, 300);
        }

        
    }
    private void area2()
    {
        for(int i=890;i<3800;i+=17)
        {
            addObject(new Ground(),i, 390);
        } 

        addObject(new Brick(), 950, 358);
        addObject(new Brick(), 950, 326);
        addObject(new Brick(), 950, 294);

        addObject(new Ghoomba(), 1050, 358);
        addObject(new Ghoomba(), 1200, 358);

        addObject(new Brick(), 1050, 170);
        addObject(new Block(), 1125, 294);
        addObject(new Brick(), 1200, 170);

        addObject(new Brick(), 1300, 358);
        addObject(new Brick(), 1300, 326);
        addObject(new Brick(), 1300, 294);
    }

    private void area3()
    {
        addObject(new Brick(), 1500, 294);

        addObject(new Brick(), 1615, 358);
        addObject(new Block(), 1868, 290);
        addObject(new Brick(), 1900, 290);
        addObject(new Block(), 1932, 290);

        addObject(new Ghoomba(), 1800, 358);

        addObject(new Brick(), 2185, 358);
    }

    private void area4()
    {

        addObject(new Brick(), 2347, 358);

        addObject(new Brick(), 2550, 290);
        addObject(new Brick(), 2582, 290);
        addObject(new Brick(), 2614, 290);
        addObject(new Brick(), 2646, 290);
        addObject(new Brick(), 2678, 290);
        addObject(new Brick(), 2710, 290);
        addObject(new Brick(), 2742, 290);
        addObject(new Brick(), 2742, 258);
        addObject(new Brick(), 2550, 258);
        addObject(new Brick(), 2550, 226);
        addObject(new Brick(), 2550, 194);
        addObject(new Brick(), 2550, 162);
        addObject(new Brick(), 2550, 130);
        addObject(new Brick(), 2550, 98);
        addObject(new Brick(), 2550, 66);
        addObject(new Brick(), 2582, 66);
        addObject(new Brick(), 2614, 66);
        addObject(new Brick(), 2646, 66);
        addObject(new Brick(), 2678, 66);
        addObject(new Brick(), 2710, 66);
        addObject(new Brick(), 2742, 66);
        addObject(new Brick(), 2742, 258);
        addObject(new Block(), 2630, 162);
        addObject(new Block(), 2662, 162);
        addObject(new Ghoomba(), 2582, 258);
        addObject(new Ghoomba(), 2710, 258);

        addObject(new Brick(), 2840, 290);
        addObject(new Brick(), 2872, 290);

        addObject(new Brick(), 3000, 358);
        addObject(new Brick(), 3000, 326);
        addObject(new Brick(), 3000, 294);

        addObject(new Brick(), 3485, 358);
        addObject(new Brick(), 3485, 326);
        addObject(new Brick(), 3485, 294);
        addObject(new Brick(), 3453, 358);
        addObject(new Brick(), 3453, 326);
        addObject(new Brick(), 3421, 358);

        addObject(new Pipe(), 3600, 375);
    }

   
}
