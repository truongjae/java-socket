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
public class demo {
    public static volatile int x=0;
    public static Thread temp;
    public synchronized static void cong(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
               x+=1;
            }
        });
        thread.start(); 
    }
    public synchronized static void tru(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
               x-=1;
            }
        });
        thread.start(); 
    }
     public synchronized static void all(){
               while(true){
                   cong();
                   tru();
                   System.out.println(x);
               }
    }
    public static void main(String[] args) { 
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
              all();
            }
        });
        thread.start(); 
        System.out.println(thread.isAlive());
    }
  
}
