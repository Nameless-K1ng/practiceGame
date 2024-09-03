package entities;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;
import utils.LoadSave;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.InputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity
{
    private int animTick, animIndex, animSpeed = 20;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private float playerSpeed = 2.0f;
    
    private BufferedImage[][] animations;
    public Player (float x, float y, int w, int h){
        super(x,y,w,h);
        loadAnimation();
    }
    
    /* 
     * called in the game loop every 5M nanoseconds to update the logic
     * this will be called 200x in 1 sec 
     */
    public void update(){
        updatePos();
        animationTick();
        setAnimation();
    }
    
    /* 
     * called in the game loop every 8.3M nanoseconds to update the frame
     * this will be called 120x in 1 sec
     */
    public void render(Graphics g){
        g.drawImage(animations[playerAction][animIndex], (int)x, (int)y,w, h, null);
    }
    
    /*
     * The value of animSpeed (animation speed) determines when to switch to the next
     * sprite for redrawing. It ticks every 5M nanoseconds so it would take 100M nanoseconds
     * to change to another sprite.
     */
    public void animationTick(){
        animTick++;
        if (animTick >= animSpeed){
            animTick = 0;
            animIndex++;
            //animSpeed x 5Mnanosec = 100Mnanosec - next sprite
            if (animIndex >= getSpriteAmount(playerAction)){
                animIndex = 0;
                //set attacking to false every 100Mnanosec
                attacking = false;
            }
        }
    }
    
    private void setAnimation(){
        int startAnim = playerAction;
        
        if(moving){
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
        
        if(attacking){
            playerAction = ATTACK_1;
        }
        
        if (startAnim != playerAction){
            resetAnimTick();
        }
    }
    
    private void resetAnimTick(){
        animTick = 0;
        animIndex = 0;
    }
    
    private void updatePos(){
        
        moving = false;
        
        if (left && !right){
            x -= playerSpeed;
            moving = true;
        }else if (right && !left){
            x += playerSpeed;
            moving = true;
        }
        
        if (up && !down){
            y -= playerSpeed;
            moving = true;
        }else if (down && !up){
            y += playerSpeed;
            moving = true;
        }
    }
    
    public void loadAnimation(){
        
            BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
            
            animations = new BufferedImage[9][6];
            for (int j = 0; j < animations.length; j++){
                 for (int i = 0; i < 6; i++){
                    animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);   
                }
            }
        
    }
    
    public void resetDirBoolean(){
        left = false;
        up = false;
        right = false;
        down = false;
    }
    
    public void setAttacking(boolean attacking){
        this.attacking = attacking;
    }
    
    public boolean isLeft(){
        return left;
    }
    
    public void setLeft(boolean left){
        this.left = left;
    }
    
    public boolean isUp(){
        return up;
    }
    
    public void setUp(boolean up){
        this.up = up;
    }
    
    public boolean isRight(){
        return right;
    }
    
    public void setRight(boolean right){
        this.right = right;
    }
    
    public boolean isDown(){
        return down;
    }
    
    public void setDown(boolean down){
        this.down = down;
    }
    
    
}
