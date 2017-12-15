package com.company;

public class Heap { //Max heap
    //Heap sınıfında tutulmak üzere oluşturulmuş iç sınıf
    class Node {
        private double data;
        public Node(double key) {
            data = key;
        }
        public double getKey() {
            return data;
        }
        public void setKey(double key) {
            data = key;
        }
    }

    private Node[] heapArray;
    private int maxSize;           // size of array
    private int currentSize;       // number of nodes in array

    public Heap(int mx)            // constructor
    {
        maxSize = mx;
        currentSize = 0;
        heapArray = new Node[maxSize];  // create array
    }

    public boolean isEmpty()
    { return currentSize == 0; }

    public boolean insert(double key)
    {
        if (currentSize == maxSize)
            return false;
        Node newNode = new Node(key);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }  // end insert()

    public void trickleUp(int index)
    {
        int parent = (index - 1) / 2;
        Node bottom = heapArray[index];

        while (index > 0 &&
                heapArray[parent].getKey() < bottom.getKey())
        {
            heapArray[index] = heapArray[parent];  // move it down
            index = parent;
            parent = (parent - 1) / 2;
        }  // end while
        heapArray[index] = bottom;
    }  // end trickleUp()

    public double remove()           // En yüksek değere sahip elemanı siler
    {
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root.data;
    }
    public double removeMin()
    {
        double min = heapArray[0].getKey();
        int minindex = 0;
        for (int i = 0; i < currentSize; i++)
        {
            if (min > heapArray[i].getKey())
            {
                min = heapArray[i].getKey();
                minindex = i;
            }
        }
        heapArray[minindex].setKey(1000);
        return min;

    }
    public void trickleDown(int index)
    {
        int largerChild;
        Node top = heapArray[index];       // save root
        while (index < currentSize / 2)       // while node has at
        {                               //    least one child,
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            // find larger child
            if (rightChild < currentSize &&  // (rightChild exists?)
                    heapArray[leftChild].getKey() <
                            heapArray[rightChild].getKey())
                largerChild = rightChild;
            else
                largerChild = leftChild;
            // top >= largerChild?
            if (top.getKey() >= heapArray[largerChild].getKey())
                break;
            // shift child up
            heapArray[index] = heapArray[largerChild];
            index = largerChild;            // go down
        }  // end while
        heapArray[index] = top;            // root to index
    }
    // end trickleDown()

    public boolean change(int index, int newValue)
    {
        if (index < 0 || index >= currentSize)
            return false;
        double oldValue = heapArray[index].getKey(); // remember old
        heapArray[index].setKey(newValue);  // change to new

        if (oldValue < newValue)             // if raised,
            trickleUp(index);                // trickle it up
        else                                // if lowered,
            trickleDown(index);              // trickle it down
        return true;
    }  // end change()


    public void displayHeap()
    {
        for (int m = 0; m < currentSize; m++)
            if (heapArray[m] != null)
                System.out.println(heapArray[m].getKey() + " ");
            else
                System.out.println("-- ");
        System.out.println();
    }
}
