package input;

import main.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static utils.Constants.Directions.*;

public class KeyboardInputs implements KeyListener
{
    private GamePanel gamePanel;
    
    public KeyboardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    
    @Override
    public void keyTyped(KeyEvent e){
        //TODO
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().setUp(false);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().setDown(false);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setRight(false);
                break;
        }
        
        if(e.getKeyCode() == KeyEvent.VK_P){
           gamePanel.getGame().getPlayer().setAttacking(true);
       }
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().setUp(true);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().setDown(true);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setRight(true);
                break;
        }
    }
}
