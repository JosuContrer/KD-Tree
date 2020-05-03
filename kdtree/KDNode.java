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
	
	public boolean add(Point pt) {
		if(pt.equals(this.pt)) {
			return false;
		}
		
		if(isBelow(pt)) {
			if(below != null) {
				return below.add(pt);
			} else {
				below = createChild(pt, true);
			}
		} else {
			if(above != null) {
				return above.add(pt);
			} else {
				above = createChild(pt, false);
			}
		}
		
		return true;
	}
	
	public KDNode createChild(Point pt, boolean below) {
		Region newRegion = region.copy();
		
		if(orient == Orientation.VERTICAL) {
			if(below) {
				newRegion.setXmax(pt.getX());
			} else {
				newRegion.setXmin(pt.getX());
			}
		} else {
			if(below) {
				newRegion.setYmax(pt.getY());
			} else {
				newRegion.setYmin(pt.getY());
			}
		}
		
		return new KDNode(pt, other(orient), newRegion);
	}
}
