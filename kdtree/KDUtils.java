package kdtree;

import kdtree.KDNode.Orientation;

public class KDUtils {

	public static int coord(Point p, Orientation orient) {
		if(orient == Orientation.HORIZONTAL) return p.getX();
		else return p.getY();
	}
	
	public static int compareTo(Point p, Point q, Orientation orient) {
		if(orient == Orientation.HORIZONTAL) return p.getX() - q.getX();
		else return p.getY() - q.getY();
	}
	
	public static void checkBalance(KDTree tree) {
		int minDepth = Integer.MAX_VALUE;
		int maxDepth = Integer.MIN_VALUE;
		double avgDepth = 0;
		int N = 0;
		
		for(Point point : tree) {
			KDNode node = tree.get(point);

			if(node.getAbove() == null && node.getBelow() == null) {
				if(tree.depth > maxDepth)
					maxDepth = tree.depth;
				if(tree.depth < minDepth)
					minDepth = tree.depth;
				avgDepth += tree.depth;
				N++;
			}
		}
		
		avgDepth /= N;
		
		System.out.println("Number of leaves in KD tree: " + N);
		System.out.println("Minimum depth of leaves in KD tree: " + minDepth);
		System.out.println("Maximum depth of leaves in KD tree: " + maxDepth);
		System.out.println("Average depth of leaves in KD tree: " + avgDepth);
	}
	
}
