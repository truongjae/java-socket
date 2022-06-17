/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.multithread;

/**
 *
 * @author truon
 */
public class Thread1 extends Thread{

    @Override
    public void run() {
        for(int i=0;i<=10;i++){
            System.out.print("0 ");
        }
    }
    
}
