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
    private boolean initColor = false;
    private int colorDelay = 0;
    private boolean alive = true;

    Enemy(){
        hp = 10;
        def = 0;
        atk = 1;
        speed = 1.5;

        xPos = GamePanel.tiles [GamePanel.pathForPeople[GamePanel.pathForPeople.length-1][0]] [GamePanel.pathForPeople[GamePanel.pathForPeople.length-1][1]] [0] - (double) width /2;
        yPos = GamePanel.tiles [GamePanel.pathForPeople[GamePanel.pathForPeople.length-1][0]] [GamePanel.pathForPeople[GamePanel.pathForPeople.length-1][1]] [1] - (double) height /2;

        location = GamePanel.pathForPeople.length-1;

        setImage("src/Images/minecraftCreeper.png");

    }
    Enemy(String enemyType){
        hp = 10;
        def = 0;
        atk = 1;
        speed = 1.5;

        xPos = GamePanel.tiles [GamePanel.pathForPeople[GamePanel.pathForPeople.length-1][0]] [GamePanel.pathForPeople[GamePanel.pathForPeople.length-1][1]] [0] - (double) width /2;
        yPos = GamePanel.tiles [GamePanel.pathForPeople[GamePanel.pathForPeople.length-1][0]] [GamePanel.pathForPeople[GamePanel.pathForPeople.length-1][1]] [1] - (double) height /2;

        location = GamePanel.pathForPeople.length-1;

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

    public void drawEnemy(Graphics g, ImageObserver x){
        if (alive) {
            g.drawImage(enemyImage, (int) xPos, (int) yPos, x);
            paintDamage(g);
            move();
        }
        else{

        }
    }

    private void move(){

        double xSign;
        double ySign;

        int[][][] tempTiles = GamePanel.tiles;
        int[][] tempPath = GamePanel.pathForPeople;

        int nextPathLocationX = tempPath[location - 1][0];
        int nextPathLocationY = tempPath[location - 1][1];


        //System.out.println("Xpos: " + (tempTiles[nextPathLocationX][nextPathLocationY][0] - (double) width / 2) + ". Current location: " + xPos + " Next location: " + (tempTiles[nextPathLocationX][nextPathLocationY][0] - (double) width / 2));
        //System.out.println("Ypos: " + (tempTiles[nextPathLocationX][nextPathLocationY][1] - (double) height / 2) + ". Current location: " + yPos);
        //System.out.println(); //testing movement code


        double nextLocationX = tempTiles[nextPathLocationX][nextPathLocationY][0] - (double) width / 2;
        double nextLocationY = tempTiles[nextPathLocationX][nextPathLocationY][1] - (double) height / 2;

        if ((xPos + (speed/2) + 1> nextLocationX && xPos - (speed/2) - 1 < nextLocationX) && (yPos + (speed/2) + 1 > nextLocationY && yPos - (speed/2) - 1 < nextLocationY)){
            if (location - 1 > 0) {
                location--;
            }
        }

        nextPathLocationX = tempPath[location - 1][0];//must update value after location change
        nextPathLocationY = tempPath[location - 1][1];

        xSign = Math.signum(tempTiles[nextPathLocationX][nextPathLocationY][0] - ((double) width / 2) - xPos);
        ySign = Math.signum(tempTiles[nextPathLocationX][nextPathLocationY][1] - ((double) height / 2) - yPos);






        xPos += (xSign * speed);
        yPos += (ySign * speed);

        if (speed > Math.abs(xPos - nextLocationX)){
            xPos += (xSign * Math.abs(xPos - nextLocationX));
        }
        if (speed > Math.abs(yPos - nextLocationY)){
            yPos += (ySign * Math.abs(yPos - nextLocationY));
        }

    }

    /**
     * @return middle of enemy
     */
    public double[] getPosition(){
        return new double[]{xPos + ((double) width /2),yPos + ((double) height /2)};
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void takeDamage(int damage, Graphics g){
        hp -= damage;
        initColor = true;

        if (hp == 0) {
            die();
        }
    }

    public void paintDamage(Graphics g){
        if (initColor){
            colorDelay = GamePanel.frames + 5;
            initColor = false;
        }
        if (colorDelay > GamePanel.frames){
            g.setColor(new Color(255,0,0, 86));
            g.fillRect((int) xPos,(int) yPos, width, height);
        }

    }

    private void die(){
        alive = false;


    }

}
