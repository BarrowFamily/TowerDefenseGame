import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;

public class Enemy {

    private int width = 100, height = 100;
    private double xPos = 0, yPos = 0;
    private int hp = 0, def = 0, atk = 0;
    private double speed = 0;
    private Image enemyImage;
    private int location = 0;

    Enemy(){
        hp = 10;
        def = 0;
        atk = 1;
        speed = 1;

        xPos = GamePanel.path[0][0] - (double) width /2;
        yPos = GamePanel.path[0][1] - (double) height /2;

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

        g.drawImage(enemyImage, (int) xPos, (int) yPos, x);
        move();

    }


    private void move(){

        double xSign;
        double ySign;

        if (xPos == GamePanel.path[location+1][0] - (double) width/2  &&  yPos == GamePanel.path[location+1][1] - (double) height/2){
            location++;
        }

        xSign = Math.signum(GamePanel.path[location+1][0] - GamePanel.path[location][0]);
        ySign = Math.signum(GamePanel.path[location+1][1] - GamePanel.path[location][1]);

        xPos += (xSign * speed);
        yPos += (ySign * speed);

    }




}
