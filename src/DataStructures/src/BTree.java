import java.util.LinkedList;

/**
 * Created by Ethan on 15/11/1.
 */
public class BTree {

    /**
     * Class skeleton and initialization routines
     */
    private static int level = 0; // tree height
    private static int t; // Minimum degree
    private BNode nullNode;
    private BNode root;

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

    private static class BNode {
        int n; // key value numbers
        LinkedList<Data> data; // key values and satellite values
        LinkedList<BNode> children; // pointer to children
        boolean isLeaf; // true if the node is leaf node

        // Constructors
        BNode() {
            this.data = new LinkedList<Data>();
            this.children = new LinkedList<BNode>();
        }

        BNode(String s) {
            this.data = new LinkedList<Data>();
            this.children = new LinkedList<BNode>();
        }
    }

    /**
     * Construct the tree.
     */
    public BTree() {
        t = 10; // Minimum degree
        BNode x = allocateNode();
        x.isLeaf = true;
        x.n = 0;
        diskWrite(x);
        this.root = x;
    }

    public BTree(int minDegree) {
        t = minDegree; // Minimum degree
        BNode x = allocateNode();
        x.isLeaf = true;
        x.n = 0;
        diskWrite(x);
        this.root = x;
    }

    /**
     * Disk operations: read, write, allocate
     */
    private void diskRead(BNode node) {

    }

    private void diskWrite(BNode node) {

    }


    /**
     * Allocate a disk page for the new node
     */
    private BNode allocateNode() {
        BNode node = new BNode();
        return node;
    }

    /**
     * @return the node where predecessor lies in
     */
    private BNode predecessorNode(BNode node, int i) {
        node = node.children.get(i);
        while (!node.isLeaf) {
            node = node.children.getLast();
        }
        return node;
    }

    /**
     * @return the node where successor lies in
     */
    private BNode successorNode(BNode node, int i) {
        node = node.children.get(i + 1);
        while (!node.isLeaf) {
            node = node.children.getFirst();
        }
        return node;
    }

    /**
     * Search the satellite value corresponding to the key value
     *
     * @param s the key value
     * @return data object
     */
    public Data bTreeSearch(String s) {
        return bTreeSearch(this.root, s);
    }

    private Data bTreeSearch(BNode node, String s) {
        int i = 0;
        while (i < node.n && s.compareTo(node.data.get(i).key) > 0) {
            i += 1;
        }
        if (i < node.n && s.equals(node.data.get(i).key)) {
            return node.data.get(i);
        } else if (node.isLeaf) {
            return null;
        } else {
            diskRead(node.children.get(i));
            return bTreeSearch(node.children.get(i), s);
        }
    }

    /**
     * Print the tree contents by preorder tree walk
     */
    public void bTreePrintTree() {
        bTreePrintTree(root, 0, 0);
    }

    /**
     * Internal method to print a subtree by preorder tree walk
     *
     * @param node the node that roots the subtree
     */
    private void bTreePrintTree(BNode node, int level, int nthChild) {
        System.out.print("Level " + level + " Nth-child: " + nthChild + " Key: |");
        for (int i = 0; i < node.n; i++) {
            System.out.print(node.data.get(i).key + "|");
        }
        System.out.println("");
        if (!node.isLeaf) {
            for (int i = 0; i < (node.n + 1); i++) {
                bTreePrintTree(node.children.get(i), (level + 1), i);
            }
        }
    }

    private void bTreeSplitChild(BNode x, int i) {
        BNode z = allocateNode();
        BNode y = x.children.get(i); // y --> y+z
        // Set z's properties
        z.isLeaf = y.isLeaf;
        z.n = t - 1;
        for (int j = 0; j < t - 1; j++) {
            z.data.add(y.data.get(t + j));
        }
        if (!y.isLeaf) {
            for (int j = 0; j < t; j++) {
                z.children.add(y.children.get(t + j));
            }
        }
        // Set x's properties
        x.children.add(i + 1, z);
        x.data.add(i, y.data.get(t - 1));
        x.n += 1;
        // Set y's properties
        y.n = t - 1;
        for (int j = 0; j < t; j++) {
            y.data.removeLast();
            if (!y.isLeaf) {
                y.children.removeLast();
            }
        }
        diskWrite(y);
        diskWrite(z);
        diskWrite(x);
    }

    public void bTreeInsert(Data data) {
        BNode r = this.root;
        if (r.n == 2 * t - 1) {
            BNode s = allocateNode();
            this.root = s;
            s.isLeaf = false;
            s.n = 0;
            s.children.add(r);
            bTreeSplitChild(s, 0);
            bTreeInsertNonfull(s, data);
        } else {
            bTreeInsertNonfull(r, data);
        }
    }

    private void bTreeInsertNonfull(BNode x, Data data) {
        int i = x.n - 1;
        if (x.isLeaf) {
            while (i >= 0 && (data.key).compareTo(x.data.get(i).key) < 0) {
                i--;
            }
            x.data.add(i + 1, data);
            x.n += 1;
            diskWrite(x);
        } else {
            while (i >= 0 && (data.key).compareTo(x.data.get(i).key) < 0) {
                i--;
            }
            i++;
            diskRead(x.children.get(i));
            if (x.children.get(i).n == (2 * t - 1)) {
                bTreeSplitChild(x, i);
                if ((data.key).compareTo(x.data.get(i).key) > 0) {
                    i++;
                }
            }
            bTreeInsertNonfull(x.children.get(i), data);
        }
    }

    public void bTreeDelete(String s) {
        bTreeDelete(this.root, s);
    }

    public void bTreeDelete(BNode node, String s) {
        int i = 0;
        while (i < node.n && s.compareTo(node.data.get(i).key) > 0) {
            i += 1;
        }
        if (i < node.n && s.equals(node.data.get(i).key)) { // Find the key to delete
            if (node.isLeaf) { // case 1: node is leaf node
                node.data.remove(i);
                node.n -= 1;
            } else { // case 2: node is internal node
                if (node.children.get(i).n >= t) {
                    Data predecessorData = predecessorNode(node, i).data.getLast(); // Predecessor's data
                    bTreeDelete(predecessorNode(node, i), predecessorData.key);
                    node.data.set(i, predecessorData);
                } else if (node.children.get(i + 1).n >= t) {
                    Data successorData = successorNode(node, i).data.getFirst(); // Successor's data
                    bTreeDelete(successorNode(node, i), successorData.key);
                    node.data.set(i, successorData);
                } else { // children[i] and children[i+1] both has t-1 data
                    node.children.get(i).data.add(node.data.get(i));
                    node.children.get(i).n += 1;
                    for (int j = 0; j < t - 1; j++) {
                        node.children.get(i).data.add(node.children.get(i + 1).data.getFirst());
                        if (!node.children.get(i).isLeaf) {
                            node.children.get(i).children.add(node.children.get(i + 1).children.getFirst());
                        }
                        node.children.get(i).n += 1;
                        node.children.get(i + 1).data.removeFirst();
                        if (!node.children.get(i + 1).isLeaf) {
                            node.children.get(i + 1).children.removeFirst();
                        }
                        node.children.get(i + 1).n -= 1;
                    }
                    node.data.remove(i);
                    node.children.remove(i + 1);
                    node.n-=1;
                    bTreeDelete(node.children.get(i),s);
                }
            }
        } else if (node.isLeaf) { // delete fail
            System.out.println("Delete error: No such value!");
        } else { // case 3: if node's children[i] has only t-1 key values
            if (node.children.get(i).n == t - 1) {
                if (i >= 1 && node.children.get(i - 1).n >= t) { // node's children[i] has a brother of more than t-1 values
                    node.children.get(i).data.add(0, node.data.get(i - 1));
                    if (!node.children.get(i).isLeaf) {
                        node.children.get(i).children.add(0, node.children.get(i - 1).children.getLast());
                    }
                    node.children.get(i).n += 1;
                    node.data.set(i - 1, node.children.get(i - 1).data.getLast());
                    node.children.get(i - 1).data.removeLast();
                    if (!node.children.get(i - 1).isLeaf) {
                        node.children.get(i - 1).children.removeLast();
                    }
                    node.children.get(i - 1).n -= 1;
                } else if (i < node.n && node.children.get(i + 1).n >= t) {
                    node.children.get(i).data.addLast(node.data.get(i));
                    if (!node.children.get(i + 1).isLeaf) {
                        node.children.get(i).children.addLast(node.children.get(i + 1).children.getFirst());
                    }
                    node.children.get(i).n += 1;
                    node.data.set(i, node.children.get(i + 1).data.getFirst());
                    node.children.get(i + 1).data.removeFirst();
                    if (!node.children.get(i + 1).isLeaf) {
                        node.children.get(i + 1).children.removeFirst();
                    }
                    node.children.get(i + 1).n -= 1;
                } else { // both brother of node's children[i] has only t-1 values
                    if (i >= 1) { // combine children with left brother
                        node.children.get(i - 1).data.addLast(node.data.get(i - 1));
                        node.children.get(i-1).n+=1;
                        for (int j = 0; j < node.children.get(i).n; j++) {
                            node.children.get(i - 1).data.addLast(node.children.get(i).data.getFirst());
                            if (!node.children.get(i - 1).isLeaf) {
                                node.children.get(i - 1).children.addLast(node.children.get(i).children.getFirst());
                            }
                            node.children.get(i - 1).n += 1;
                            node.children.get(i).data.removeFirst();
                            if (!node.children.get(i).isLeaf) {
                                node.children.get(i).children.removeFirst();
                            }
                            node.children.get(i).n -= 1;
                        }
                        if (!node.children.get(i - 1).isLeaf) {
                            node.children.get(i - 1).children.addLast(node.children.get(i).children.getFirst());
                        }
                        node.data.remove(i - 1);
                        node.children.remove(i);
                        node.n -= 1;
                        i-=1;
                    } else { // combine with children with right brother
                        node.children.get(i).data.addLast(node.data.get(i));
                        for (int j = 0; j < node.children.get(i + 1).n; j++) {
                            node.children.get(i).data.addLast(node.children.get(i + 1).data.getFirst());
                            if (!node.children.get(i).isLeaf) {
                                node.children.get(i).children.addLast(node.children.get(i + 1).children.getFirst());
                            }
                            node.children.get(i).n += 1;
                            node.children.get(i + 1).data.removeFirst();
                            if (!node.children.get(i + 1).isLeaf) {
                                node.children.get(i + 1).children.removeFirst();
                            }
                            node.children.get(i + 1).n -= 1;
                        }
                        node.data.remove(i - 1);
                        node.children.remove(i);
                        node.n -= 1;
                    }
                    if (this.root == node && node.n == 0) {
//                        node = nullNode;
                        this.root = node.children.get(0);
                    }
                }
            }
            bTreeDelete(node.children.get(i), s);
        }
    }

    public static void main(String[] args) {
        BTree bTree = new BTree(2);

        Data data1 = new Data("f", "fff");
        Data data2 = new Data("s", "sss");
        Data data3 = new Data("q", "qqq");
        Data data4 = new Data("k", "kkk");
        Data data5 = new Data("c", "ccc");
        Data data6 = new Data("l", "lll");
        Data data7 = new Data("h", "hhh");
        Data data8 = new Data("t", "ttt");
        Data data9 = new Data("v", "vvv");
        Data data10 = new Data("w", "www");
        Data data11 = new Data("r", "rrr");
        Data data12 = new Data("n", "nnn");
        Data data13 = new Data("p", "ppp");
        Data data14 = new Data("a", "aaa");
        Data data15 = new Data("b", "bbb");
        Data data16 = new Data("x", "xxx");
        Data data17 = new Data("y", "yyy");
        Data data18 = new Data("d", "ddd");
        Data data19 = new Data("z", "zzz");
        Data data20 = new Data("e", "eee");

        bTree.bTreeInsert(data1);
        bTree.bTreeInsert(data2);
        bTree.bTreeInsert(data3);
        bTree.bTreeInsert(data4);
        bTree.bTreeInsert(data5);
        bTree.bTreeInsert(data6);
        bTree.bTreeInsert(data7);
        bTree.bTreeInsert(data8);
        bTree.bTreeInsert(data9);
        bTree.bTreeInsert(data10);
        bTree.bTreeInsert(data11);
        bTree.bTreeInsert(data12);
        bTree.bTreeInsert(data13);
        bTree.bTreeInsert(data14);
        bTree.bTreeInsert(data15);
        bTree.bTreeInsert(data16);
        bTree.bTreeInsert(data17);
        bTree.bTreeInsert(data18);
        bTree.bTreeInsert(data19);
        bTree.bTreeInsert(data20);

        Data searchData = bTree.bTreeSearch("v");
        if (searchData != null) {
            System.out.println(searchData.satellite);
        }

        bTree.bTreePrintTree();
        System.out.println("");

        bTree.bTreeDelete("y");
        bTree.bTreeDelete("w");
        bTree.bTreeDelete("q");
        bTree.bTreeDelete("x");
        bTree.bTreeDelete("k");
        bTree.bTreeDelete("b");
        bTree.bTreeDelete("h");
        bTree.bTreeDelete("p");
        bTree.bTreePrintTree();
    }
}
