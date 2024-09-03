package input;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import main.GamePanel;
import java.awt.Graphics;



public class MouseInputs implements MouseListener, MouseMotionListener
{
    private GamePanel gamePanel;
    
    public MouseInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        
    }

    @Override
    public void mouseReleased(MouseEvent e){
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
       gamePanel.requestFocus();
       if(e.getButton() == MouseEvent.BUTTON1){
           gamePanel.getGame().getPlayer().setAttacking(true);
       }
    }
    
    @Override
    public void mouseExited(MouseEvent e){
        
    }
    
    @Override
    public void mouseEntered(MouseEvent e){
        
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        
    }
}
