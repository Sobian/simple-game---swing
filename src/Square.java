import java.awt.*;

public class Square {

    int x;
    int y;
    int size = 100;
    int[] xCoord;
    int[] yCoord;

    public Square(){
        xCoord = new int[5];
        yCoord = new int[5];

        this.x = (int) (Math.random()*800);
        this.y = (int) (Math.random()*500);
    }

//    boolean checkArrForDuplicate(){
//        boolean check =false;
//        for (int i =0;i<xCoord.length;i++){
//            int tempX = xCoord[i];
//            int tempY = xCoord[i];
//            for (int j=i+1;j<xCoord.length;j++){
//                if (tempX)
//            }
//        }
//    }

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

    public int getSize() {
        return size;
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), 100, 100);
    }





}
