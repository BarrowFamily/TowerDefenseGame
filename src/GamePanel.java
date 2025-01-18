import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.awt.MouseInfo;

public class GamePanel extends JPanel implements ActionListener {

    static int windowWidth;
    static int windowHeight;


    private final int PATH_WIDTH = 25;
    private final int NUM_TOWERS = 8;



    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double screenWidth = screenSize.getWidth();
    double screenHeight = screenSize.getHeight();

    static final int DELAY = 5;
    Random random;
    public static boolean running = false;
    javax.swing.Timer timer;


    public static int[] mousePos = new int[2];
    public static int[] leftClickedLocation = new int[2];

    ImageObserver io = (img, infoflags, x, y, width, height) -> {
        return false;
    };


    public static boolean leftClicking = false;


    public static int[] xWalls = new int[13];
    public static int[] yWalls = new int[8];
    public static int[][][] tiles = new int[yWalls.length][xWalls.length][2];

    public static int[][] path;


    private int[] xPathOrigin;
    private int[] xPathExtent;

    private int[] yPathOrigin;
    private int[] yPathExtent;

    private boolean pathInit = false;


    Tower[] towers;

    //Tower firstTower = new Tower("src/Images/DeltaImage.png");
    Enemy firstEnemy;




    private Image tempSkin;

    GamePanel(){
        random = new Random();

        setScreenDimensions();

        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.addMouseListener(new MyMouseAdapter());
        this.setBackground(Color.white);
        startGame();
    }

    private void setScreenDimensions(){
        int wRatio = (int)screenWidth/16;
        int hRatio = (int)screenHeight/9;

        if (wRatio < hRatio){
            windowWidth = 16 * wRatio;
            windowHeight = 9 * wRatio;

        }
        else {
            windowWidth = 16 * hRatio;
            windowHeight = 9 * hRatio;
        }
        windowWidth  -= 50;
        windowHeight -= 100;
    }

    public void startGame(){
        running = true;
        timer = new javax.swing.Timer(DELAY,this);
        timer.start();

        findPlaceableLocations();
        firstEnemy = new Enemy();

        initTowers();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        getMousePos();

        if (running){

            drawPath(g);

            firstEnemy.drawTower(g, io);
            towers[0].drawTower(g, io);
            //firstTower.sendTelemetry();

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();

    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                default:
                    break;

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()){
                default:
                    break;
            }
        }
    }


    public class MyMouseAdapter extends MouseAdapter{

        @Override
        public void mousePressed(MouseEvent e){
            switch (e.getButton()){
                case MouseEvent.BUTTON1://left click
                    leftClicking = true;
                    leftClickedLocation[0] = MouseInfo.getPointerInfo().getLocation().x;
                    leftClickedLocation[1] = MouseInfo.getPointerInfo().getLocation().y;

                    break;
                case MouseEvent.BUTTON2://middle click

                    break;
                case MouseEvent.BUTTON3://right click

                    break;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e){
            switch (e.getButton()){
                case MouseEvent.BUTTON1://left click
                    leftClicking = false;
                    break;
                case MouseEvent.BUTTON2://middle click

                    break;
                case MouseEvent.BUTTON3://right click

                    break;
            }
        }
    }


    private void setPlaceableLocations(){

        for (int i = 1; i < xWalls.length-1; i++) {
            xWalls[i] = (windowWidth/(xWalls.length-1)) * i;
            //g.setColor(Color.gray);
            //g.fillRect(xWalls[i], 0, 2, 10000);
        }
        xWalls[xWalls.length-1] = windowWidth;
        for (int i = 1; i < yWalls.length-1; i++) {
            yWalls[i] = (windowHeight/(yWalls.length-1)) * i;
            //g.setColor(Color.gray);
            //g.fillRect(0, yWalls[i], 10000, 2);
        }
        yWalls[yWalls.length-1] = windowHeight;

    }



    private void findPlaceableLocations(){


        setPlaceableLocations();


        for (int i = 0; i < yWalls.length-2; i++) {

            for (int j = 0; j < xWalls.length-1; j++) {
                tiles[i][j][1] = (yWalls[i] + yWalls[i + 1])/2;
                tiles[i][j][0] = (xWalls[j] + xWalls[j + 1])/2;

            }
        }


        setPathCorners();

    }


    private void setPathCorners(){

        path = new int[7][2];

        path[0][0] = tiles[0][11][0]; path[0][1] = tiles[0][11][1];
        path[1][0] = tiles[0][8][0]; path[1][1] = tiles[0][8][1];
        path[2][0] = tiles[5][8][0]; path[2][1] = tiles[5][8][1];
        path[3][0] = tiles[5][3][0]; path[3][1] = tiles[5][3][1];
        path[4][0] = tiles[2][3][0]; path[4][1] = tiles[2][3][1];
        path[5][0] = tiles[2][0][0]; path[5][1] = tiles[2][0][1];

        //System.out.println(tiles[0][11][0] + ", " + tiles[0][11][1]);

    }

    private void drawPath(Graphics g){

        if (!pathInit){
            initPathToPaint();
            pathInit = true;
        }
        else{
            for (int i = 0; i < path.length - 2; i++) {
                g.fillRect(xPathOrigin[i] - PATH_WIDTH/2, yPathOrigin[i] - PATH_WIDTH/2, xPathExtent[i] + PATH_WIDTH, yPathExtent[i] + PATH_WIDTH);
            }
        }
    }


    private void initPathToPaint(){
        xPathOrigin = new int[path.length];
        xPathExtent = new int[path.length];

        yPathOrigin = new int[path.length];
        yPathExtent = new int[path.length];

        for (int i = 0; i < path.length-1; i++) {
            //x
            if (path[i][0] < path[i+1][0]){//if moving to the right
                xPathOrigin[i] = path[i][0];
                xPathExtent[i] = path[i+1][0] - path[i][0];
            }
            else{//if moving to the left
                xPathOrigin[i] = path[i+1][0];
                xPathExtent[i] = path[i][0] - path[i+1][0];
            }
            //y
            if (path[i][1] < path[i+1][1]){//if moving down
                yPathOrigin[i] = path[i][1];
                yPathExtent[i] = path[i+1][1] - path[i][1];
            }
            else{//if moving to the left
                yPathOrigin[i] = path[i+1][1];
                yPathExtent[i] = path[i][1] - path[i+1][1];
            }
        }

    }

    private void initTowers(){
        towers = new Tower[NUM_TOWERS];

        towers[0] = new Tower("src/Images/DeltaImage.png");


    }


    private void setIdleTowers(){



    }



    private void getMousePos(){
        mousePos[0] = MouseInfo.getPointerInfo().getLocation().x;
        mousePos[1] = MouseInfo.getPointerInfo().getLocation().y;
    }


}
