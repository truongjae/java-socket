/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sapxepmang;

import kiemtra.folder.*;
import com.mycompany.rmi.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author truon
 */
public class Process extends  UnicastRemoteObject implements Service {
    public Process() throws RemoteException{
        super();
    }

    @Override
    public String SX(String s) throws RemoteException {
        String[]s1 = s.split(",");
            int []a= new int[s1.length];
            for(int i=0;i<s1.length;i++)
                a[i] = Integer.parseInt(s1[i]);
            for(int i=0;i<a.length-1;i++){
                for(int j=i+1;j<a.length;j++){
                    if(a[i]>a[j]){
                        int temp = a[i];
                        a[i] = a[j];
                        a[j] = temp;
                    }
                }
            }
            String kq = "";
            for(int i=0;i<a.length;i++) kq+=a[i]+",";
            return kq;    
    }
    
}
