package kdtree;

import kdtree.KDNode.Orientation;

import java.util.Arrays;
import java.util.Comparator;

public class KDFactory {
	
	enum MedianPolicy {
		FIRST, LAST, MIDDLE
	}
	
	@SuppressWarnings("unused")
	private static int kdPartition(Point[] points, int lo, int hi, int pivotIdx, Orientation orient) {
		Point[] subTemp = Arrays.copyOfRange(points, lo, hi + 1);
		int i = lo;
		int j = hi;
		Point pivot = points[pivotIdx];
		
		for(Point pt : subTemp) {
			if(KDUtils.compareTo(pt, pivot, orient) < 0) points[i++] = pt;
			if(KDUtils.compareTo(pt, pivot, orient) > 0) points[j--] = pt;
			else if(!pt.equals(pivot)) points[i++] = pt;
		}
		
		points[j] = pivot;
		return j;
	}
	
	/**
	 * Find the median point for sorting, with the option to look for the first, middle, or last
	 * if there are multiple identical medians.
	 * @param points
	 * @param lo
	 * @param hi
	 * @param orient
	 * @param policy
	 * @return
	 */
	private static int findMedian(Point[] points, int lo, int hi, Orientation orient, MedianPolicy policy) {
		if(policy == MedianPolicy.FIRST) {
			int m = (lo + hi) / 2;
			while(m > lo && KDUtils.coord(points[m-1], orient)  
						 ==	KDUtils.coord(points[m], orient))
				m--;
			return m;
		} else if(policy == MedianPolicy.LAST) {
			int m = (lo + hi) / 2;
			while(m < hi && KDUtils.coord(points[m+1], orient)  
						 ==	KDUtils.coord(points[m], orient))
				m++;
			return m;
		} else {
			return (lo + hi) / 2;
		}
	}

	/**
	 * Creates Node for the points left and right with orientations
	 * @param points
	 * @param lo
	 * @param hi
	 * @param orient
	 * @return
	 */
	private static KDNode generateSubTree(Point[] points, int lo, int hi, Orientation orient) {
		if(hi < lo) {
			return null;
		} else if(hi == lo) {
			return new KDNode(points[lo], KDNode.other(orient));
		} else {
			Arrays.sort(points, lo, hi + 1, new Comparator<Point>() {
				@Override
				public int compare(Point arg0, Point arg1) {
					return KDUtils.compareTo(arg0, arg1, orient);
				}
			});
			
			int m = findMedian(points, lo, hi, orient, MedianPolicy.FIRST);
			KDNode subRoot = new KDNode(points[m], KDNode.other(orient));

			// Compute above and below sub-trees
			subRoot.below = generateSubTree(points, lo, m - 1, orient);
			subRoot.above = generateSubTree(points, m + 1, hi, orient);
			
			return subRoot;
		}
	}


	private static void propagate(KDNode node, Region region) {
		if(node != null) {
			node.region = region;
			
			if(node.below != null) {
				Region child = node.region.copy();
				
				if(node.orient == Orientation.VERTICAL) {
					child.setXmax(node.getPoint().getX());
				} else {
					child.setYmax(node.getPoint().getY());
				}
				
				propagate(node.below, child);
			}
			
			if(node.above != null) {
				Region child = node.region.copy();
				
				if(node.orient == Orientation.VERTICAL) {
					child.setXmin(node.getPoint().getX());
				} else {
					child.setYmin(node.getPoint().getY());
				}
				
				propagate(node.above, child);
			}
		}
	}
	
	public static KDNode generate(Point[] points) {
		if(points.length == 0) return null;
		
		KDNode root = generateSubTree(points, 0, points.length - 1, Orientation.VERTICAL);
		propagate(root, new Region());
		
		return root;
	}
	
}
