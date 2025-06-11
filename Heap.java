public class Heap {

    private int[] heap = new int[10];
    private int size;

    public void insert(int value) {

        if (isFull())
            throw new IllegalStateException();

        heap[size++] = value;

        bubbleUp();
    }

    public void remove() {

        if (isEmpty()) {
            throw new IllegalStateException();
        }
        heap[0] = heap[--size];
        int index = 0;
        while (index <= size && !isValidParent(index)) {
            int largerChildIndex = largerChildIndex(index);
            swap(index, largerChildIndex);
            index = largerChildIndex;
        }

    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int largerChildIndex(int index) {
        
        if(!hasLeftChild(index))
        return index;
        
        if(!hasRightChild(index))
        return index;

        return (leftChild(index) > rightChild(index))
                ? leftChildIndex(index)
                : rightChildIndex(index);

    }



    private boolean isValidParent(int index) {
        if(!hasLeftChild(index))
        return true;
        if(!hasRightChild(index))
        return heap[index] >= leftChild(index);
        
        return heap[index] >= leftChild(index) &&
                heap[index] >= rightChild(index);
    }

    private int leftChild(int index) {
        return heap[leftChildIndex(index)];
    }

    private int rightChild(int index) {
        return heap[rightChildIndex(index)];
    }

    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int rightChildIndex(int index) {
        return index * 2 + 2;
    }

    public boolean isFull() {

        return size == heap.length;

    }
    
    private boolean hasLeftChild(int index){
        return leftChildIndex(index) <=size;
    }

    private boolean hasRightChild(int index){
        return rightChildIndex(index) <=size;
    }
    
    private void bubbleUp() {
        int index = size - 1; 
        while (index > 0 && heap[index] > heap[parent(index)]) {

            swap(index, parent(index));
            index = parent(index);
        }

    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void swap(int first, int second) {
        int temp = heap[first];
        heap[first] = heap[second];
        heap[second] = temp;
    }

}
