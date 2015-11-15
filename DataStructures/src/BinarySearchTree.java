/**
 * Created by Ethan on 15/11/15.
 */
public class BinarySearchTree {

    /**
     * Class skeleton and initialization routines
     */
    private static class BSTNode {
        String key; // The key value of the node
        String satellite; // The satellite data of the node
        BSTNode leftNode; // left child
        BSTNode rightNode; // right child
        BSTNode parentNode; // parent

        // Constructors
        BSTNode(String key) {
            this(key, "", null, null, null);
        }

        BSTNode(String key, String satellite, BSTNode leftNode, BSTNode rightNode, BSTNode parentNode) {
            this.key = key;
            this.satellite = satellite;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.parentNode = parentNode;
        }
    }

    private BSTNode root;

    /**
     * Construct the tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Print the tree contents by inorder traversal (lexicographical order)
     */
    public void bstPrintTree() {
        bstPrintTree(root);
    }

    /**
     * Internal method to print a subtree by inorder traversal
     *
     * @param n the node that roots the subtree
     */
    private void bstPrintTree(BSTNode n) {
        if (n != null) {
            // left leaf node
            bstPrintTree(n.leftNode);

            System.out.print(n.key + " ");

            // right leaf node
            bstPrintTree(n.rightNode);
        }
    }

    public BSTNode bstTreeSearch(String s) {
        return bstTreeSearch(this.root, s);
    }

    private BSTNode bstTreeSearch(BSTNode n, String s) {
        if (n == null || s.equals(n.key)) {
            return n;
        }
        if (s.compareTo(n.key) < 0) {
            return bstTreeSearch(n.leftNode, s);
        } else {
            return bstTreeSearch(n.rightNode, s);
        }
    }

    private BSTNode iterativeBstTreeSearch(String s) {
        return iterativeBstTreeSearch(this.root, s);
    }

    private BSTNode iterativeBstTreeSearch(BSTNode n, String s) {
        while (n != null && !s.equals(n.key)) {
            if (s.compareTo(n.key) < 0) {
                n = n.leftNode;
            } else {
                n = n.rightNode;
            }
        }
        return n;
    }

    private BSTNode bstMinimum(BSTNode n) {
        while (n.leftNode != null) {
            n = n.leftNode;
        }
        return n;
    }

    private BSTNode bstMaximum(BSTNode n) {
        while (n.rightNode != null) {
            n = n.rightNode;
        }
        return n;
    }

    private BSTNode bstSuccessor(BSTNode n) {
        if (n.rightNode != null) {
            return bstMinimum(n.rightNode);
        }
        BSTNode m = n.parentNode;
        while (m != null && n == m.rightNode) {
            n = m;
            m = m.parentNode;
        }
        return m;
    }

    public void bstInsert(String s) {
        BSTNode z = new BSTNode(s);
        BSTNode y = null;
        BSTNode x = this.root;
        while (x != null) {
            y = x;
            if ((z.key).compareTo(x.key) < 0) {
                x = x.leftNode;
            } else if ((z.key).compareTo(x.key) > 0) {
                x = x.rightNode;
            } else { // (z.key).compareTo(x.key) = 0
                System.out.println("Insertion failed: duplicated data");
                return;
            }
        }
        z.parentNode = y;
        if (y == null) {
            this.root = z;
        } else if ((z.key).compareTo(y.key) < 0) {
            y.leftNode = z;
        } else {
            y.rightNode = z;
        }
        z.leftNode = null;
        z.rightNode = null;
    }

    private void bstTransplant(BSTNode u, BSTNode v) {
        if (u.parentNode == null) {
            this.root = v;
        } else if (u == u.parentNode.leftNode) {
            u.parentNode.leftNode = v;
        } else {
            u.parentNode.rightNode = v;
        }
        if (v != null) {
            v.parentNode = u.parentNode;
        }
    }

    public void bstDelete(String s) {
        BSTNode z = this.bstTreeSearch(s);
        if (z == null) {
            System.out.println("Deletion failed: No such data");
            return;
        }
        if (z.leftNode == null) {
            bstTransplant(z, z.rightNode);
        } else if (z.rightNode == null) {
            bstTransplant(z, z.leftNode);
        } else {
            BSTNode y = bstMinimum(z.rightNode);
            if (y.parentNode != z) {
                bstTransplant(y, y.rightNode);
                y.rightNode = z.rightNode;
                y.rightNode.parentNode = y;
            }
            bstTransplant(z, y);
            y.leftNode = z.leftNode;
            y.leftNode.parentNode = y;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.bstInsert("6");
        bst.bstInsert("3");
        bst.bstInsert("7");
        bst.bstInsert("10");
        bst.bstInsert("1");
        bst.bstInsert("17");
        bst.bstInsert("12");
        bst.bstInsert("8");
        bst.bstInsert("5");
        bst.bstInsert("2");
        bst.bstInsert("14");
        bst.bstInsert("9");

        bst.bstPrintTree();
        System.out.println();
        System.out.println(bst.bstTreeSearch("2").key);
        System.out.println(bst.iterativeBstTreeSearch("2").key);
        System.out.println(bst.bstMaximum(bst.root).key);
        System.out.println(bst.bstMinimum(bst.root).key);
        System.out.println(bst.bstSuccessor(bst.root).key);

        bst.bstDelete("10");
        bst.bstDelete("6");
        bst.bstDelete("7");
        bst.bstDelete("12");

        bst.bstPrintTree();
    }
}