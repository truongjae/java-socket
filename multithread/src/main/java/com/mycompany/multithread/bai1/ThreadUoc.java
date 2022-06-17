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
public class ThreadUoc implements Runnable{

    private int n;
    public ThreadUoc(int n){
        this.n=n;
    }
    @Override
    public void run() {
        System.out.println("Uoc cua n: ");
        for(int i=1;i<=n;i++){
            if(n%i==0) System.out.print(i+" ");
        }
    }
    
}
