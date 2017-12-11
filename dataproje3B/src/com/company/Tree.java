package com.company;

public class Tree {
    private TreeNode root;
    private int nodeNum = 0;

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
    public TreeNode delete(TreeNode node, String data){ //art arda node silinecekse yine bak
        if(root == null) return null;
        if (node == null) return null;
        if (data.compareTo(node.data.toString()) < 0)
            node.left = delete(node.left, data);
        else if (data.compareTo(node.data.toString()) > 0)
            node.right = delete(node.right, data);
        else{
            if (node.left != null || node.right != null){
                node.data = minValue(node.right).data;
                node.right = delete(node.right, minValue(node.right).toString());
            }
            else if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else
                node = null;
        }
        nodeNum--;
        return node;
    }

    //Bilgisi verilen düğümü ağaçta bulan metod
    public TreeNode find(String data){
        if(root == null) return null;

        TreeNode temp = root;
        boolean found = false;
        while(!found){
            if(temp.data.toString().equals(data)){
                found = true;
            }
            else if(temp.data.toString().compareTo(data) < 0)
                temp = temp.right;
            else
                temp = temp.left;
        }
        return temp;
    }

    //Verilen düğümün alt ağacının en küçük değerini döndüren metod
    public TreeNode minValue(TreeNode node){
        if (node.left == null) return node;
        return minValue(node.left);
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

    /**
     *
     * @return
     *
     * başka bir şey istiyormuş
     */

    //Ağacın her bir derinlikteki ortalama düğüm sayısını dizide döndüren metod
    public int[] average(){
        int maxDepth = depth((root));
        int[] averages = new int[maxDepth];
        findDepthElementSum(root, averages, 0);
        int temp;
        for(int i = 0; i < maxDepth; i++){
            temp = averages[i]/nodeNum;
            averages[i] = temp;
        }
        return averages;
    }

    //Her bir derinlikte kaç düğüm olduğunu bulan metod
    public void findDepthElementSum(TreeNode node, int[] depthList, int depth){
        if (node == null) return;
        depth++;
        findDepthElementSum(node.left, depthList, depth);
        depthList[depth]++;
        depth--;
        findDepthElementSum(node.right, depthList, depth);
    }

    //Ağacın boş olup olmadığını döndüren metod
    public boolean isEmpty(){
        return (root == null);
    }
}
