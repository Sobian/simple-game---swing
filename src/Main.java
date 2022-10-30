import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import javax.imageio.ImageIO;
import javax.swing.*;

class MyJFrame extends JFrame implements KeyListener {

    int delta = 10;

    public MyJFrame() {
        addKeyListener(
                this
        );
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!checkCollision() && Main.lives > 0) {
//            System.out.println("brak kolizji");
            if (e.getKeyChar() == 'w') {
                Main.player.setY(-delta);
                Main.img = Main.imgUp;
                if (Main.points >= 5)
                    Main.img = Main.luigiUp;
            }
            if (e.getKeyChar() == 's') {
                Main.player.setY(delta);
                Main.img = Main.imgDown;
                if (Main.points >= 5)
                    Main.img = Main.luigiDown;
            }
            if (e.getKeyChar() == 'a') {
                Main.player.setX(-delta);
                Main.img = Main.imgLeft;
                if (Main.points >= 5)
                    Main.img = Main.luigiLeft;
            }
            if (e.getKeyChar() == 'd') {
                Main.player.setX(delta);
                Main.img = Main.imgRight;
                if (Main.points >= 5)
                    Main.img = Main.luigiRight;
            }
        } else if (checkCollision()) {
//            System.out.println("kolizja");
            if (e.getKeyChar() == 'w') {
                Main.player.setY(2 * delta);

            }
            if (e.getKeyChar() == 's') {
                Main.player.setY(-2 * delta);
            }
            if (e.getKeyChar() == 'a') {
                Main.player.setX(2 * delta);
            }
            if (e.getKeyChar() == 'd') {
                Main.player.setX(-2 * delta);
            }
            if (e.getKeyChar() == ' ') {
                destroySquare();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(Main.player.getX() - 5, Main.player.getY() - 5, 110, 110);
    }

    public boolean checkCollision() {
        boolean col = false;
        Rectangle rect;
        for (int i = 0; i < Main.squares.size(); i++) {
            rect = new Rectangle(Main.squares.get(i).getX(), Main.squares.get(i).getY(), 100, 100);
            if (getBounds().intersects(rect))
                col = true;
        }
        return col;
    }

    public void destroySquare() {
        Rectangle rect;
        for (int i = 0; i < Main.squares.size(); i++) {
            rect = new Rectangle(Main.squares.get(i).getX(), Main.squares.get(i).getY(), 100, 100);
            if (getBounds().intersects(rect)) {
                Main.squares.remove(i);
                Main.squares.add(new Square());
            }
        }
    }

}

public class Main extends JPanel {


    //mario
    static BufferedImage imgLeft;
    static BufferedImage imgRight;
    static BufferedImage imgDown;
    static BufferedImage imgUp;
    static BufferedImage img;
    static BufferedImage dImg;
    //luigi
    static BufferedImage luigiRight;
    static BufferedImage luigiLeft;
    static BufferedImage luigiUp;
    static BufferedImage luigiDown;
    Font font = new Font("Helvetica", Font.PLAIN, 20);
    static Player player = new Player();
    public static Diamond diamond = new Diamond();
    static int points = 0;
    static int lives = 3;
    static int laserX;
    static int laserY;
    static Rectangle laserRect = new Rectangle();
    static ArrayList<Square> squares = listOfSq(5);

    {
        try {
            imgRight = ImageIO.read(new File("/Users/marcinsobianowski/Documents/Documents – MacBook Pro (Marcin)/korki/mario/src/assets/mario-old-right.png"));
            imgLeft = ImageIO.read(new File("/Users/marcinsobianowski/Documents/Documents – MacBook Pro (Marcin)/korki/mario/src/assets/mario-old-left.png"));
            imgDown = ImageIO.read(new File("/Users/marcinsobianowski/Documents/Documents – MacBook Pro (Marcin)/korki/mario/src/assets/mario-old-down.png"));
            imgUp = ImageIO.read(new File("/Users/marcinsobianowski/Documents/Documents – MacBook Pro (Marcin)/korki/mario/src/assets/mario-old-up.png"));
            dImg = ImageIO.read(new File("/Users/marcinsobianowski/Documents/Documents – MacBook Pro (Marcin)/korki/mario/src/assets/diamond.png"));
            luigiRight = ImageIO.read(new File("/Users/marcinsobianowski/Documents/Documents – MacBook Pro (Marcin)/korki/mario/src/assets/luigi/luigi-right.png"));
            luigiLeft = ImageIO.read(new File("/Users/marcinsobianowski/Documents/Documents – MacBook Pro (Marcin)/korki/mario/src/assets/luigi/luigi-left.png"));
            ;
            luigiUp = ImageIO.read(new File("/Users/marcinsobianowski/Documents/Documents – MacBook Pro (Marcin)/korki/mario/src/assets/luigi/luigi-up.png"));
            ;
            luigiDown = ImageIO.read(new File("/Users/marcinsobianowski/Documents/Documents – MacBook Pro (Marcin)/korki/mario/src/assets/luigi/luigi-down.png"));
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int distToDiam() {
        double tempX = player.getX() - diamond.getX();
        double tempY = player.getY() - diamond.getY();
        return (int) Math.sqrt(Math.abs(Math.pow(tempX, 2) - Math.pow(tempY, 2)));
    }

    public boolean checkDiamCollision() {
        boolean col = false;
        for (int i = 0; i < squares.size(); i++) {
            if (diamond.getBounds().intersects(squares.get(i).getBounds())) {
                col = true;
                System.out.println("kolizja z kwadratem");
                diamond = new Diamond();

            }

        }
        return col;
    }

    public void nextDiamond() {
        if (!checkDiamCollision()) {
            while (distToDiam() <= 20) {
                diamond.setX((int) (Math.random() * 800));
                diamond.setY((int) (Math.random() * 400));
                points++;
                System.out.println("1 up!");
            }
        } else
            diamond = new Diamond();

    }

    public static ArrayList<Square> listOfSq(int amount) {
        ArrayList<Square> list = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Square tempSq = new Square();
            while (tempSq.getBounds().intersects(player.getBounds())) {
                tempSq = new Square();
            }
            list.add(tempSq);
        }
        return list;
    }

    public boolean checkLaserCollision() {
        return player.getBounds().intersects(laserRect);
    }

    public void drawLaser() {
        ActionListener taskPerformer = e -> {
            int randPointer = (int) (Math.random() * squares.size());
            laserX = squares.get(randPointer).getX() + (squares.get(0).getSize() / 2);
            laserY = squares.get(randPointer).getY() + squares.get(0).getSize();
            laserRect.setLocation(squares.get(randPointer).getX() + (squares.get(0).getSize() / 2) - 2, squares.get(randPointer).getY() + squares.get(0).getSize());
            laserRect.setSize(4, 400);
            if (checkLaserCollision())
                lives--;
        };
        Timer timer = new Timer(1000, taskPerformer);
        timer.setRepeats(true);
        timer.start();
        if (lives <= 0)
            timer.stop();
    }

    public Dimension getPreferredSize() {
        return new Dimension(1300, 800);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String s = Integer.toString(points);
        g.setFont(font);
        g.drawString("lives: " + lives, 30, getHeight() - 60);
        g.drawString("score: " + s, 30, getHeight() - 30);
        nextDiamond();
        g.setColor(Color.red);
        g.drawLine(laserX, laserY, laserX, laserY + 400);

        for (Square square : squares) {
            g.setColor(Color.black);
            g.fillRect(square.getX(), square.getY(), square.getSize(), square.getSize());
        }
        g.drawImage(dImg, diamond.getX(), diamond.getY(), 100, 100, null, null);
        g.drawImage(img, player.getX(), player.getY(), 100, 100, null, null);

        if (lives <= 0) {
            Font font1 = new Font("Helvetica", Font.BOLD, 50);
            g.setFont(font1);
            g.setColor(Color.black);
            g.drawString("Game Over!", getWidth() / 2 - 110, getHeight() / 2);
        }
    }


    public void refreshAnim() {
        do {
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (lives >= 0);
    }

    public static void main(String[] args) {
        MyJFrame window = new MyJFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main mario = new Main();
        window.add(mario);
        window.setVisible(true);
        window.pack();
        mario.drawLaser();
        mario.refreshAnim();


    }
}