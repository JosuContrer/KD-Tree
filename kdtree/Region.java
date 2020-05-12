package kdtree;

public class Region {

	private double xmin;
	private double xmax;
	private double ymin;
	private double ymax;
	
	public Region() {
		this(Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public Region(double xmin, double xmax, double ymin, double ymax) {
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
	}
	
	public Region copy() {
		return new Region(xmin, xmax, ymin, ymax);
	}
	
	public Region unionPoint(Point pt) {
		double mx1 = Math.min(xmin, pt.getX());
		double mx2 = Math.max(xmax, pt.getX());
		double my1 = Math.min(ymin, pt.getY());
		double my2 = Math.max(ymax, pt.getY());
		
		return new Region(mx1, mx2, my1, my2);
	}
	
	public boolean containsRegion(Region r) {
		return r.xmin >= xmin && r.xmax <= xmax && r.ymin >= ymin && r.ymax <= ymax;
	}
	
	public boolean containsPoint(Point pt) {
		return pt.getX() >= xmin && pt.getX() <= xmax && pt.getY() >= ymin && pt.getY() <= ymax;
	}

	public double getXmin() {
		return xmin;
	}

	public void setXmin(double xmin) {
		this.xmin = xmin;
	}

	public double getXmax() {
		return xmax;
	}

	public void setXmax(double xmax) {
		this.xmax = xmax;
	}

	public double getYmin() {
		return ymin;
	}

	public void setYmin(double ymin) {
		this.ymin = ymin;
	}

	public double getYmax() {
		return ymax;
	}

	public void setYmax(double ymax) {
		this.ymax = ymax;
	}

	public String toString(){
		Integer xMin = (int)this.xmin;
		Integer xMax = (int)this.xmax;
		Integer yMin = (int)this.ymin;
		Integer yMax = (int)this.ymax;

		if(xMin == Integer.MAX_VALUE){ xMin = 11111; }
		if(xMin == Integer.MIN_VALUE){ xMin = 0; }
		if(xMax == Integer.MAX_VALUE){ xMax = 11111; }
		if(xMax == Integer.MIN_VALUE){ xMax = 0; }

		if(yMin == Integer.MAX_VALUE){ yMin = 11111; }
		if(yMin == Integer.MIN_VALUE){ yMin = 0; }
		if(yMax == Integer.MAX_VALUE){ yMax = 11111; }
		if(yMax == Integer.MIN_VALUE){ yMax = 0; }
		return "  Region: xmin-" + xMin + " xmax-" + xMax +
				"\n                 ymin-" + yMin + " ymax-" + yMax; }
	
}
