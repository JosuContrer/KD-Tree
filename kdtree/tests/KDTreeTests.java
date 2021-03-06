package kdtree.tests;

import kdtree.*;
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

    void createTestTree() {
        tree = new KDTree();
        tree.insert(new Point(0, 0));
        tree.insert(new Point(-2, 2));
        tree.insert(new Point(2, 0));
        tree.insert(new Point(-3, 3));
        tree.insert(new Point(-2, 0));
        tree.insert(new Point(2, 1));
        tree.insert(new Point(4, -3));
        tree.insert(new Point(-1, -2));
    }

    @Test
    public void nearestTest1() {
        createTestTree();
        assertEquals(tree.nearest(new Point(1, -2)).getPoint(), new Point(-1, -2));
    }

    @Test
    public void nearestTest2() {
        createTestTree();
        assertEquals(tree.nearest(new Point(1, 2)).getPoint(), new Point(2, 1));
    }

    @Test
    public void nearestTest3() {
        createTestTree();
        assertEquals(tree.nearest(new Point(-2, 3)).getPoint(), new Point(-3, 3));
    }

    @Test
    public void nearestTestContainsPoint() {
        createTestTree();
        assertEquals(tree.nearest(new Point(2, 0)).getPoint(), new Point(2, 0));
    }

    @Test
    public void balanceTest() {
        tree = new KDTree();
        tree.insert(new Point(0, 0));
        tree.insert(new Point(-1, 0));
        tree.insert(new Point(-1, -1));
        tree.insert(new Point(-2, -1));
        tree.insert(new Point(-2, -2));
        tree.insert(new Point(-3, -2));

        tree.balance();
        for(Point p : tree.inOrder()) {
            KDNode n = tree.get(p);
            System.out.println(n.getPoint());
        }
    }


    @Test
    public void KDTreeGeneration() {
        Point[] points = new Point[1000];

        for (int i = 0; i < 1000; i++) {
            points[i] = new Point((int) (Math.random() * 1000), (int) (Math.random() * 1000));
        }

        KDTree tree = new KDTree(KDFactory.generate(points));
        KDUtils.checkBalance(tree);
    }
}
