/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package array;

import java.util.Scanner;

/**
 *
 * @author truon
 */
public class Array {
     private int n;
    public int a[];
    public Array(int n){
        this.n=n;
        this.a = new int[n];
    }
    public int[] listArray(){
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<this.n;i++){
            System.out.println("nhap vao pt thu "+(i+1));
            a[i] = sc.nextInt();
        }
        return a;
    }
}

