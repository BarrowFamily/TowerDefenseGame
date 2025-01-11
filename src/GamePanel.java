import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.Random;
import java.awt.MouseInfo;

public class GamePanel extends JPanel implements ActionListener {

    static int windowWidth;
    static int windowHeight;

    static final int MIN_Width = 800;
    static final int MIN_HEIGHT = 450;

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



    Tower firstTower = new Tower("src/Images/DeltaImage.png");




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
    }

    public void startGame(){
        running = true;
        timer = new javax.swing.Timer(DELAY,this);
        timer.start();

        initImage("src/Images/DeltaImage.png");

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        getMousePos();

        if (running){

            firstTower.drawTower(g, io);
            //firstTower.sendTelemetry();
        }
    }

    private void initImage(String fileName){
        Image tempIm;

        try{
            tempIm = ImageIO.read(new File(fileName));
            tempSkin = tempIm.getScaledInstance(400,400,1);
        }
        catch (Exception e){
            System.out.println("error: " + e);
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


    private void getMousePos(){
        mousePos[0] = MouseInfo.getPointerInfo().getLocation().x;
        mousePos[1] = MouseInfo.getPointerInfo().getLocation().y;
    }


}
