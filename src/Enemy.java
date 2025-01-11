import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;

public class Enemy {

    private int width = 100, height = 100, xPos = 1000, yPos = 500;
    private int hp = 0, def = 0, atk = 0, speed = 0;
    private Image enemyImage;

    Enemy(){
        hp = 10;
        def = 0;
        atk = 1;
        speed = 1;
        setImage("src/Images/minecraftCreeper.png");
    }


    private void setImage(String fileName){
        Image tempIm;

        try{
            tempIm = ImageIO.read(new File(fileName));
            enemyImage = tempIm.getScaledInstance(width,height,1);
        }
        catch (Exception e){
            System.out.println("error: " + e);
        }
    }


    public void drawTower(Graphics g, ImageObserver x){

        g.drawImage(enemyImage, xPos, yPos, x);

    }


}
