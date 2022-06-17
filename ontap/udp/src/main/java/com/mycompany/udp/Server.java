/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


/**
 *
 * @author truon
 */
public class Server {
    private int port;
    private DatagramSocket socket;
    private byte[] receiveData,sendData;
    private DatagramPacket receivePacket;
    private DatagramPacket sendPacket;

    public Server(int port){
        this.port = port;
    }
    public boolean checkSNT(String str){
        String x[] = str.split("-");
        int a = Integer.parseInt(x[0]);
        int b = Integer.parseInt(x[1]);
        int min = a;
        if (min>b){
            min = b;
        }
        for(int i=min;i>1;i--){
            if(a%i==0 && b%i==0){
                return false;
            }
        }
        return true;
    }
    public String getData() throws IOException {
        this.socket = new DatagramSocket(this.port);
        System.out.println(socket.getInetAddress());
        //nhan dl
        this.receiveData = new byte[256];
        this.receivePacket = new DatagramPacket(this.receiveData,this.receiveData.length);
        this.socket.receive(this.receivePacket);
        //xly
        String str = new String(receivePacket.getData()).trim();
        return str;
    }
    public void sendData(String str) throws IOException {
        String tl="";
        if(checkSNT(str)) tl="Nguyen to cung nhau";
        else tl="Khong nguyen to cung nhau";
        this.sendData = new byte[256];
        this.sendData = tl.getBytes();
        //this.sendPacket = new DatagramSocket(this.sendData,this.sendData.length,this.receivePacket,this.receivePacket.getPort());
        this.sendPacket = new DatagramPacket(this.sendData,this.sendData.length,this.receivePacket.getAddress(),this.receivePacket.getPort());
        this.socket.send(this.sendPacket);

    }
}
