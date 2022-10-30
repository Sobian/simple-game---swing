import javax.swing.*;
import java.awt.*;

public class GameOver extends JPanel {

    Font font = new Font("Helvetica", Font.BOLD, 50);

    public Dimension getPreferredSize() {
        return new Dimension(1300, 800);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString("Game Over", getWidth()/2-120, getHeight()/2);
    }

    public static void main(String[] args) {
        JFrame gOverFrame = new JFrame();
        gOverFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GameOver gOver = new GameOver();
        gOverFrame.add(gOver);
        gOverFrame.setVisible(true);
        gOverFrame.pack();
    }

}
