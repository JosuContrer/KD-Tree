package kdtree;

import java.util.Iterator;
import java.util.LinkedList;

import kdtree.KDNode.Orientation;

public class KDTree implements Iterable<Point> {
	
	class KDIterator implements Iterator<Point> {

		private LinkedList<Point> list;
		
		public KDIterator(KDNode root) {
			list = new LinkedList<>();
			root.inorder(list);
		}
		
		@Override
		public boolean hasNext() {
			return !list.isEmpty();
		}

		@Override
		public Point next() {
			return list.removeFirst();
		}
		
	}
	
	protected KDNode root;
	protected int depth;

	public KDTree() {
		root = null;
	}
	
	public void add(Point pt) {
		if(root != null) {
			root.add(pt);
		} else {
			root = new KDNode(pt, Orientation.VERTICAL);
		}
	}
	
	private boolean containsInternal(Point pt, KDNode node) {
		if(pt.equals(node.getPoint())) {
			return true;
		} else {
			if(node.isBelow(pt) && node.getBelow() != null) {
				return containsInternal(pt, node.getBelow());
			} 
			
			if(!node.isBelow(pt) && node.getAbove() != null) {
				return containsInternal(pt, node.getAbove());
			}
		}
		
		return false;
	}
	
	public boolean contains(Point pt) {
		return containsInternal(pt, root);
	}
	
	private KDNode getInternal(Point pt, KDNode node) {
		depth++;
		if(pt.equals(node.getPoint())) {
			return node;
		} else {
			if(node.isBelow(pt) && node.getBelow() != null) {
				KDNode belowNode = getInternal(pt, node.getBelow());
				if(belowNode != null) return belowNode;
			} 
			
			if(!node.isBelow(pt) && node.getAbove() != null) {
				KDNode aboveNode = getInternal(pt, node.getAbove());
				if(aboveNode != null) return aboveNode;
			}
		}
		
		return null;
	}
	
	public KDNode get(Point pt) {
		depth = 0;
		return getInternal(pt, root);
	}

	@Override
	public Iterator<Point> iterator() {
		return new KDIterator(root);
	}
	
	public static void main(String[] args) {
		KDTree tree = new KDTree();
		
		Point[] points = new Point[1000];
		
		for(int i=0; i<1000; i++) {
			points[i] = new Point((int) (Math.random() * 1000), (int) (Math.random() * 1000));
		}

		tree = KDFactory.generate(points);
		KDUtils.checkBalance(tree);
	}
}
