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
public class ThreadSNT implements Runnable
{
    private int n;
    public ThreadSNT(int n){
        
        this.n=n;
    }

    public boolean checkSNT(int n){
        if(n<2) return false;
        for(int i=2;i<=Math.sqrt(n);i++){
            if(n%i==0) return false;
        }
        return true;
    }
    @Override
    public void run() {
        System.out.println("SNT < n: ");
        for(int i=1;i<=n;i++){
            if(checkSNT(i)) System.out.print(i+" ");
        }
    }
    
}
