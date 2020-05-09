package kdtree;

import java.util.LinkedList;

public class KDNode {

	// Orientation of the slice in the region
    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    public static Orientation other(Orientation o) {
        if (o == Orientation.HORIZONTAL) return Orientation.VERTICAL;
        return Orientation.HORIZONTAL;
    }

    private Point pt;

    protected Orientation orient;
    protected Region region;

    protected KDNode above = null, below = null;

    public KDNode(Point pt, Orientation orient, Region region) {
        this.pt = pt;
        this.orient = orient;
        this.region = region;
    }

    public KDNode(Point pt, Orientation orient) {
        this(pt, orient, new Region());
    }

    public int compareTo(Point otherPt) {
        if(pt.equals(otherPt)) return 0;

        if (orient == Orientation.VERTICAL) {
			return otherPt.getX() < pt.getX()? -1 : 1;
        } else {
            return otherPt.getY() < pt.getY()? -1 : 1;
        }
    }

    public double perpendicularDistance(Point otherPt) {
        if(orient == Orientation.HORIZONTAL)    return Math.abs(otherPt.getY() - this.pt.getY());
        else                                    return Math.abs(otherPt.getX() - this.pt.getX());
    }

    public Point getPoint() {
        return pt;
    }

    public KDNode getAbove() {
        return above;
    }

    public KDNode getBelow() {
        return below;
    }

    public void setAbove(Point pt) {
		Region r = region.copy();

		if(orient == Orientation.VERTICAL) r.setXmin(pt.getX());
        else 	r.setYmin(pt.getY());

        this.above = new KDNode(pt, other(this.orient), r);
    }

    public void setBelow(Point pt) {
        Region r = region.copy();

        if(orient == Orientation.VERTICAL) r.setXmax(pt.getX());
        else	r.setYmax(pt.getY());

        this.below = new KDNode(pt, other(this.orient), r);
    }
}
