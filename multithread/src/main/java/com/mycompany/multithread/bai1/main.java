/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.multithread.bai1;

import java.util.Scanner;

/**
 *
 * @author truon
 */
public class main {
    public static void main(String[] args) throws InterruptedException {
        int n;
        System.out.println("Nhap vao n: ");
        Scanner sc = new Scanner(System.in);
        n=sc.nextInt();
        Thread thread1 = new Thread(new ThreadUoc(n));
        Thread thread2 = new Thread(new ThreadSNT(n));
        thread1.start();
        thread1.join();
        System.out.println("");
        thread2.start();
    }
}
