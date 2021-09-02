package GamePlay;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Card {
    private int x = 0;//x
    private int y = 0;//y
    private int width = 0;//width
    private int height = 0;//height
    private int i = 0;//row
    private int j = 0;//collum
    private int index = 0;//index
    private int type = 0;//0 new image  1 chose image 2 null
    private BufferedImage image = null;
    private BufferedImage overImage = null;
    private GamePanel panel = null;

    public Card(int x, int y, int index, int i, int j, GamePanel panel) {
        this.x = x;
        this.y = y;
        this.panel = panel;
        this.index = index;
        this.i = i;
        this.j = j;
        //System.out.println(index);
        this.image = GamePanel.cardList.get(index);
        this.overImage = GamePanel.chooseCard.get(index);
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
    }

    public void draw(Graphics g) {
        if (type == 0) {
            g.drawImage(image, x, y, width, height, null);
        } else if (type == 1) {
            g.drawImage(overImage, x, y, width, height, null);
        } else if (type == 2) {
        }
    }

    boolean isPoint(int x, int y) {

        if (x > this.x && y > this.y
                && x < this.x + this.width && y < this.y + this.height) {
            return true;
        }
        return false;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }

    public int getI() {

        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {

        return j;
    }

    public void setJ(int j) {

        this.j = j;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
