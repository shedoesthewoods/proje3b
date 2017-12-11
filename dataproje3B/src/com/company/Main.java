package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] ürünler = { "Dizüstü Bilgisayar,Bilgisayar,Dell,XPS 13 9333 Intel Core i5,10,5799,5927.3",
                "Buzdolabı,Beyaz Eşya,Regal,Cool RGL 3000,45,899.5,929",
                "Lazer Yazıcı,Yazıcı,HP,LaserJet Pro M102a G3Q34A,51,700,750",
                "Cep Telefonu,Telefon ve Aksesuarlar,Asus,Zenfone 3 Max ZC553KL,29,1000,1400",
                "Cep Telefonu,Telefon ve Aksesuarlar,Xiaomi,Mi 5S Plus,15,1305.6,1579.9",
                "SLR Fotoğraf Makinesi,Fotoğraf Makinesi,Canon, Eos 700D 18-55 IS STM DSLR,4,2110.7,2499",
                "Ocak,Beyaz Eşya,ICF,2817/443,9,341.8,388"};

        List<Ürün> liste = ürünNesneleriOluştur(ürünler);
        Tree tree = new Tree();
        TreeNode root = tree.getRoot();

        Tree bts = binarySearchTree(liste);
        List<Tree> kategoriAğaçları = kategoriAğacı(liste, kategoriler(liste));
//        System.out.println("Ağacın derinliği: " + kategoriAğaçları.get(0).depth(root));
        //kategori ağaçlarının özelliklerini tek tek yazdıracak metodları yaz sonra



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
    public static List<Tree> kategoriAğacı(List<Ürün> list, List<String> kategoriler){ //hata var dön
        List<Tree> kategoriAğaçları = new ArrayList<>();

        for (int i = 0; i < kategoriler.size(); i++) {
            for (Ürün ürün : list){
                if (ürün.getKategori().equals(kategoriler.get(i)))
                    kategoriAğaçları.get(i).insert(ürün);
            }
        }
        return kategoriAğaçları;
    }

    //Kategori isimlerini alıp sıralı şekilde döndüren metod
    public static List<String> kategoriler(List<Ürün> list){
        List<String> isimler = new ArrayList<>();
        for (Ürün ürün : list)
            isimler.add(ürün.getKategori());

        Collections.sort(isimler);
        return isimler;
    }

    //Verilen bir kategoriyi verilen başka bir kategoriye ekleyen metod
    public static void kategoriAktar(String nereden, String nereye, List<Tree> kategoriAğaçları){
        List<TreeNode> tempList = new ArrayList<>();
        TreeNode tempRoot;
        for(Tree tree : kategoriAğaçları){
            tempRoot = tree.getRoot();
            if (tempRoot.data.getKategori().equals(nereden)) {
                while (!tree.isEmpty())
                    tempList.add(tree.delete(tempRoot, tempRoot.data.toString()));
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
}
