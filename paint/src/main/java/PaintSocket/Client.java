/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaintSocket;

import chat.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author thanh
 */
public class Client {
    private InetAddress host;
    private int port;
    public ClientReceiveData clientReceiveData;
    public ClientSendData clientSendData;
    public Client(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }
    public void execute() throws SocketException, IOException{
        DatagramSocket client = new DatagramSocket();
        client.send(createPacket(""));
        this.clientReceiveData = new ClientReceiveData(client);
        clientReceiveData.start();
        this.clientSendData = new ClientSendData(client,host,port);
        clientSendData.start();
    }
    public DatagramPacket createPacket(String value){
        byte[] arrData = value.getBytes();
        return new DatagramPacket(arrData,arrData.length,host,port);
    }
//    public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
//        Client client = new Client(InetAddress.getLocalHost(),2000);
//        client.execute();
//    }
}
class ClientReceiveData extends Thread{
    private DatagramSocket client;
    public String sms="";

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }
    public ClientReceiveData(DatagramSocket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while(true){
                String str = this.recieveData(client);
                this.sms = str;
//                System.out.println(str);
                
            }
        } catch (Exception e) {
        }
    }
    public String recieveData(DatagramSocket client) throws IOException{
        byte[] data = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(data,data.length);
        client.receive(receivePacket);
        return new String(receivePacket.getData()).trim();
    }
    
}

class ClientSendData extends Thread{
    private DatagramSocket client;
    private InetAddress host;
    private int port;
    private String sms="";
    public ClientSendData(DatagramSocket client, InetAddress host, int port) {
        this.client = client;
        this.host = host;
        this.port = port;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }
    
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while(true){
            try {
                    DatagramPacket datagramPacket = this.createPacket(this.sms);
                    client.send(datagramPacket);
            } catch (Exception e) {
            }
        }
    }
    public DatagramPacket createPacket(String value){
        byte[] arrData = value.getBytes();
        return new DatagramPacket(arrData,arrData.length,host,port);
    }
    
}