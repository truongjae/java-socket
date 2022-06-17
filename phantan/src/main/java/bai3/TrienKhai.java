/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author truon
 */
public class TrienKhai extends  UnicastRemoteObject implements Service{
    
    private String arr;
    private ArrayList<Integer> arrayList;
    public TrienKhai() throws RemoteException{
        super();
    }
    public void loadArr(String arr){
       this.arr = arr;
       String s[] = arr.split(",");
       this.arrayList = new ArrayList<>();
       for(String x : s) this.arrayList.add(Integer.parseInt(x));
    }

    public int[] ArrayListToArr(ArrayList<Integer> arrayList){
        int a[] = new int[arrayList.size()];
        for(int i=0;i<arrayList.size();i++) a[i]=arrayList.get(i);
        return a;
    }
    
    public String sx(int choice){
        int n = this.arrayList.size();
        int a[] = ArrayListToArr(this.arrayList);
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(choice==1){
                    if(a[i]<a[j]){
                        int temp = a[i];
                        a[i] = a[j];
                        a[j] = temp;
                    }
                }
                else{
                    if(a[i]>a[j]){
                        int temp = a[i];
                        a[i] = a[j];
                        a[j] = temp;
                    }
                }
            }
        }
        String arrString = "";
        for(int x : a){
            arrString+=String.valueOf(x)+",";
        }
        return arrString;
    }
    @Override
    public String sortUp(String s) throws RemoteException {
        loadArr(s);
        return sx(0);
    }

    @Override
    public String sortDown(String s) throws RemoteException {
        loadArr(s);
       return sx(1);
    }

    @Override
    public String addElmt(String s,int value, int index) throws RemoteException {
        loadArr(s);
        this.arrayList.add(index,value);
        String arrString="";
        for(int x : this.arrayList) arrString+=String.valueOf(x)+",";
        return arrString;
    }

    @Override
    public String delElmt(String s,int index) throws RemoteException {
        loadArr(s);
        this.arrayList.remove(index);
        String arrString="";
        for(int x : this.arrayList) arrString+=String.valueOf(x)+",";
        return arrString;
    }

    @Override
    public String searchElmt(String s) throws RemoteException {
        loadArr(s);
        String arrString="";
        for(int x : this.arrayList){
            if(x%10==2) arrString+=String.valueOf(x)+",";
        }
        return arrString;
    }

   
}
