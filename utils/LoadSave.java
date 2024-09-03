package utils;

import main.Game;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.IOException;
import javax.imageio.ImageIO;


public class LoadSave
{
    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";
    
    public static BufferedImage GetSpriteAtlas(String fileName){
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("../res/" + fileName);
        
        try{
            img = ImageIO.read(is);
            
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        
        return img;
    }
    
    
    //returns a two-dimentsional array of red values of an image
    public static int[][] GetLevelData(){
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        int rgbValue;
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        
        for (int j = 0; j < img.getHeight(); j++){//getHeight() returns the height in pixels
            for (int i = 0; i < img.getWidth(); i++){//getWidth() returns the width in pixels
                Color color = new Color(img.getRGB(i,j));//get the rgb values of the image located at (x,y) coordinates
                rgbValue = color.getRed();//get the red value of the color
                if (rgbValue >= 48)
                    rgbValue = 0;
                lvlData[j][i] = rgbValue;
            }
        }
        return lvlData;
    }
}
