package kdtree;

public class Region {

	private int xmin, xmax, ymin, ymax;
	
	public Region() {
		this(Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public Region(int xmin, int xmax, int ymin, int ymax) {
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
	}
	
	public Region unionPoint(Point pt) {
		int mx1 = Math.min(xmin, pt.getX());
		int mx2 = Math.max(xmax, pt.getX());
		int my1 = Math.min(ymin, pt.getY());
		int my2 = Math.max(ymax, pt.getY());
		
		return new Region(mx1, mx2, my1, my2);
	}
	
	public boolean containsRegion(Region r) {
		return r.xmin >= xmin && r.xmax <= xmax && r.ymin >= ymin && r.ymax <= ymax;
	}
	
	public boolean containsPoint(Point pt) {
		return pt.getX() >= xmin && pt.getX() <= xmax && pt.getY() >= ymin && pt.getY() <= ymax;
	}

	public int getXmin() {
		return xmin;
	}

	public void setXmin(int xmin) {
		this.xmin = xmin;
	}

	public int getXmax() {
		return xmax;
	}

	public void setXmax(int xmax) {
		this.xmax = xmax;
	}

	public int getYmin() {
		return ymin;
	}

	public void setYmin(int ymin) {
		this.ymin = ymin;
	}

	public int getYmax() {
		return ymax;
	}

	public void setYmax(int ymax) {
		this.ymax = ymax;
	}
	
}
