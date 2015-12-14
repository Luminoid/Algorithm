package Trees;

/**
 * Created by Ethan on 15/11/21.
 * An order-statistic tree T is simply a red-black tree with additional size information stored in each node.
 */
public class OrderStatisticTree {
    /**
     * Class skeleton and initialization routines
     */
    private static final int BLACK = 1;
    private static final int RED = 0;
    private static int level = 0; // tree height

    private static class Data {
        String key;
        String satellite;

        Data(String key) {
            this(key, "");
        }

        Data(String key, String satellite) {
            this.key = key;
            this.satellite = satellite;
        }
    }

    private static class OSNode {
        Data data; // The data value in the node
        OSNode leftNode; // left child
        OSNode rightNode; // right child
        OSNode parentNode; // parent
        int color; // color
        int size; // the number of (internal) nodes in the subtree rooted at x (including x itself)

        // Constructors
        OSNode(Data data) {
            this(data, null, null, null);
        }

        OSNode(Data data, OSNode leftNode, OSNode rightNode, OSNode parentNode) {
            this.data = data;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.parentNode = parentNode;
            this.color = OrderStatisticTree.BLACK;
        }
    }

    private OSNode root;
    private OSNode nullNode;

    /**
     * Construct the tree.
     */
    public OrderStatisticTree() {
        nullNode = new OSNode(null);
        nullNode.leftNode = nullNode.rightNode = nullNode;
        root = nullNode;
        root.parentNode = nullNode;
    }

    /**
     * Find the node with the ith smallest key in an order-statistic tree T.
     */
    public OSNode osSelect(int n) {
        return osSelect(this.root, n);
    }

    public OSNode osSelect(OSNode n, int i) {
        int r = n.leftNode.size + 1; // rank
        if (i == r) {
            return n;
        } else if (i < r) {
            return osSelect(n.leftNode, i);
        } else {
            return osSelect(n.rightNode, i - r);
        }
    }

    /**
     * Determine the rank of an element.
     */
    public int osRank(OSNode n) {
        int r = n.leftNode.size + 1;
        OSNode m = n;
        while (m != this.root) {
            if (m == m.parentNode.rightNode) {
                r += m.parentNode.leftNode.size + 1;
            }
            m = m.parentNode;
        }
        return r;
    }

    public OSNode rbTreeSearch(String s) {
        return rbTreeSearch(this.root, s);
    }

    private OSNode rbTreeSearch(OSNode n, String s) {
        if (n == nullNode || s.equals(n.data.key)) {
            return n;
        }
        if (s.compareTo(n.data.key) < 0) {
            return rbTreeSearch(n.leftNode, s);
        } else {
            return rbTreeSearch(n.rightNode, s);
        }
    }

    private OSNode rbTreeMinimum(OSNode n) {
        while (n.leftNode != nullNode) {
            n = n.leftNode;
        }
        return n;
    }

    /**
     * Print the tree contents by inorder traversal
     */
    public void osPrintTree() {
        osPrintTree(root, 0);
    }

    /**
     * Internal method to print a subtree by inorder traversal
     *
     * @param n     the node that roots the subtree
     * @param level tree height
     */
    private void osPrintTree(OSNode n, int level) {
        if (n != nullNode) {
            // left leaf node
            if (n.leftNode == this.nullNode) {
                System.out.println(n.leftNode.data + "   \tLevel: " + (level + 1) + " Parent: " + n.data.key +
                        "\t\tColor:" + BLACK);
            } else {
                osPrintTree(n.leftNode, (level + 1));
            }

            if (n != this.root) {
                System.out.println(n.data.key + "   \tLevel: " + level + " Parent: " + n.parentNode.data.key + "\t\tColor:" +
                        " " + n.color);
            } else {
                System.out.println(n.data.key + "   \tLevel: " + level + " Parent: null" + "\t\tColor: " + n.color);
            }

            // right leaf node
            if (n.rightNode == this.nullNode) {
                System.out.println(n.rightNode.data + "   \tLevel: " + (level + 1) + " Parent: " + n.data.key + "\t\tColor:"
                        + BLACK);
            } else {
                osPrintTree(n.rightNode, (level + 1));
            }
        }
    }

    /**
     * Preorder tree walk
     */
    public void osPreorderTreeWalk() {
        osPreorderTreeWalk(this.root, 0);
    }

    private void osPreorderTreeWalk(OSNode n, int level) {
        if (n != nullNode) {

            if (n != this.root) {
                System.out.println(n.data.key + "   \tLevel: " + level + " Parent: " + n.parentNode.data.key + "\t\tColor:" +
                        " " + n.color);
            } else {
                System.out.println(n.data.key + "   \tLevel: " + level + " Parent: null" + "\t\tColor: " + n.color);
            }

            // left leaf node
            if (n.leftNode == this.nullNode) {
                System.out.println(n.leftNode.data + "   \tLevel: " + (level + 1) + " Parent: " + n.data.key +
                        "\t\tColor: " + BLACK);
            } else {
                osPreorderTreeWalk(n.leftNode, (level + 1));
            }
            // right leaf node
            if (n.rightNode == this.nullNode) {
                System.out.println(n.rightNode.data + "   \tLevel: " + (level + 1) + " Parent: " + n.data.key +
                        "\t\tColor:" + BLACK);
            } else {
                osPreorderTreeWalk(n.rightNode, (level + 1));
            }
        }
    }

    /**
     * Rotate binary tree node with right child. (Left rotate)
     *
     * @param x old root
     */
    private void osRotateWithRightChild(OSNode x) {
//        System.out.println(x.key);
        OSNode y = x.rightNode; // new root
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

        y.size = x.size; // Maintaining subtree sizes
        x.size = x.rightNode.size + x.leftNode.size + 1;
    }

    /**
     * Rotate binary tree node with left child. (Right rotate)
     */
    private void osRotateWithLeftChild(OSNode x) {
        OSNode y = x.leftNode;
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

        y.size = x.size; // Maintaining subtree sizes
        x.size = x.rightNode.size + x.leftNode.size + 1;
    }

    public void osInsert(Data d) {
        OSNode z = new OSNode(d);
        OSNode y = this.nullNode;
        OSNode x = this.root;
        while (x != this.nullNode) {
            y = x;
            if ((z.data.key).compareTo(x.data.key) <= 0) {
                x.size += 1;
                x = x.leftNode;
            } else if ((z.data.key).compareTo(x.data.key) > 0) {
                x.size += 1;
                x = x.rightNode;
            }
        }
        z.parentNode = y;
        if (y == this.nullNode) {
            this.root = z;
        } else if ((z.data.key).compareTo(y.data.key) < 0) {
            y.leftNode = z;
        } else {
            y.rightNode = z;
        }
        z.leftNode = this.nullNode;
        z.rightNode = this.nullNode;
        z.color = RED;
        z.size = 1;
        osInsertFixup(z);
    }

    private void osInsertFixup(OSNode z) {
        while (z.parentNode.color == RED) {
            if (z.parentNode == z.parentNode.parentNode.leftNode) {
                OSNode y = z.parentNode.parentNode.rightNode;
                if (y.color == RED) {
                    z.parentNode.color = BLACK; // case 1
                    y.color = BLACK;
                    z.parentNode.parentNode.color = RED;
                    z = z.parentNode.parentNode;
                } else {
                    if (z == z.parentNode.rightNode) {
                        z = z.parentNode; // case 2
                        osRotateWithRightChild(z);
                    }
                    z.parentNode.color = BLACK; // case 3
                    z.parentNode.parentNode.color = RED;
                    osRotateWithLeftChild(z.parentNode.parentNode);
                }
            } else {
                OSNode y = z.parentNode.parentNode.leftNode;
                if (y.color == RED) {
                    z.parentNode.color = BLACK; // case 1
                    y.color = BLACK;
                    z.parentNode.parentNode.color = RED;
                    z = z.parentNode.parentNode;
                } else if (z == z.parentNode.leftNode) {
                    z = z.parentNode; // case 2
                    osRotateWithLeftChild(z);
                } else {
                    z.parentNode.color = BLACK; // case 3
                    z.parentNode.parentNode.color = RED;
                    osRotateWithRightChild(z.parentNode.parentNode);
                }
            }
        }
        this.root.color = BLACK;
    }

    private void osTransplant(OSNode u, OSNode v) {
        if (u.parentNode == this.nullNode) {
            this.root = v;
        } else if (u == u.parentNode.leftNode) {
            u.parentNode.leftNode = v;
        } else {
            u.parentNode.rightNode = v;
        }
        v.parentNode = u.parentNode;
    }

    public void osDelete(String s) {
        OSNode z = this.rbTreeSearch(s);
        if (z == this.nullNode) {
            System.out.println("Deletion failed: No such data");
            return;
        }
        OSNode y = z;
        OSNode x;
        int yOriginalColor = y.color;
        if (z.leftNode == this.nullNode) {
            x = z.rightNode;
            osTransplant(z, z.rightNode);
        } else if (z.rightNode == this.nullNode) {
            x = z.leftNode;
            osTransplant(z, z.leftNode);
        } else {
            y = rbTreeMinimum(z.rightNode);
            yOriginalColor = y.color;
            x = y.rightNode;
            if (y.parentNode == z) {
                x.parentNode = y;
            } else {
                osTransplant(y, y.rightNode);
                y.rightNode = z.rightNode;
                y.rightNode.parentNode = y;
            }
            osTransplant(z, y);
            y.leftNode = z.leftNode;
            y.leftNode.parentNode = y;
            y.color = z.color;
        }

        OSNode m = x; // Maintaining subtree sizes
        while (m != this.root) {
            m = m.parentNode;
            m.size -= 1;
        }

        if (yOriginalColor == BLACK) {
            osDeleteFixup(x);
        }
    }

    private void osDeleteFixup(OSNode x) {
        while (x != this.root && x.color == BLACK) {
            if (x == x.parentNode.leftNode) {
                OSNode w = x.parentNode.rightNode;
                if (w.color == RED) {
                    w.color = BLACK; // case 1
                    x.parentNode.color = RED;
                    osRotateWithRightChild(x.parentNode);
                    w = x.parentNode.rightNode;
                }
                if (w.leftNode.color == BLACK && w.rightNode.color == BLACK) {
                    w.color = RED; // case 2
                    x = x.parentNode;
                } else {
                    if (w.rightNode.color == BLACK) {
                        w.leftNode.color = BLACK; // case 3
                        w.color = RED;
                        osRotateWithLeftChild(w);
                        w = x.parentNode.rightNode;
                    }
                    w.color = x.parentNode.color; // case 4
                    x.parentNode.color = BLACK;
                    w.rightNode.color = BLACK;
                    osRotateWithRightChild(x.parentNode);
                    x = this.root;
                }
            } else {
                OSNode w = x.parentNode.leftNode;
                if (w.color == RED) {
                    w.color = BLACK; // case 1
                    x.parentNode.color = RED;
                    osRotateWithLeftChild(x.parentNode);
                    w = x.parentNode.leftNode;
                }
                if (w.leftNode.color == BLACK && w.rightNode.color == BLACK) {
                    w.color = RED; // case 2
                    x = x.parentNode;
                } else {
                    if (w.leftNode.color == BLACK) {
                        w.rightNode.color = BLACK; // case 3
                        w.color = RED;
                        osRotateWithRightChild(w);
                        w = x.parentNode.leftNode;
                    }
                    w.color = x.parentNode.color; // case 4
                    x.parentNode.color = BLACK;
                    w.leftNode.color = BLACK;
                    osRotateWithLeftChild(x.parentNode);
                    x = this.root;
                }
            }
        }
        x.color = BLACK;
    }

    public static void main(String[] args) {
        OrderStatisticTree osTree = new OrderStatisticTree();

        // Test insert
        osTree.osInsert(new Data("j"));
        osTree.osInsert(new Data("b"));
        osTree.osInsert(new Data("l"));
        osTree.osInsert(new Data("d", "ddd"));
        osTree.osInsert(new Data("f"));
        osTree.osInsert(new Data("h"));
        osTree.osInsert(new Data("a"));
        osTree.osInsert(new Data("i"));
        osTree.osInsert(new Data("g"));
        osTree.osInsert(new Data("c"));
        osTree.osInsert(new Data("k"));
        osTree.osInsert(new Data("e"));
        osTree.osInsert(new Data("e")); // duplicated insertion

        System.out.println("Inorder tree walk: ");
        osTree.osPrintTree();
        System.out.println("\nPreorder tree walk:");
        osTree.osPreorderTreeWalk();

        // Test rank
        System.out.println();
        System.out.println("The 5th smallest key: " + osTree.osSelect(5).data.key); // e
        System.out.println("The root's rank: " + osTree.osRank(osTree.root)); // 9

        // Test search
        System.out.print("\nSearch the node d's satellite data: ");
        OSNode node = osTree.rbTreeSearch("d");
        if (node.data != null) {
            System.out.println(node.data.satellite + "\n");
        } else {
            System.out.println("No result!\n");
        }

        // Test delete
        osTree.osDelete("c");
        osTree.osDelete("b");
        osTree.osDelete("i");
        osTree.osDelete("d");
        osTree.osDelete("j");
        osTree.osDelete("k");
        osTree.osDelete("h");
        osTree.osDelete("h"); // duplicated deletion

        System.out.println("\nAfter deletion: ");
        osTree.osPrintTree();

        // Test rank
        System.out.println();
        System.out.println("The 3th smallest key: " + osTree.osSelect(3).data.key); // e
        System.out.println("The root's rank: " + osTree.osRank(osTree.root)); // 4
    }
}
