public class MinHeap {

    private int[] heap;
    private int capacity;
    private int currentSize;

    public MinHeap(int n) {
        capacity = n;
        heap = new int[n];
        currentSize = 0;
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    private int getParent(int key) {
        return (key - 1) / 2;
    }

    private int leftChild(int key) {
        return key * 2 + 1;
    }

    private int rightChild(int key) {
        return key * 2 + 2;
    }

    public int getMin(){
        return heap[0];
    }

    public boolean insertKey(int value){
        if(currentSize == capacity)
        return false;

        int i = currentSize;
        heap[i] = value;
        currentSize++;

        while(i!=0 && heap[i] < heap[getParent(i)]){
            swap(heap, i, getParent(i));
            i=getParent(i);
        }
        return true;

    }

    public void decreaseKey(int key , int value){
        heap[key] = value;

        while(key!=0 && heap[key] < heap[getParent(key)]){
            swap(heap, key, getParent(key));
            key = getParent(key);
        }
    }

    public int extractMin(){
        if(currentSize<=0)
        return Integer.MAX_VALUE;

        if(currentSize==1){
            currentSize--;
            return heap[0];
        }

        int root = heap[0];

        heap[0] = heap[--currentSize];
        MinHeapify(0);
        return root;
    }

    public void deleteKey(int key) {
        decreaseKey(key, Integer.MIN_VALUE);
        extractMin();
    }

    private void MinHeapify(int key){
        int l = leftChild(key);
        int r = rightChild(key);

        int smallest = key;

        if(l < currentSize && heap[l] < heap[smallest])
        smallest = l;
    
        if(r < currentSize && heap[r] < heap[smallest])
        smallest = r;

        if(smallest!=key){
            swap(heap, key, smallest);
            MinHeapify(key);
        }

    
    }

    public void increaseKey(int key, int new_val) {
        heap[key] = new_val;
        MinHeapify(key);
    }
    

}
