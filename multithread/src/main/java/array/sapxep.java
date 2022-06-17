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
public class sapxep{
        public static void main(String[] args) throws InterruptedException {
            int n;
            Scanner sc = new Scanner(System.in);
            System.out.println("nhap so luong phan tu: ");
            n=sc.nextInt();
            Array array = new Array(n);
            int[] a = array.listArray();
            Thread t1 = new Thread(new Runnable() {
                @Override
                public synchronized void run() {
                    for(int i=0;i<(int)a.length/2;i++){
                        int min=i;
                        for(int j=i;j<a.length;j++){
                            if(a[i]>a[j]) min=j;
                        }
                        int temp = a[min];
                        a[min] = a[i];
                        a[i] =temp;
                    }
                }
            });
            t1.start();
            Thread t2 = new Thread(new Runnable() {
                @Override
                public synchronized void run() {
                    for(int i=(int)a.length/2;i<a.length;i++){
                        int min=i;
                        for(int j=i;j<a.length;j++){
                            if(a[i]>a[j]) min=j;
                        }
                        int temp = a[min];
                        a[min] = a[i];
                        a[i] =temp;
                    }
                }
            });
            t2.start();
            t1.join();
            t2.join();
            for(int i : a){
                System.out.println(i);
            }
                
        }
    }