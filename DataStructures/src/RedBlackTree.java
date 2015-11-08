/**
 * Created by Ethan on 15/10/27.
 */
public class RedBlackTree {

    /**
     * Class skeleton and initialization routines
     */
    private static final int BLACK = 1;
    private static final int RED = 0;
    private static int level = 0; // tree height

    private static class RedBlackNode {
        String key; // The key value in the node
        String satellite; // The satellite value in the node
        RedBlackNode leftNode; // left child
        RedBlackNode rightNode; // right child
        RedBlackNode parentNode; // parent
        int color; // color

        // Constructors
        RedBlackNode(String key) {
            this(key, "", null, null, null);
        }

        RedBlackNode(String key, String satellite) {
            this(key, satellite, null, null, null);
        }

        RedBlackNode(String key, String satellite, RedBlackNode leftNode, RedBlackNode rightNode, RedBlackNode parentNode) {
            this.key = key;
            this.satellite = satellite;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.parentNode = parentNode;
            this.color = RedBlackTree.BLACK;
        }
    }

    private RedBlackNode root;
    private RedBlackNode nullNode;

    /**
     * Construct the tree.
     */
    public RedBlackTree() {
        nullNode = new RedBlackNode(null);
        nullNode.leftNode = nullNode.rightNode = nullNode;
        root = nullNode;
        root.parentNode = nullNode;
    }

    public RedBlackNode rbTreeSearch(String s){
        return rbTreeSearch(this.root, s);
    }

    private RedBlackNode rbTreeSearch(RedBlackNode n, String s) {
        if (n == nullNode || s.equals(n.key)) {
            return n;
        }
        if (s.compareTo(n.key) < 0) {
            return rbTreeSearch(n.leftNode, s);
        } else {
            return rbTreeSearch(n.rightNode, s);
        }
    }

    private RedBlackNode rbTreeMimimum(RedBlackNode n) {
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
     * @param n the node that roots the subtree
     * @param level tree height
     */
    private void rbPrintTree(RedBlackNode n, int level) {
        if (n != nullNode) {
            // left leaf node
            if (n.leftNode == this.nullNode) {
                System.out.println(n.leftNode.key + "\tLevel: " + (level + 1) + " Parent: " + n.key + "\t\tColor: " + BLACK);
            }else {
                rbPrintTree(n.leftNode, (level + 1));
            }
            System.out.println(n.key + "\tLevel: " + level + " Parent: " + n.parentNode.key + "\t\tColor: " + n.color);
            // right leaf node
            if (n.rightNode == this.nullNode) {
                System.out.println(n.rightNode.key + "\tLevel: " + (level + 1) + " Parent: " + n.key + "\t\tColor: " + BLACK);
            }else {
                rbPrintTree(n.rightNode, (level + 1));
            }
        }
    }

    /**
     * Preorder tree walk
     */
    public void rbPreorderTreeWalk(){
        rbPreorderTreeWalk(this.root, 0);
    }

    private void rbPreorderTreeWalk(RedBlackNode n, int level){
        if (n != nullNode) {
            System.out.println(n.key + "\tLevel: " + level + " Parent: " + n.parentNode.key + "\t\tColor: " + n.color);
            // left leaf node
            if (n.leftNode == this.nullNode) {
                System.out.println(n.leftNode.key + "\tLevel: " + (level + 1) + " Parent: " + n.key + "\t\tColor: " + BLACK);
            }else {
                rbPreorderTreeWalk(n.leftNode, (level + 1));
            }
            // right leaf node
            if (n.rightNode == this.nullNode) {
                System.out.println(n.rightNode.key + "\tLevel: " + (level + 1) + " Parent: " + n.key + "\t\tColor: " + BLACK);
            }else {
                rbPreorderTreeWalk(n.rightNode, (level + 1));
            }
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
    }

    public void rbInsert(String s) {
        RedBlackNode z = new RedBlackNode(s);
        RedBlackNode y = this.nullNode;
        RedBlackNode x = this.root;
        while (x != this.nullNode) {
            y = x;
            if ((z.key).compareTo(x.key) < 0) {
                x = x.leftNode;
            } else if ((z.key).compareTo(x.key) > 0){
                x = x.rightNode;
            }else{ // (z.data.key).compareTo(x.data.key) = 0
                System.out.println("Insertion failed: duplicated data");
                return;
            }
        }
        z.parentNode = y;
        if (y == this.nullNode) {
            this.root = z;
        } else if ((z.key).compareTo(y.key) < 0) {
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

    public void rbDelete(String s) {
        RedBlackNode z = this.rbTreeSearch(s);
        if (z==this.nullNode){
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
            y = rbTreeMimimum(z.rightNode);
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
        RedBlackTree rbTree = new RedBlackTree();

        // Test insert
        rbTree.rbInsert("j");
        rbTree.rbInsert("b");
        rbTree.rbInsert("l");
        rbTree.rbInsert("d");
        rbTree.rbInsert("f");
        rbTree.rbInsert("h");
        rbTree.rbInsert("a");
        rbTree.rbInsert("i");
        rbTree.rbInsert("g");
        rbTree.rbInsert("c");
        rbTree.rbInsert("k");
        rbTree.rbInsert("e");
        rbTree.rbInsert("e"); // duplicated insertion

        System.out.println("Inorder tree walk: ");
        rbTree.rbPrintTree();
        System.out.println("\nPreorder tree walk:");
        rbTree.rbPreorderTreeWalk();

        // Test search
        System.out.print("\nSearch the node d's key: ");
        RedBlackNode node = rbTree.rbTreeSearch("d");
        if (node.key!=null){
            System.out.println(node.key + "\n");
        }else {
            System.out.println("No result!\n");
        }

        // Test delete
        rbTree.rbDelete("c");
        rbTree.rbDelete("b");
        rbTree.rbDelete("i");
        rbTree.rbDelete("d");
        rbTree.rbDelete("j");
        rbTree.rbDelete("k");
        rbTree.rbDelete("h");
        rbTree.rbDelete("h"); // duplicated deletion

        System.out.println("\nAfter deletion");
        rbTree.rbPrintTree();
    }
}