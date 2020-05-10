package kdtree;

import kdtree.KDNode.Orientation;

import java.util.Iterator;
import java.util.LinkedList;

public class KDTree implements Iterable<Point> {

    protected KDNode root;
    protected int depth;

    public KDTree() {
        root = null;
    }

    /**
     * Insert a new point into the tree
     * @param pt point to insert
     * @return if the point could be added (duplicates are not allowed)
     */
    public boolean insert(Point pt) {
        if (root == null) {
            root = new KDNode(pt, Orientation.VERTICAL);
            return true;
        }

        return insert(pt, root);
    }

    //helper method//
    private boolean insert(Point pt, KDNode node) {
        // Compare the point to the current node
        int c = node.compareTo(pt);

        if (c == -1) { // The point is below n
            if (node.below == null) node.setBelow(pt); // We are done
            else return insert(pt, node.below);

        } else if (c == 1) { // The point is above n
            if (node.above == null) node.setAbove(pt);
            else return insert(pt, node.above);

        } else { // The point exists in the tree
            return false;
        }

        return true;
    }

    /**
     * Determines if a point is in this instance of KDTree
     *
     * @param pt point of interest
     * @return true if tree contains pt
     */
    public boolean contains(Point pt) {
        return contains(pt, root);
    }

    //helper method//
    private boolean contains(Point pt, KDNode node) {
        if (node == null) return false; // Base case

        // Compare the point to the current node
        int c = node.compareTo(pt);

        if (c == 0) 		return true;
        else if (c == -1) 	return contains(pt, node.below);
        else 				return contains(pt, node.above);

    }

    /**
     * Return the node associated with point pt
     *
     * @param pt point of interest
     * @return Instance of KDNode
     */
    public KDNode get(Point pt) {
        depth = 0;
        return get(pt, root);
    }

    //helper method//
    private KDNode get(Point pt, KDNode node) {
        if (node == null) return null; // Base case

        // Compare the point to the current node
        int c = node.compareTo(pt);
        depth++;

        if (c == 0) 		return node;
        else if (c == -1) 	return get(pt, node.below);
        else 				return get(pt, node.above);
    }

    /**
     * Return a list of all the nodes in order (left to right)
     *
     * @return Linked List of Points
     */
    public LinkedList<Point> inOrder() {
        return inOrder(root);
    }

    //helper method//
    private LinkedList<Point> inOrder(KDNode n) {
        LinkedList<Point> list = new LinkedList<>();
        if (n.below != null) {
            list.addAll(inOrder(n.below));
        }

        list.add(n.getPoint());

        if (n.above != null) {
            list.addAll(inOrder(n.above));
        }

        return list;
    }

    /**
     * Given a region find all points within the given space/region (inclusive)s.
     *
     * @param space
     * @return
     */
    public LinkedList<Point> search(Region space){

        LinkedList<Point> resultPoints = new LinkedList<Point>();
        search(space, root, resultPoints);
        return resultPoints;
    }

    // Helper Method
    public void search(Region space, KDNode parent, LinkedList<Point> points){
        // Base Case: Compare space to the parents region
        // If the region is contained by the space get all the points and return
        if(space.containsRegion(parent.region)){
            this.drain(parent, points);
            return;
        }

        // Space contains point of KDNode
        if(space.containsPoint(parent.getPoint())){ points.push(parent.getPoint()); }

        // Recursively search through the left and right subtrees
        // if(space.getXmin() < parent.region.getXmin()) { // TODO: check if this cropping is correct
            if (parent.below != null) { search(space, parent.below, points); }
        // }

        // if(space.getXmax() > parent.region.getXmax()) {
            if (parent.above != null) { search(space, parent.above, points); }
        // }

    }

    /**
     * Traversal from left to right of all descendant nodes in the tree rooted at
     * the given node.
     *
     * @param parent
     * @param points
     */
    private void drain(KDNode parent, LinkedList<Point> points){
        if(parent.below != null){ drain(parent.below, points);}
        points.push(parent.getPoint());
        if(parent.above != null){ drain(parent.above, points); }
    }

    @Override
    public Iterator<Point> iterator() {
        return this.inOrder().iterator();
    }

}
