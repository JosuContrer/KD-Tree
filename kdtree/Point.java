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

    public String toString(){
        return "kdtree.Point x: " + Integer.toString(this.x) + " y: " + Integer.toString(this.y);
    }

    public boolean equals(Point p){
        return this.x == p.x && this.y == p.y;
    }

    public static void main(String[] args){
        int numPoints = 5;
        Point[] points = new Point[numPoints];

        for(int i = 0; i < numPoints; i++){
            points[i] = new Point(i,i+1);
        }

        for(Point point : points){
            System.out.println(point.toString());
        }
    }
}
