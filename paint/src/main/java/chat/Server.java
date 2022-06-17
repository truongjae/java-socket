/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author truon
 */
public class Server {
    private int port;
    private InetAddress clientIP;
    private int clientPort;
    public static Map<DatagramPacket,Integer> listSocket = new HashMap<>();

    public Server(int port) {
        this.port = port;
    }
    public void execute() throws SocketException, IOException{
        DatagramSocket datagramSocket = new DatagramSocket(port);
        ServerReceiveData serverReceiveData = new ServerReceiveData(datagramSocket);
        serverReceiveData.start();
        while(true){
            String str = this.recieveData(datagramSocket);
            for(DatagramPacket item: listSocket.keySet()){
                if(!(item.getAddress().equals(clientIP) && item.getPort() == clientPort))
                    ServerReceiveData.sendData(str, datagramSocket, item.getAddress(), item.getPort());
            }
            System.out.println(str);
        }
    }
    public String recieveData(DatagramSocket server) throws IOException{
        byte[] data = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(data,data.length);
        server.receive(receivePacket);
        clientIP = receivePacket.getAddress();
        clientPort = receivePacket.getPort();
        checkDuplicate(receivePacket);
        return new String(receivePacket.getData()).trim();
    }
    public void checkDuplicate(DatagramPacket datagramPacket){
        for(DatagramPacket item : listSocket.keySet()){
            if(item.getAddress().equals(datagramPacket.getAddress()) && item.getPort() == datagramPacket.getPort()){
                listSocket.replace(item, 0);
                return;
            }
        }
        listSocket.put(datagramPacket,0);
    }
    public static void main(String[] args) throws IOException {
        Server server = new Server(2000);
        server.execute();
    }
}

class ServerReceiveData extends Thread{
    private DatagramSocket datagramSocket;

    public ServerReceiveData(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while(true){
            String str = sc.nextLine();
            try {
                for (DatagramPacket item: Server.listSocket.keySet()) {
                    this.sendData(str, datagramSocket, item.getAddress(), item.getPort());
                }
            } catch (Exception e) {
            }
                
        }
    }
    public static void sendData(String value,DatagramSocket datagramSocket,InetAddress clientIP,int clientPort) throws IOException{
        byte[] data = new byte[1024];
        data = value.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(data,data.length,clientIP,clientPort);
        datagramSocket.send(sendPacket);
    }
}