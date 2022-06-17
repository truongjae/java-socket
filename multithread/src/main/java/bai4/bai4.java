/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author truon
 */
public class bai4 {
    private int n;
    private synchronized void readFile() throws InterruptedException{
         try {
            FileInputStream filein = new FileInputStream("D:/number.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(filein));
            String strLine;
            while ((strLine = br.readLine()) != null)   {
                n=Integer.parseInt(strLine);
                if(n%2==0){
                 Thread t3 = new Thread(new Runnable() {
                     @Override
                     public void run() {
                        Thread3();
                     }
                 });
                 t3.start();
                 wait();
                }
                else{
                   Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Thread2();
                    }
                    });
                    t2.start();
                    wait();
                }
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(bai4.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(bai4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private synchronized void Thread2(){
        System.out.println("Uoc cua "+n+": ");
        for(int i=1;i<=n;i++){
            if(n%i==0) System.out.print(i+" ");
        }
        System.out.println("");      
        notify();
    }
      private synchronized void Thread3(){
        System.out.println("Binh phuong cua "+n+"="+(n*n));
        notify();
    }
     public static void main(String[] args) {
         bai4 bai4 = new bai4();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bai4.readFile();
                } catch (InterruptedException ex) {
                    Logger.getLogger(bai4.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        t.start();    
    }
    
}
