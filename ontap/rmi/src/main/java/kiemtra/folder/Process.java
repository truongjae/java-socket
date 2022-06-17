/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiemtra.folder;

import com.mycompany.rmi.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author truon
 */
public class Process extends  UnicastRemoteObject implements Service {
    public Process() throws RemoteException{
        super();
    }

    @Override
    public boolean checkSNT(int n) throws RemoteException {
        if (n<2) return false;
        for(int i=2;i<=Math.sqrt(n);i++)
            if (n%i==0) return false;
        return true;
    }

    @Override
    public String numberGreatThan4(String s) throws RemoteException {
        String x[] = s.split(",");
        String result="";
        for(String i : x){
            int k = Integer.parseInt(i);
            if(k%2==0 && k>4 && k<12) result+=String.valueOf(k)+",";
        }
        return result;
    }

    @Override
    public String insertValue(String s, int n,int index) throws RemoteException {
        String x[] = s.split(",");
        String result = "";
        ArrayList<Integer> arr = new ArrayList<>();
        for(String i : x){
            int k = Integer.parseInt(i);
            arr.add(k);
        }
        arr.add(index,n);
        for(int i : arr) result+= String.valueOf(i)+",";
        return result;
    }
    

    @Override
    public String sortOdd(String s) throws RemoteException {
        String x[] = s.split(",");
        int []k = new int[x.length];
        for(int i=0;i<k.length;i++) k[i] = Integer.parseInt(x[i]);
        for(int i=0;i<k.length-1;i++){
            for(int j=i+1;j<k.length;j++){
                if(k[i]%2==0 && k[j]%2!=0){
                    int temp = k[i];
                    k[i]=k[j];
                    k[j]=temp;
                }
            }
        }
        String result = "";
        for(int i=0;i<k.length;i++) result+=String.valueOf(k[i])+",";
        return result;
    }

    @Override
    public String AVG(String s) throws RemoteException {
        float sum=0.0f;
        int count=0;
        String x[] = s.split(",");
        for(String i : x){
            int k = Integer.parseInt(i);
            if(checkSNT(k)){
                sum+=k;
                count++;
            }
        }
        return String.valueOf((sum/(float)count));
    }

   
    
}
