package main;

import javax.swing.JFrame;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class GameWindow
{
    private JFrame jframe;
    
    public GameWindow(GamePanel gamePanel)
    {
       jframe = new JFrame();
       
       jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       jframe.add(gamePanel);
       jframe.setLocationRelativeTo(null);
       jframe.setResizable(false);
       jframe.pack();//fit the size of the window to the preferred size of its component
       jframe.setVisible(true);
       jframe.addWindowFocusListener(new WindowFocusListener(){
            @Override
            public void windowLostFocus(WindowEvent e){
                gamePanel.getGame().windowFocusLost();
            }
            
            @Override
            public void windowGainedFocus(WindowEvent e){
                
            }
        });

    }

}
