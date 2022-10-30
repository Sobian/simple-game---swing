import java.awt.*;

public class Player {

    public int x;
    public int y;
    public int size;

    public Player() {
        this.x = 30;
        this.y = 30;
        this.size = 100;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x += x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y += y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), 100, 100);
    }


}
