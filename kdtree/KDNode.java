package kdtree;

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

    public Region getRegion() { return  region; }

    public Orientation getOrient(){ return orient; }

    public void setAbove(Point pt) {
        this.above = new KDNode(pt, other(this.orient));
        updateAboveRegion(region);
    }

    protected void setAbove(KDNode above) {
        this.above = above;
        if(above!=null) updateAboveRegion(region);
    }

    public void setBelow(Point pt) {
        this.below = new KDNode(pt, other(this.orient));
        updateBelowRegion(region.copy());
    }

    protected void setBelow(KDNode below) {
        this.below = below;
        if(below!=null) updateBelowRegion(region.copy());
    }

    /**
     * Recursively updates all the regions of the subtree rooted at this node
     * @param region Region to update to
     */
    public void updateRegions(Region region) {
        this.region = region;
        Region r = region.copy();

        if(above != null) {
            updateAboveRegion(r);
            above.updateRegions(r);
        }

        if(below != null) {
            r = region.copy();
            updateBelowRegion(r);
            below.updateRegions(r);
        }
    }

    private void updateAboveRegion(Region r) {
        if (orient == Orientation.VERTICAL) r.setXmin(pt.getX());
        else r.setYmin(pt.getY());

        this.above.region = r;
    }

    private void updateBelowRegion(Region r) {
        if (orient == Orientation.VERTICAL) r.setXmax(pt.getX());
        else r.setYmax(pt.getY());

        this.below.region = r;
    }
}
