package com.company;

public class Ürün {
    private String ürünAdı;
    private String kategori;
    private String marka;
    private String model;
    private double fiyat;
    private double maliyet;
    private int miktar;

    public Ürün(String ürünAdı, String kategori, String marka, String model, int miktar, double maliyet, double fiyat){
        this.ürünAdı = ürünAdı;
        this.kategori = kategori;
        this.marka = marka;
        this.model = model;
        this.fiyat = fiyat;
        this.maliyet = maliyet;
        this.miktar = miktar;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public double getMaliyet() {
        return maliyet;
    }

    public void setMaliyet(double maliyet) {
        this.maliyet = maliyet;
    }

    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    public String getÜrünAdı() {
        return ürünAdı;
    }

    public void setÜrünAdı(String ürünAdı) {
        this.ürünAdı = ürünAdı;
    }

    @Override
    public String toString(){
        return ürünAdı + " " + marka + " " + model + " " + kategori + " " + miktar + " " + maliyet + " " + fiyat;
    }
}
