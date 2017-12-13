package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] ürünler = {
                "Dizüstü Bilgisayar,Bilgisayar,Dell,XPS 13 9333 Intel Core i5,10,5799,5927.3",
                "Buzdolabı,Beyaz Eşya,Regal,Cool RGL 3000,45,899.5,929",
                "Lazer Yazıcı,Yazıcı,HP,LaserJet Pro M102a G3Q34A,51,700,750",
                "Cep Telefonu,Telefon ve Aksesuarlar,Asus,Zenfone 3 Max ZC553KL,29,1000,1400",
                "Cep Telefonu,Telefon ve Aksesuarlar,Xiaomi,Mi 5S Plus,15,1305.6,1579.9",
                "SLR Fotoğraf Makinesi,Fotoğraf Makinesi,Canon, Eos 700D 18-55 IS STM DSLR,4,2110.7,2499",
                "Ocak,Beyaz Eşya,ICF,2817/443,9,341.8,388"};

        List<Ürün> liste = ürünNesneleriOluştur(ürünler);
        Tree bst = binarySearchTree(liste);

        List<String> kategoriler = kategoriler(liste);
        List<Tree> kategoriAğaçları = kategoriAğacı(liste, kategoriler);


    }

    //Verilen ürün stringlerini Ürün nesnelerine çevirip listeye yerleştiren metod
    public static List<Ürün> ürünNesneleriOluştur(String[] ürünler){
        List<Ürün> liste = new ArrayList<Ürün>();
        StringTokenizer data;
        ArrayList kelimeler;
        String kelime;
        Ürün ürünNesnesi;
        for (String ürün : ürünler) {
            kelimeler = new ArrayList(7);
            data = new StringTokenizer(ürün, ",");
            while (data.hasMoreTokens()) {
                kelime = data.nextToken();
                kelimeler.add(kelime);
            }
            ürünNesnesi = new Ürün( (String) kelimeler.get(0), (String)kelimeler.get(1), (String)kelimeler.get(2),
                    (String)kelimeler.get(3), Integer.parseInt((String) kelimeler.get(4)),
                    Double.parseDouble((String) kelimeler.get(5)), Double.parseDouble((String) kelimeler.get(6)));
            liste.add(ürünNesnesi);
        }
        return liste;
    }

    //Ürünleri ürünAdı + marka + model'e göre ikili arama ağacına yerleştiren metod
    public static Tree binarySearchTree(List<Ürün> list){
        Tree bts = new Tree();
        for(Ürün ürün : list)
            bts.insert(ürün);
        return bts;
    }

    //Kategori ağaçlarını sıralanmış kategori isimleriyle uyumlu indexlerde başka bir listeye atan metod
    public static List<Tree> kategoriAğacı(List<Ürün> list, List<String> kategoriler){
        List<Tree> kategoriAğaçları = new ArrayList<>();
        Tree tempTree;

        for (int i = 0; i < kategoriler.size(); i++) {
            tempTree = new Tree();
            for (Ürün ürün : list){
                if (ürün.getKategori().equals(kategoriler.get(i)))
                    tempTree.insert(ürün);
            }
            kategoriAğaçları.add(tempTree);
        }
        return kategoriAğaçları;
    }

    //Kategori isimlerini alıp sıralı şekilde döndüren metod
    public static List<String> kategoriler(List<Ürün> list){
        List<String> isimler = new ArrayList<>();
        for (Ürün ürün : list)
            if(!isimler.contains(ürün.getKategori()))
                isimler.add(ürün.getKategori());

        Collections.sort(isimler);
        return isimler;
    }

    //Verilen bir kategoriyi verilen başka bir kategoriye ekleyen metod
    public static void kategoriAktar(String nereden, String nereye, List<Tree> kategoriAğaçları){
        /**
         *
         * yine bak olmamış
         */
        List<TreeNode> tempList = new ArrayList<>();
        TreeNode tempRoot;
        for(Tree tree : kategoriAğaçları){
            tempRoot = tree.getRoot();
            if (tempRoot.data.getKategori().equals(nereden)) {
                while (!tree.isEmpty())
                    tempList.add(tree.delete(tempRoot.data.toString()));
            }
        }
        for(Tree tree : kategoriAğaçları){
            tempRoot = tree.getRoot();
            if (tempRoot.data.getKategori().equals(nereye)){
                for(TreeNode node : tempList)
                    tree.insert(node.data);
            }
        }
    }

    //Ürünleri Hashtable veri yapısına aktaran metod
    public static Hashtable<String, Ürün> hashtable(List<Ürün> list){
        Hashtable<String, Ürün> returnTable = new Hashtable<>();
        String tempStr;
        for (Ürün ürün : list){
            tempStr = ürün.getÜrünAdı() + ürün.getMarka() + ürün.getModel();
            returnTable.putIfAbsent(tempStr, ürün);
        }
        return returnTable;
    }

    //Verilen kategorideki ürünlerde indirim yapan metod
    public static void indirim(Hashtable<String, Ürün> hashtable, String kategori){
        for (Ürün ürün : hashtable.values()){
            if (ürün.getKategori().equals(kategori))
                ürün.setFiyat(ürün.getFiyat()*0.9);
        }
    }

    //Bir ağaçtaki tüm ürünler satıldığında elde edilecek karı hesaplayan metod
    //Kar hesabı yapılacak ağacın kök düğümü parametre olarak verilmelidir
    public static void karHesapla(TreeNode node, double kar){
        if (node == null) return;
        kar += Math.abs(node.data.getFiyat() - node.data.getMaliyet()) * node.data.getMiktar();
        karHesapla(node.left, kar);
        karHesapla(node.right, kar);
    }

    //Ürünlerin maliyetlerini heap veri yapısına yerleştiren metod
    public static void maliyetHeap(Heap heap, List<Ürün> liste){
        for(Ürün ürün : liste)
            heap.insert(ürün.getMaliyet());
    }

    //Ürünler arasında en ucuz ve pahalı iki ürünü listeleyen metod
    public static void ucuzPahalı(Heap heap){
        System.out.println("Yığındaki en pahalı ürün: " + heap.remove());
        System.out.println("Yığındaki en pahalı ikinci ürün: " + heap.remove());
        System.out.println("Yığındaki en ucuz ürün: " + heap.removeMin());
        System.out.println("Yığındaki en ucuz ikinci ürün: " + heap.removeMin());
    }

}
