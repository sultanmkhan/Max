import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * 
 * 
 * @author (Stars as2127,gs376,smk38) 
 * @version (8/3/17)
 */
public class Stage2 extends MWorld
{
    static GreenfootSound music = new GreenfootSound("World2 theme.mp3");
    static GreenfootSound start = new GreenfootSound("Stage.wav");
    private ScoreBoard scoreBoard;
    /**
     * Constructor for objects of class Stage2.
     * 
     */
    public Stage2()
    {    
        super(595, 400, 1,0);
        GreenfootImage bg = new GreenfootImage("clouds.png");
        setScrollingBackground(bg);
        maxSmall = true;
        if (!music.isPlaying())
        {
            music.playLoop();
        }
        buildWorld();
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

    private void buildWorld()
    {   
        addScoreboard();
        area1();
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

    /**
     * Stops the music when called
     */
    public void stopMusic()
    {
        music.stop();
    }

    private void area1()
    {
        if (maxSmall == true)
        {
            addMaxToWorld(new SmallMax(), 50, 352, 250, 300);
        }

        addObject(new Ground(),0,390);
        addObject(new Ground(),16,390);
        addObject(new Ground(),32,390);
        addObject(new Ground(),48,390);

        addObject(new Ground(),545,390);
        addObject(new Ground(),551,390);
        addObject(new Ground(),567,390);
        addObject(new Ground(),583,390);
        addObject(new Ground(),599,390);

        addObject(new VerticalPlatform(),138,352);
        addObject(new VerticalPlatform(),300,277);
        addObject(new VerticalPlatform(),461,222);

        addObject(new Pipe(),564,343);
        
    }

}
