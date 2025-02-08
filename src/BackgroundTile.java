import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.Objects;

public class BackgroundTile {

    int xPos = 0, yPos = 0;
    Image tileImage;
    int width = 100, height = 100;


    BackgroundTile(int xPos, int yPos, String tileState, int width, int height){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;

        if (tileState.equals("Path")){
            setImage("src/Images/Background/Path.PNG");
        }
        else if(tileState.equals("Grass")){
            setImage("src/Images/Background/Grass.PNG");
        }
        else{
            setImage("src/Images/Background/Grass.PNG");
        }
    }

    public void drawTile(Graphics g, ImageObserver x){
        g.drawImage(tileImage, xPos, yPos, x);
    }
    private void setImage(String fileName){
        Image tempIm;

        try{
            tempIm = ImageIO.read(new File(fileName));
            tileImage = tempIm.getScaledInstance(width,height,1);
        }
        catch (Exception e){
            System.out.println("error: " + e);
        }
    }
}
