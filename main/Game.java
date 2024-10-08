package main;

import entities.Player;
import java.awt.Graphics;
import levels.LevelManager;

public class Game implements Runnable
{
    private GameWindow gamewindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player player;
    private LevelManager levelManager;
    
    public static final int TILES_DEFAULT_SIZE = 32;
    public static final float SCALE = 2f;
    public static final int TILES_IN_WIDTH = 26;//number of tiles horizontally
    public static final int TILES_IN_HEIGHT = 14;//number of tiles vertically
    public static final int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public static final int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public static final int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    
    public Game()
    {
        initClasses();
        gamePanel = new GamePanel(this);
        gamewindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }
    
    public void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void update(){
        player.update();
        levelManager.update();
    }
    
    public void render(Graphics g){
        levelManager.draw(g);
        player.render(g);
    }
    
    private void initClasses(){
        player = new Player(200,200, (int)(64 * SCALE), (int)(40 * SCALE));
        levelManager = new LevelManager(this);
    }
    
    //this is the game loop
    @Override
    public void run(){
        
        //
        double timePerFrame = 1000000000.0 / FPS_SET;//8.3M nanoseconds per frame
        double timePerUpdate = 1000000000.0 / UPS_SET; //5M nanoseconds per update
        
        long previousTime = System.nanoTime();//used for UPS
        
        int frames = 0;
        int updates = 0;//used for UPS
        long lastCheck = System.currentTimeMillis();
        
        double deltaU = 0;
        double deltaF = 0;
        
        while (true){
            
            long currentTime = System.nanoTime();
            
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            
            previousTime = currentTime;
            
            if (deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }
            
            if (deltaF >= 1){
                gamePanel.repaint();
                frames++;
                deltaF--;
            }
            
            //prints the FPS and UPS
            if (System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public void windowFocusLost(){
        player.resetDirBoolean();
    }
}
