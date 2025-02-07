import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;

public class Tower {

    private int width = 150, height = 150, xPos = 0, yPos = 0;
    private int initX = 0, initY = 0;
    private Image towerImage;
    private int[] sides = new int[2];
    private boolean init = false;
    private int placeableTileIndicatorSize = 50;

    private boolean onTile = false;

    private int hp = 0, atk = 0, def = 0, atkR = 0, atkDelay = 0;
    private int atkInit = 0;


    private int[] pathIntercepts = new int[2];

    Tower(String skin){
        hp = 10;
        atk = 1;
        def = 0;
        atkR = 250;
        atkDelay = 60;

        xPos = GamePanel.windowHeight;
        yPos = GamePanel.windowWidth;
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
        trackEnemies(g);


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
                showRange(g);

            }//ignores the rest of these calculations if still dragging
            else if (GamePanel.leftClickedLocation[0] > xPos && GamePanel.leftClickedLocation[0] < xPos + width) {//check mouse in x
                if (GamePanel.leftClickedLocation[1] > yPos && GamePanel.leftClickedLocation[1] < yPos + height) {//check mouse in y
                    initX = xPos;
                    initY = yPos;//inits the tower to be moved by mouse

                    xPos = GamePanel.mousePos[0] - (GamePanel.leftClickedLocation[0] - initX);
                    yPos = GamePanel.mousePos[1] - (GamePanel.leftClickedLocation[1] - initY);

                    init = true;
                    onTile = false;
                }
            }
        }
        else{//on release of mouse
            init = false;

            if (!onTile) {
                for (int i = 0; i < GamePanel.tiles.length - 1; i++) {//checks if is on a placeable tile
                    for (int j = 0; j < GamePanel.tiles[0].length - 1; j++) {

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
            }
        }
    }



    public void sendTelemetry(){
        System.out.println("xPos: " + xPos);
        System.out.println("yPos: " + yPos);

    }

    public int getWidth(){
        return width;
    }

    public boolean getOnTile(){
        return onTile;
    }

    /**
     * @param newX x pos for center of tower
     * @param newY y pos for center of tower
     */
    public void setPos(int newX, int newY){
        xPos = newX - (width/2);
        yPos = newY - (height/2);
    }

    private void showRange(Graphics g){
        g.setColor(new Color(255,0,0, 52));
        g.fillOval(xPos - (atkR) + (width/2), yPos - (atkR) + (height/2), atkR * 2, atkR * 2);
    }

    private void trackEnemies(Graphics g){
        //tracking if an enemy is in attack range. Uses circle detection, not square and circle
        if(calcIntercepts(GamePanel.enemies[0])){
            attackEnemy(g, GamePanel.enemies[0]);
        }
    }

    private void attackEnemy(Graphics g, Enemy enemy){
        if (atkInit < GamePanel.frames){
            atkInit = GamePanel.frames + atkDelay;
        }
        else if (atkInit == GamePanel.frames){
            enemy.takeDamage(atk, g);
            System.out.println("ATTACK");
        }

    }

    private boolean calcIntercepts(Enemy enemy){
        double distance = Math.sqrt(Math.pow((xPos + ((double) width /2)) - enemy.getPosition()[0],2) + Math.pow((yPos + ((double) height /2)) - enemy.getPosition()[1],2));
        double radi = atkR + ((double)enemy.getWidth() / 2);
        return (distance - radi) < 0;
    }

}
