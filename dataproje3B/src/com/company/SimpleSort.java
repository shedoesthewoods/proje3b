package com.company;

public class SimpleSort {
    public static int temp;

    public static void bubbleSort(int[] liste){
        for(int i = 0; i < liste.length; i++){
            for(int j = 0; j < liste.length - 1; j++){
                if(liste[j + 1] > liste[j]){
                    temp = liste[j];
                    liste[j] = liste[j + 1];
                    liste[j + 1] = temp;
                }
            }
        }
    }
}
//O(n^2)
