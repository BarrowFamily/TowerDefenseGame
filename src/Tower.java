import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;

public class Tower {

    private int width = 300, height = 300, xPos = 0, yPos = 0;
    private int initX = 0, initY = 0;
    private Image towerImage;
    private int[] sides = new int[2];
    private boolean init = false;


    Tower(String skin){

        setImage(skin);

    }

    private void setImage(String fileName){
        Image tempIm;

        try{
            tempIm = ImageIO.read(new File(fileName));
            towerImage = tempIm.getScaledInstance(width,height,1);
        }
        catch (Exception e){
            System.out.println("error: " + e);
        }
    }

    public void drawTower(Graphics g, ImageObserver x){
        checkMouseDragging();

        g.drawImage(towerImage, xPos, yPos, x);

    }

    public void checkMouseDragging(){
        if (GamePanel.leftClicking) {//is clicking
            if (GamePanel.mousePos[0] > xPos && GamePanel.mousePos[0] < xPos + width) {//check mouse in x
                if (GamePanel.mousePos[1] > yPos && GamePanel.mousePos[1] < yPos + height) {//check mouse in y
                    xPos = GamePanel.mousePos[0] - (GamePanel.leftClickedLocation[0] - initX);
                    yPos = GamePanel.mousePos[1] - (GamePanel.leftClickedLocation[1] - initY);
                }
            }
        }
    }


    public void initPos(boolean initBool){
        if(initBool) {
            initX = xPos;
            initY = yPos;
        }
    }

    public void sendTelemetry(){
        System.out.println("xPos: " + xPos);
        System.out.println("yPos: " + yPos);

    }






}
