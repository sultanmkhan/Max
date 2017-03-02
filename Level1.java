import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.awt.Color;
import java.util.ArrayList;

public class Level1 extends SWorld
{
    private ScoreBoard scoreBoard;
    static GreenfootSound theme = new GreenfootSound("World1 theme.mp3");
    static GreenfootSound start = new GreenfootSound("Stage.wav");

    Stage1Map map = new Stage1Map();
    GreenfootImage mapImage = map.getImage();
    final int MAPIMGWIDTH = mapImage.getWidth();
    final int MAPIMGHEIGHT = mapImage.getHeight();

    Ground ground = new Ground(0,0);
    GreenfootImage groundImg = ground.getImage();
    final int GROUNDWIDTH = groundImg.getWidth();
    final int GROUNDHEIGHT = groundImg.getHeight();

    final int MAPWIDTH = MAPIMGWIDTH * GROUNDWIDTH;
    final int MAPHEIGHT = MAPIMGHEIGHT * GROUNDHEIGHT;

    private List<Ground> grounds = new ArrayList<Ground>();
    private List<Brick> bricks = new ArrayList<Brick>();
    private List<Pipe> pipes = new ArrayList<Pipe>();
    private List<Block> blocks = new ArrayList<Block>();
    private List<Ghoomba> ghoombas = new ArrayList<Ghoomba>();
   
    
    public Level1()
    {    
        super(800, 400, 1, 6350);
        makeMap();
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

        }
        List objects = getObjects(null);
        removeObjects(objects);
        grounds.clear();
        bricks.clear();
        pipes.clear();
        blocks.clear();
        ghoombas.clear();

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
        makeMap();
        startingArea();
        addScoreboard();
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

    public void makeMap()
    {
        
        
        float hsb[] = new float[3];
        for(int y = 0; y < MAPIMGHEIGHT; y++){
            for(int x = 0; x < MAPIMGWIDTH; x++){
                int colorRGB = mapImage.getColorAt(x,y).getRGB();
                int r = (colorRGB >> 16) & 0xFF;
                int g = (colorRGB >>  8) & 0xFF;
                int b = (colorRGB  ) & 0xFF;
                Color.RGBtoHSB(r, g, b, hsb);
                float deg = hsb[0] * 360;
                if(colorRGB == Color.BLACK.getRGB()){
                    int mapX = x * MAPWIDTH /MAPIMGWIDTH ;
                    int mapY = (MAPIMGHEIGHT-y) * MAPHEIGHT / MAPIMGHEIGHT;
                    grounds.add(new Ground(mapX, mapY));
                }else if(deg >=  90 && deg < 150){ //find green pixels
                    int mapBrickX = x * MAPWIDTH /MAPIMGWIDTH;
                    int mapBrickY = (MAPIMGHEIGHT-y) * MAPHEIGHT / MAPIMGHEIGHT;
                    bricks.add(new Brick(mapBrickX, mapBrickY));
                }else if(deg >= 210 && deg < 270){ //find blue pixels
                    int mapPipeX = x * MAPWIDTH /MAPIMGWIDTH;
                    int mapPipeY = (MAPIMGHEIGHT-y) * MAPHEIGHT / MAPIMGHEIGHT;
                    pipes.add(new Pipe(mapPipeX, mapPipeY));
                }else if (deg >=  30 && deg <  90){
                    int mapBlockX = x * MAPWIDTH /MAPIMGWIDTH;
                    int mapBlockY = (MAPIMGHEIGHT-y) * MAPHEIGHT / MAPIMGHEIGHT;
                    blocks.add(new Block(mapBlockX, mapBlockY));
                }else if(deg >= 150 && deg < 210){ //find cyan
                    int mapghoombaX = x * MAPWIDTH /MAPIMGWIDTH;
                    int mapghoombaY = (MAPIMGHEIGHT-y) * MAPHEIGHT / MAPIMGHEIGHT;
                    ghoombas.add(new Ghoomba(mapghoombaX, mapghoombaY));
                }else if(deg >= 270 && deg < 330){
                    int mapHammerBroX = x * MAPWIDTH /MAPIMGWIDTH;
                    int mapHammerBroY = (MAPIMGHEIGHT-y) * MAPHEIGHT / MAPIMGHEIGHT;
                    // hammerBros.add(new HammerBro(mapHammerBroX, mapHammerBroY));
                }
            }
        }
        

    }
    
    private void startingArea()
    {
        if (maxBig == true)
        {
            addMainActor(new Max(), 50, 352, 250, 300);
        }
        if (maxSmall == true)
        {
            addMainActor(new Max(), 50, 352, 250, 300);
        }

        Ground thisGround ;
        int thisGroundX;
        int thisGroundY;
        Brick thisBrick;
        int thisBrickX;
        int thisBrickY;
        Pipe thisPipe;
        int thisPipeX;
        int thisPipeY;
        Block thisBlock;
        int thisBlockX;
        int thisBlockY;
        Ghoomba thisGhoomba;
        int thisGhoombaX;
        int thisGhoombaY;
        for(int i = 0; i < grounds.size(); i++){
            thisGround = grounds.get(i);
            thisGroundX = thisGround.mapX;
            thisGroundY = thisGround.mapY;
            addObject(thisGround, thisGroundX, 422-thisGroundY);

        }

        for(int i = 0 ; i < bricks.size(); i++){
            thisBrick = bricks.get(i);
            thisBrickX = thisBrick.mapX;
            thisBrickY = thisBrick.mapY;
            addObject(thisBrick, thisBrickX, 422-thisBrickY);
        }
        //System.out.println(pipes.size());
        for(int i = 0 ; i < pipes.size(); i++){
            thisPipe = pipes.get(i);
            thisPipeX = thisPipe.mapX;
            thisPipeY = thisPipe.mapY;
            addObject(thisPipe, thisPipeX , 422-thisPipeY);
        }

        for(int i = 0 ; i < blocks.size(); i++){
            thisBlock = blocks.get(i);
            thisBlockX = thisBlock.mapX;
            thisBlockY = thisBlock.mapY;
            addObject(thisBlock, thisBlockX , 422-thisBlockY);

        }

        for(int i = 0 ; i < ghoombas.size(); i++){
            thisGhoomba = ghoombas.get(i);
            thisGhoombaX = thisGhoomba.mapX;
            thisGhoombaY = thisGhoomba.mapY;
            addObject(thisGhoomba, thisGhoombaX , 422-thisGhoombaY);
        }

    
    
}
}