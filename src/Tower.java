import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;

public class Tower {

    private int width = 200, height = 200, xPos = 0, yPos = 0;
    private int initX = 0, initY = 0;
    private Image towerImage;
    private int[] sides = new int[2];
    private boolean init = false;
    private int placeableTileIndicatorSize = 50;

    private boolean onTile = false;

    private int hp = 0, atk = 0, def = 0;


    Tower(String skin){
        hp = 10;
        atk = 1;
        def = 0;


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
        checkMouseDragging(g);


        g.drawImage(towerImage, xPos, yPos, x);

    }

    private void checkMouseDragging(Graphics g){
        if (GamePanel.leftClicking) {//is clicking
            if (init){//while being dragged
                xPos = GamePanel.mousePos[0] - (GamePanel.leftClickedLocation[0] - initX);
                yPos = GamePanel.mousePos[1] - (GamePanel.leftClickedLocation[1] - initY);


                for (int i = 0; i < GamePanel.tiles.length-1; i++) {//shows grey blocks
                    for (int j = 0; j < GamePanel.tiles[0].length-1; j++) {

                        if (GamePanel.tiles[i][j][0] != 0) {
                            g.setColor(Color.gray);
                            g.fillRect(GamePanel.tiles[i][j][0] - placeableTileIndicatorSize / 2, GamePanel.tiles[i][j][1] - placeableTileIndicatorSize / 2, placeableTileIndicatorSize, placeableTileIndicatorSize);
                        }

                    }
                }

            }//ignores the rest of these calculations if still dragging
            else if (GamePanel.leftClickedLocation[0] > xPos && GamePanel.leftClickedLocation[0] < xPos + width) {//check mouse in x
                if (GamePanel.leftClickedLocation[1] > yPos && GamePanel.leftClickedLocation[1] < yPos + height) {//check mouse in y
                    initX = xPos;
                    initY = yPos;//inits the tower to be moved by mouse

                    xPos = GamePanel.mousePos[0] - (GamePanel.leftClickedLocation[0] - initX);
                    yPos = GamePanel.mousePos[1] - (GamePanel.leftClickedLocation[1] - initY);

                    init = true;
                }
            }
        }
        else{//on release of mouse
            init = false;
            boolean onTile = false;

            for (int i = 0; i < GamePanel.tiles.length-1; i++) {//checks if is on a placeable tile
                for (int j = 0; j < GamePanel.tiles[0].length-1; j++) {

                    if (GamePanel.tiles[i][j][0] != 0) {
                        if (GamePanel.tiles[i][j][0] > xPos && GamePanel.tiles[i][j][0] < xPos + width) {
                            if (GamePanel.tiles[i][j][1] > yPos && GamePanel.tiles[i][j][1] < yPos + height) {
                                xPos = GamePanel.tiles[i][j][0] - width / 2;
                                yPos = GamePanel.tiles[i][j][1] - height / 2;
                                onTile = true;
                            }
                        }
                    }

                }
            }
            if (!onTile){//otherwise goes back to corner
                xPos = GamePanel.windowWidth - width;
                yPos = GamePanel.windowHeight - height;
            }
        }
    }


    public void sendTelemetry(){
        System.out.println("xPos: " + xPos);
        System.out.println("yPos: " + yPos);

    }






}
