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
	
	private KDNode root;

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

	@Override
	public Iterator<Point> iterator() {
		return new KDIterator(root);
	}
	
	public static void main(String[] args) {
		KDTree tree = new KDTree();
		tree.add(new Point(7, 7));
		tree.add(new Point(5, 3));
		tree.add(new Point(2, 5));
		tree.add(new Point(8, 8));
		
		Iterator<Point> iter = tree.iterator();
		
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}
