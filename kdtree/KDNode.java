package kdtree;

public class KDNode {

	public enum Orientation {
		HORIZONTAL,
		VERTICAL
	}
	
	public static Orientation other(Orientation o) {
		if(o == Orientation.HORIZONTAL) return Orientation.VERTICAL;
		return Orientation.HORIZONTAL;
	}
	
	private Point pt;
	private Orientation orient;
	private Region region;
	
	private KDNode above = null, below = null;
	
	public KDNode(Point pt, Orientation orient, Region region) {
		this.pt = pt;
		this.orient = orient;
		this.region = region;
	}
	
	public KDNode(Point pt, Orientation orient) {
		this(pt, orient, new Region());
	}
	
	public boolean isBelow(Point otherPt) {
		if(orient == Orientation.HORIZONTAL) {
			return otherPt.getX() < pt.getX();
		} else {
			return otherPt.getY() < pt.getY();
		}
	}
	
	public void add(Point pt) {
		
	}
	
	public KDNode createChild(Point pt, boolean below) {
		return null;
	}
}
