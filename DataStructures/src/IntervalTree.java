/**
 * Created by Ethan on 15/11/21.
 * An interval tree is a red-black tree that maintains a dynamic set of elements.
 */
public class IntervalTree {
    /**
     * Class skeleton and initialization routines
     */
    private static final int BLACK = 1;
    private static final int RED = 0;
    private static int level = 0; // tree height

    public static class Interval {
        int low;
        int high;

        Interval(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }

    private static class RedBlackNode {
        Interval interval; // The interval value
        int key; // the low endpoint: x.interval.low
        int max; // the maximum value of any interval endpoint stored in the subtree rooted at x
        RedBlackNode leftNode; // left child
        RedBlackNode rightNode; // right child
        RedBlackNode parentNode; // parent
        int color; // color

        // Constructors
        RedBlackNode(Interval interval) {
            this(interval, interval.low, interval.high, null, null, null);
        }

        RedBlackNode(Interval interval, int key, int max, RedBlackNode leftNode, RedBlackNode rightNode, RedBlackNode
                parentNode) {
            this.interval = interval;
            this.key = key;
            this.max = max;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.parentNode = parentNode;
            this.color = IntervalTree.BLACK;
        }
    }

    private RedBlackNode root;
    private RedBlackNode nullNode;

    /**
     * Construct the tree.
     */
    public IntervalTree() {
        nullNode = new RedBlackNode(new Interval(Integer.MIN_VALUE, Integer.MIN_VALUE), Integer.MIN_VALUE, Integer.MIN_VALUE,
                null, null, null);
        nullNode.leftNode = nullNode.rightNode = nullNode;
        root = nullNode;
        root.parentNode = nullNode;
    }

    public static String intervalPrint(Interval i) {
        return ("[" + i.low + ", " + i.high + "]");
    }

    /**
     * Whether two intervals are overlapped
     */
    public static boolean overlap(Interval i1, Interval i2) {
        return (i1.low <= i2.high && i1.high >= i2.low);
    }

    /**
     * find a node in tree T whose interval equals interval i
     */
    public RedBlackNode itSearch(Interval i) {
        return itSearch(this.root, i);
    }

    private RedBlackNode itSearch(RedBlackNode n, Interval i) {
        if (n == nullNode || i.low == n.key) {
            return n;
        }
        if (i.low < n.key) {
            return itSearch(n.leftNode, i);
        } else {
            return itSearch(n.rightNode, i);
        }
    }

    /**
     * find a node in tree T whose interval overlaps interval i
     */
    private RedBlackNode intervalSearch(Interval i) {
        RedBlackNode x = this.root;
        while (x != this.nullNode && !overlap(i, x.interval)) {
            if (x.leftNode != this.nullNode && x.leftNode.max >= i.low) {
                x = x.leftNode;
            } else {
                x = x.rightNode;
            }
        }
        return x;
    }

    private RedBlackNode rbTreeMinimum(RedBlackNode n) {
        while (n.leftNode != nullNode) {
            n = n.leftNode;
        }
        return n;
    }

    /**
     * Print the tree contents by inorder traversal
     */
    public void rbPrintTree() {
        rbPrintTree(root, 0);
    }

    /**
     * Internal method to print a subtree by inorder traversal
     *
     * @param n     the node that roots the subtree
     * @param level tree height
     */
    private void rbPrintTree(RedBlackNode n, int level) {
        if (n != nullNode) {
            // left leaf node
            rbPrintTree(n.leftNode, (level + 1));

            if (n != this.root) {
                System.out.println(intervalPrint(n.interval) + "\tLevel: " + level + " Parent: " +
                        intervalPrint(n.parentNode.interval) + "\t\tColor: " + n.color);
            } else {
                System.out.println(intervalPrint(n.interval) + "\tLevel: " + level + " Parent: null" + "\t\tColor: " + n.color);
            }

            // right leaf node
            rbPrintTree(n.rightNode, (level + 1));
        }
    }

    /**
     * Preorder tree walk
     */
    public void rbPreorderTreeWalk() {
        rbPreorderTreeWalk(this.root, 0);
    }

    private void rbPreorderTreeWalk(RedBlackNode n, int level) {
        if (n != nullNode) {

            if (n != this.root) {
                System.out.println(intervalPrint(n.interval) + "\tLevel: " + level + " Parent: " +
                        intervalPrint(n.parentNode.interval) + "\t\tColor: " + n.color);
            } else {
                System.out.println(intervalPrint(n.interval) + "\tLevel: " + level + " Parent: null" + "\t\tColor: " + n.color);
            }

            // left leaf node
            rbPreorderTreeWalk(n.leftNode, (level + 1));
            // right leaf node
            rbPreorderTreeWalk(n.rightNode, (level + 1));

        }
    }

    /**
     * Rotate binary tree node with right child. (Left rotate)
     *
     * @param x old root
     */
    private void rbRotateWithRightChild(RedBlackNode x) {
//        System.out.println(x.key);
        RedBlackNode y = x.rightNode; // new root
        x.rightNode = y.leftNode; // turn y's left subtree into x's right subtree
        if (y.leftNode != this.nullNode) {
            y.leftNode.parentNode = x;
        }
        y.parentNode = x.parentNode; // link x's parent to y
        if (x.parentNode == this.nullNode) {
            this.root = y;
        } else if (x == x.parentNode.leftNode) {
            x.parentNode.leftNode = y;
        } else {
            x.parentNode.rightNode = y;
        }
        y.leftNode = x; // put x on y's left
        x.parentNode = y;

        y.max = x.max; // Maintaining max value
        x.max = Math.max(Math.max(x.leftNode.max, x.rightNode.max), x.max);
    }

    /**
     * Rotate binary tree node with left child. (Right rotate)
     */
    private void rbRotateWithLeftChild(RedBlackNode x) {
        RedBlackNode y = x.leftNode;
        x.leftNode = y.rightNode; // turn y's right subtree into x's left subtree
        if (y.rightNode != this.nullNode) {
            y.rightNode.parentNode = x;
        }
        y.parentNode = x.parentNode; // link x's parent to y
        if (x.parentNode == this.nullNode) {
            this.root = y;
        } else if (x == x.parentNode.leftNode) {
            x.parentNode.leftNode = y;
        } else {
            x.parentNode.rightNode = y;
        }
        y.rightNode = x; // put x on y's right
        x.parentNode = y;

        y.max = x.max; // Maintaining max value
        x.max = Math.max(Math.max(x.leftNode.max, x.rightNode.max), x.interval.high);
    }

    public void rbInsert(Interval d) {
        RedBlackNode z = new RedBlackNode(d);
        RedBlackNode y = this.nullNode;
        RedBlackNode x = this.root;
        while (x != this.nullNode) {
            y = x;
            if (z.key < x.key) {
                x.max = Math.max(x.max, z.interval.high); // Maintaining max value
                x = x.leftNode;
            } else if (z.key > x.key) {
                x.max = Math.max(x.max, z.interval.high); // Maintaining max value
                x = x.rightNode;
            }
        }
        z.parentNode = y;
        if (y == this.nullNode) {
            this.root = z;
        } else if (z.key < y.key) {
            y.leftNode = z;
        } else {
            y.rightNode = z;
        }
        z.leftNode = this.nullNode;
        z.rightNode = this.nullNode;
        z.color = RED;

        rbInsertFixup(z);
    }

    private void rbInsertFixup(RedBlackNode z) {
        while (z.parentNode.color == RED) {
            if (z.parentNode == z.parentNode.parentNode.leftNode) {
                RedBlackNode y = z.parentNode.parentNode.rightNode;
                if (y.color == RED) {
                    z.parentNode.color = BLACK; // case 1
                    y.color = BLACK;
                    z.parentNode.parentNode.color = RED;
                    z = z.parentNode.parentNode;
                } else {
                    if (z == z.parentNode.rightNode) {
                        z = z.parentNode; // case 2
                        rbRotateWithRightChild(z);
                    }
                    z.parentNode.color = BLACK; // case 3
                    z.parentNode.parentNode.color = RED;
                    rbRotateWithLeftChild(z.parentNode.parentNode);
                }
            } else {
                RedBlackNode y = z.parentNode.parentNode.leftNode;
                if (y.color == RED) {
                    z.parentNode.color = BLACK; // case 1
                    y.color = BLACK;
                    z.parentNode.parentNode.color = RED;
                    z = z.parentNode.parentNode;
                } else if (z == z.parentNode.leftNode) {
                    z = z.parentNode; // case 2
                    rbRotateWithLeftChild(z);
                } else {
                    z.parentNode.color = BLACK; // case 3
                    z.parentNode.parentNode.color = RED;
                    rbRotateWithRightChild(z.parentNode.parentNode);
                }
            }
        }
        this.root.color = BLACK;
    }

    private void rbTransplant(RedBlackNode u, RedBlackNode v) {
        if (u.parentNode == this.nullNode) {
            this.root = v;
        } else if (u == u.parentNode.leftNode) {
            u.parentNode.leftNode = v;
        } else {
            u.parentNode.rightNode = v;
        }
        v.parentNode = u.parentNode;
    }

    public void rbDelete(Interval i) {
        RedBlackNode z = this.itSearch(i);
        if (z == this.nullNode) {
            System.out.println("Deletion failed: No such data");
            return;
        }
        RedBlackNode y = z;
        RedBlackNode x;
        int yOriginalColor = y.color;
        if (z.leftNode == this.nullNode) {
            x = z.rightNode;
            rbTransplant(z, z.rightNode);
        } else if (z.rightNode == this.nullNode) {
            x = z.leftNode;
            rbTransplant(z, z.leftNode);
        } else {
            y = rbTreeMinimum(z.rightNode);
            yOriginalColor = y.color;
            x = y.rightNode;
            if (y.parentNode == z) {
                x.parentNode = y;
            } else {
                rbTransplant(y, y.rightNode);
                y.rightNode = z.rightNode;
                y.rightNode.parentNode = y;
            }
            rbTransplant(z, y);
            y.leftNode = z.leftNode;
            y.leftNode.parentNode = y;
            y.color = z.color;
        }

        RedBlackNode m = x; // Maintaining max value
        while (m != this.root) {
            m = m.parentNode;
            m.max = Math.max(Math.max(x.leftNode.max, x.rightNode.max), x.interval.high);
        }

        if (yOriginalColor == BLACK) {
            rbDeleteFixup(x);
        }
    }

    private void rbDeleteFixup(RedBlackNode x) {
        while (x != this.root && x.color == BLACK) {
            if (x == x.parentNode.leftNode) {
                RedBlackNode w = x.parentNode.rightNode;
                if (w.color == RED) {
                    w.color = BLACK; // case 1
                    x.parentNode.color = RED;
                    rbRotateWithRightChild(x.parentNode);
                    w = x.parentNode.rightNode;
                }
                if (w.leftNode.color == BLACK && w.rightNode.color == BLACK) {
                    w.color = RED; // case 2
                    x = x.parentNode;
                } else {
                    if (w.rightNode.color == BLACK) {
                        w.leftNode.color = BLACK; // case 3
                        w.color = RED;
                        rbRotateWithLeftChild(w);
                        w = x.parentNode.rightNode;
                    }
                    w.color = x.parentNode.color; // case 4
                    x.parentNode.color = BLACK;
                    w.rightNode.color = BLACK;
                    rbRotateWithRightChild(x.parentNode);
                    x = this.root;
                }
            } else {
                RedBlackNode w = x.parentNode.leftNode;
                if (w.color == RED) {
                    w.color = BLACK; // case 1
                    x.parentNode.color = RED;
                    rbRotateWithLeftChild(x.parentNode);
                    w = x.parentNode.leftNode;
                }
                if (w.leftNode.color == BLACK && w.rightNode.color == BLACK) {
                    w.color = RED; // case 2
                    x = x.parentNode;
                } else {
                    if (w.leftNode.color == BLACK) {
                        w.rightNode.color = BLACK; // case 3
                        w.color = RED;
                        rbRotateWithRightChild(w);
                        w = x.parentNode.leftNode;
                    }
                    w.color = x.parentNode.color; // case 4
                    x.parentNode.color = BLACK;
                    w.leftNode.color = BLACK;
                    rbRotateWithLeftChild(x.parentNode);
                    x = this.root;
                }
            }
        }
        x.color = BLACK;
    }

    public static void main(String[] args) {
        IntervalTree iTree = new IntervalTree();

        // Test insert
        iTree.rbInsert(new Interval(16, 21));
        iTree.rbInsert(new Interval(8, 9));
        iTree.rbInsert(new Interval(25, 30));
        iTree.rbInsert(new Interval(5, 8));
        iTree.rbInsert(new Interval(15, 23));
        iTree.rbInsert(new Interval(17, 19));
        iTree.rbInsert(new Interval(26, 26));
        iTree.rbInsert(new Interval(0, 3));
        iTree.rbInsert(new Interval(6, 10));
        iTree.rbInsert(new Interval(19, 20));

        System.out.println("Inorder tree walk: ");
        iTree.rbPrintTree();
        System.out.println("\nPreorder tree walk:");
        iTree.rbPreorderTreeWalk();

        // Test search
        RedBlackNode node1 = iTree.intervalSearch(new Interval(22, 25));
        System.out.print("\nThe interval overlapped with [22, 25]: ");
        if (node1 != iTree.nullNode) {
            System.out.println(intervalPrint(node1.interval));
        } else {
            System.out.println("No result!\n");
        }
        RedBlackNode node2 = iTree.intervalSearch(new Interval(31, 33));
        System.out.print("\nThe interval overlapped with [31, 33]: ");
        if (node2 != iTree.nullNode) {
            System.out.println(intervalPrint(node2.interval));
        } else {
            System.out.println("No result!\n");
        }

        // Test delete
        iTree.rbDelete(new Interval(8, 9));
        iTree.rbDelete(new Interval(17, 19));
        iTree.rbDelete(new Interval(25, 30));
        iTree.rbDelete(new Interval(25, 30)); // duplicated deletion

        System.out.println("\nAfter deletion: ");
        iTree.rbPrintTree();
    }
}
