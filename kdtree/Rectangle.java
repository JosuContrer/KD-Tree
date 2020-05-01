package kdtree;

public class Rectangle {

    private int x;
    private int y;
    private int h;
    private int w;

    public void Rectangle(){}

    public void Rectangle(int xi, int yi, int height, int width){
        this.x = xi;
        this.y = yi;
        this.h = height;
        this.w = width;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getH(){
        return this.h;
    }

    public int getW(){
        return this.w;
    }

    public String toString(){
        return "kdtree.Rectangle x: " + this.x + " y: " + this.y + " h: " + this.h + " w: " + this.w;
    }

}
