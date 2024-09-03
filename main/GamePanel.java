package main;

import input.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;
import static main.Game.GAME_WIDTH;
import static main.Game.GAME_HEIGHT;


public class GamePanel extends JPanel
{
    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game)
    {
        this.game = game;
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setPanelSize();
        
    }
    
    public void updateGame(){
        
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        game.render(g);
    }
    
    //sets the panel size then let the JFrame adjust to this panel size using the pack()
    public void setPanelSize(){
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        System.out.println("Width: " + GAME_WIDTH + "Height: " + GAME_HEIGHT);
    }
    
    public Game getGame(){
        return game;
    }
}
