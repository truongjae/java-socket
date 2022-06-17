/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiemtra.folder;

import com.mycompany.rmi.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author truon
 */
public interface Service extends Remote{
    boolean checkSNT(int n) throws RemoteException;
    String AVG(String s) throws RemoteException;
    String numberGreatThan4(String s) throws RemoteException;
    String insertValue(String s,int n,int index)throws RemoteException;
    String sortOdd(String s) throws RemoteException;
}
