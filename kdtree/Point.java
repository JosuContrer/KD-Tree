package kdtree;

public class Point {

    private final double x;
    private final double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }

    public double distTo(Point pt) {return Math.sqrt(Math.pow(x - pt.getX(), 2) + Math.pow(y - pt.getY(), 2));}

    public String toString() { return "  Point x: " + this.x + " y: " + this.y; }

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
