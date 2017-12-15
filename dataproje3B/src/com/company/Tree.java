package com.company;

public class Tree {
    private TreeNode root;
    private int nodeNum = 0;
    private int[] depthList = new int[10];

    public Tree(){
        root = null;
    }

    public TreeNode getRoot(){
        return root;
    }

    //Ağaca yeni bir düğüm ekleyen metod
    public void insert(Ürün newData) {
        nodeNum++;
        TreeNode newNode = new TreeNode();
        newNode.data = newData;
        if (root == null) root = newNode;
        else {
            TreeNode current = root;
            TreeNode parent;
            while (true) {
                parent = current;
                if (newData.toString().compareTo(current.data.toString()) < 0){
                    current = current.left;
                    if (current == null){
                        parent.left = newNode;
                        return;
                    }
                }
                else {
                    current = current.right;
                    if (current == null){
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    //Ağaçtan bilgisi verilen düğümü silen metod
    public TreeNode delete(String data){
        TreeNode current = root;
        TreeNode parent = root;
        boolean isLeftChild = true;

        while(!current.data.toString().equals(data)){
            parent = current;
            if(data.compareTo(current.data.toString()) < 0){
                isLeftChild = true;
                current = current.left;
            }
            else{
                isLeftChild = false;
                current = current.right;
            }
            if(current == null)
                return null;
        }

        if (current.left == null && current.right == null){
            if(current == root)
                root = null;
            else if(isLeftChild)
                parent.left = null;
            else
                parent.right = null;
        }

        else if(current.right==null)
            if(current == root)
                root = current.left;
            else if(isLeftChild)
                parent.left = current.left;
            else
                parent.right = current.left;

        else if(current.left==null)
            if(current == root)
                root = current.right;
            else if(isLeftChild)
                parent.left = current.right;
            else
                parent.right = current.right;

        else{
            TreeNode successor = getSuccessor(current);
            if(current == root)
                root = successor;
            else if(isLeftChild)
                parent.left = successor;
            else
                parent.right = successor;

            successor.left = current.left;
        }
        return current;
    }

    //Delete metodunda silinecek olan düğümün yerine geçecek düğümü bulan metod
    private TreeNode getSuccessor(TreeNode delNode)
    {
        TreeNode successorParent = delNode;
        TreeNode successor = delNode;
        TreeNode current = delNode.right;
        while(current != null){
            successorParent = successor;
            successor = current;
            current = current.left;
        }

        if(successor != delNode.right){
            successorParent.left = successor.right;
            successor.right = delNode.right;
        }
        return successor;
    }

    //Ağacın derinliğini döndüren metod
    //Metodu çağırırken argümanı kök düğümü olmalıdır
    public int depth(TreeNode node){
        if (node == null) return 0;
        return 1 + Math.max(depth(node.left), depth(node.right));
    }

    //Toplam düğüm sayısını döndüren metod
    public int getNodeNum(){
        return nodeNum;
    }

    //Düğümlerin derinlik ortalamasını bulduran metod
    public double average(){
        int maxDepth = depth((root));
        double total = 0;
        for(int i = 0; i < 10; i++){
            total += depthList[i] * i;
        }
        return total / nodeNum;
    }

    //Her bir derinlikte kaç düğüm olduğunu bulan metod
    public void findDepths(TreeNode node, int depth){
        if (node == null) return;
        depth++;
        findDepths(node.left, depth);
        depthList[depth]++;
        findDepths(node.right, depth);
    }

    //Ağacın boş olup olmadığını döndüren metod
    public boolean isEmpty(){
        return (root == null);
    }
}
