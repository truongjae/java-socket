/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai2;

import java.util.Scanner;


/**
 *
 * @author truon
 */
public class Thread1 implements Runnable{
    private int n;
    public int a[];
    public Thread1(int n){
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
    @Override
    public void run() {
        listArray();
    }
    
}
