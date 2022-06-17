/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai2;

/**
 *
 * @author truon
 */
public class main {
    public static void main(String[] args) throws InterruptedException {
        Thread1 t1 = new Thread1(5);
        Thread thread1 = new Thread(t1);
        thread1.start();
        thread1.join();
        int a[] = t1.a;
        for(int i : a){
            System.out.print(i);
        }
        
    }
}
