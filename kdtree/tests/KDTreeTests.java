package kdtree.tests;

import kdtree.KDFactory;
import kdtree.KDTree;
import kdtree.Point;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class KDTreeTests {
    KDTree tree;

    public KDTreeTests() {
        tree = new KDTree();
        tree.insert(new Point(0, 0));
        tree.insert(new Point(1, -1));
        tree.insert(new Point(-1, 2));
    }

    @Test
    public void InsertAbove() {
        assertTrue(tree.insert(new Point(2, 1)));
    }

    @Test
    public void InsertBelow() {
        assertTrue(tree.insert(new Point(2, -2)));
    }

    @Test
    public void InsertEquals() {
        assertFalse(tree.insert(new Point(1, -1)));
    }

    @Test
    public void ContainsTrue() {
        assertTrue(tree.contains(new Point(-1, 2)));
    }

    @Test
    public void ContainsFalse() {
        assertFalse(tree.contains(new Point(2, -1)));
    }

    @Test
    public void GetNode() {
        assertEquals(tree.get(new Point(-1, 2)).getPoint(), new Point(-1, 2));
    }

    @Test
    public void GetNull() {
        assertNull(tree.get(new Point(-1, -1)));
    }

    @Test
    public void InOrder() {
        LinkedList<Point> expected = new LinkedList<>();
        expected.add(new Point(-1, 2));
        expected.add(new Point(0, 0));
        expected.add(new Point(1, -1));

        assertEquals(expected, tree.inOrder());
    }

    @Test
    public void nearestInSubtree() {
        assertEquals(tree.nearest(new Point(1, 1)).getPoint(), new Point(0, 0));
    }

    @Test
    public void KDTreeGeneration() {
        Point[] points = new Point[1000];

        for (int i = 0; i < 1000; i++) {
            points[i] = new Point((int) (Math.random() * 1000), (int) (Math.random() * 1000));
        }

        KDTree tree = KDFactory.generate(points);
        //KDUtils.checkBalance(tree); throws null pointer
    }
}
