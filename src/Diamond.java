import java.awt.*;

public class Diamond {

    int x;
    int y;

    public Diamond(){
        this.x = (int) (Math.random()*1000);
        this.y = (int) (Math.random()*800);
    }

    public Diamond(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), 100, 100);
    }

}
