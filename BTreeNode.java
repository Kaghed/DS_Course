public class BTreeNode {

    int t;
    int [] keys ;
    BTreeNode [] childs;
    int n;
    boolean leaf;

    public BTreeNode(int t , boolean leaf){
        this.keys = new int[2*t -1];
        this.t = t;
        this.childs = new BTreeNode[2*t];
        this.n = 0;
        this.leaf =leaf;
    }

    BTreeNode search(int k) {
        int i = 0;
        while (i < n && k > keys[i]) {
            i++;
        }
        if (i < n && k == keys[i]) {
            return this;
        }
        if (leaf) {
            return null;
        }
        return childs[i].search(k);
    }

    void traverse() {
        for (int i = 0; i < n; i++) {
            if (!leaf) {
                childs[i].traverse();
            }
            System.out.print(" " + keys[i]);
        }
        if (!leaf) {
            childs[n].traverse();
        }
    }

    void splitChild(int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(y.t, y.leaf);
        z.n = t - 1;
        for (int j = 0; j < t - 1; j++) {
            z.keys[j] = y.keys[j + t];
        }
        if (!y.leaf) {
            for (int j = 0; j < t; j++) {
                z.childs[j] = y.childs[j + t];
            }
        }
        y.n = t - 1;
        for (int j = n; j > i; j--) {
            childs[j + 1] = childs[j];
        }
        
        childs[i + 1] = z;
        for (int j = n - 1; j >= i; j--) {
            keys[j + 1] = keys[j];
        }
        keys[i] = y.keys[t - 1];
        n++;
    }

    void insertNonFull(int k) {
        int i = n - 1;
        if (leaf) {
            while (i >= 0 && keys[i] > k) {
                keys[i + 1] = keys[i];
                i--;
            }
            keys[i + 1] = k;
            n++;
        } else {
            while (i >= 0 && keys[i] > k) {
                i--;
            }
            if (childs[i + 1].n == 2 * t - 1) {
                splitChild(i + 1, childs[i + 1]);
                if (keys[i + 1] < k) {
                    i++;
                }
            }
            childs[i + 1].insertNonFull(k);
        }
    }



    
}

