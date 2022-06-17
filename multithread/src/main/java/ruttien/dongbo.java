/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruttien;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author truon
 */
public class dongbo {
    private int balance = 1000;
    
    private synchronized void rutTien(int amount) throws InterruptedException{
        System.out.println("Giao dich rut tien dang thuc hien "+amount+"...");
        if(balance < amount){
            System.out.println("Rut khong thanh cong");
            wait();
        }
        balance-=amount;
        System.out.println("Rut thanh cong. So tien con lai la: "+balance);
    }
    private synchronized void napTien(int amount){
        System.out.println("Dang nap tien "+amount);
        balance+=amount;
        System.out.println("So tien hien tai la "+balance);
        notify();
    }
    public static void main(String[] args) throws InterruptedException {
        dongbo dongbo = new dongbo();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dongbo.rutTien(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(dongbo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                dongbo.napTien(3000);
            }
        });
        thread2.start();
    }
}
