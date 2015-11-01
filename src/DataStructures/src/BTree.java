import java.util.LinkedList;

/**
 * Created by Ethan on 15/11/1.
 */
public class BTree {

    /**
     * Class skeleton and initialization routines
     */
    private static int level = 0; // tree height
    private int t; // Minimum degree
    private BNode nullNode;
    private BNode root;

    public static class Data {
        String key;
        String satellite;

        Data(String key, String satellite){
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
            this.n = 10;
            this.data = new LinkedList<Data>();
            this.children = new LinkedList<BNode>();
        }
        BNode(int n) {
            this.n = n;
            this.data = new LinkedList<Data>();
            this.children = new LinkedList<BNode>();
        }
    }

    /**
     * Construct the tree.
     */
    public BTree() {
        this.t = 10; // Minimum degree
        BNode x = allocateNode();
        x.isLeaf = true;
        x.n = 0;
        diskWrite(x);
        this.root = x;
    }
    public BTree(int t) {
        this.t = t; // Minimum degree
        BNode x = allocateNode();
        x.isLeaf = true;
        x.n = 0;
        diskWrite(x);
        this.root = x;
    }

    /**
     * Disk operations: read, write, allocate
     */
    private void diskRead(BNode node){

    }

    private void diskWrite(BNode node){

    }


    /**
     * Allocate a disk page for the new node
     */
    private BNode allocateNode(){
        BNode node = new BNode();
        return node;
    }

    /**
     * Search the satellite value corresponding to the key value
     * @param s the key value
     * @return data object
     */
    public Data bTreeSearch(String s){
        return bTreeSearch(this.root, s);
    }

    private Data bTreeSearch(BNode node, String s) {
        int i = 1;
        while(i <= node.n && s.compareTo(node.data.get(i).key)>0){
            i+=1;
        }
        if(i<=node.n && s.equals(node.data.get(i).key)){
            return node.data.get(i);
        }else if (node.isLeaf){
            return null;
        }else {
            diskRead(node.children.get(i));
            return bTreeSearch(node.children.get(i), s);
        }
    }

    /**
     * Print the tree contents by preorder tree walk
     */
    public void bTreePrintTree() {
        bTreePrintTree(root, 0);
    }

    /**
     * Internal method to print a subtree by preorder tree walk
     *
     * @param n the node that roots the subtree
     */
    public void bTreePrintTree(BNode n, int level) {
        System.out.println();
    }

    private void bTreeSplitChild(BNode x, int i){
        BNode z = allocateNode();
        BNode y = x.children.get(i);
        // Set z's properties
        z.isLeaf = y.isLeaf;
        z.n = this.t-1;
        for (int j = 0; j < this.t - 1; j++) {
            z.data.set(j, y.data.get(this.t+j));
        }
        if(!y.isLeaf){
            for (int j = 0; j < this.t; j++) {
                z.children.set(j, y.children.get(this.t+j));
            }
        }
        // Set y's properties
        y.n = this.t-1;
        // Set x's properties
        for (int j = x.n; j >= i; j--) {
            x.children.set(j+1, x.children.get(j));
        }
        x.children.set(i, z);
        for (int j = x.n - 1; j >= i - 1; j--) {
            x.data.set(j+1, x.data.get(j));
        }
        x.data.set(i-1, y.data.get(this.t-1));
        x.n+=1;
        diskWrite(y);
        diskWrite(z);
        diskWrite(x);
    }

    public void bTreeInsert(Data data){
        BNode r = this.root;
        if (r.n == 2*this.t-1){
            BNode s = allocateNode();
            this.root = s;
            s.isLeaf = false;
            s.n = 0;
            s.children.set(0, r);
            bTreeSplitChild(s,0);
            bTreeInsertNonfull(s, data);
        }else{
            bTreeInsertNonfull(r, data);
        }
    }

    private void bTreeInsertNonfull(BNode x, Data data){
        int i = x.n - 1;
        if (x.isLeaf){
            while (i>=0 && (data.key).compareTo(x.data.get(i).key)<0){
                x.data.get(i+1).key = x.data.get(i).key;
                i--;
            }
            x.data.get(i+1).key = data.key;
            x.n+=1;
            diskWrite(x);
        }else{
            while (i>=0 && (data.key).compareTo(x.data.get(i).key)<0){
                i--;
            }
            i++;
            diskRead(x.children.get(i));
            if (x.children.get(i).n==(2*this.t-1)){
                bTreeSplitChild(x,i);
                if ((data.key).compareTo(x.data.get(i).key)>0){
                    i++;
                }
            }
            bTreeInsertNonfull(x.children.get(i), data);
        }
    }



}
