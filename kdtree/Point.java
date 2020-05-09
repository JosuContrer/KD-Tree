package kdtree;

public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public double distTo(Point pt) {return Math.sqrt(Math.pow(x - pt.getX(), 2) + Math.pow(y - pt.getY(), 2));}

    public String toString() { return "kdtree.Point x: " + this.x + " y: " + this.y; }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o instanceof Point)
            return equals((Point) o);

        return false;
    }

    public boolean equals(Point p){
        return this.x == p.x && this.y == p.y;
    }
}
