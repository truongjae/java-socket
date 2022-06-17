/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai3;

import com.mycompany.phantan.bai2.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author truon
 */
public interface Service extends Remote{
    String sortUp(String s) throws RemoteException;
    String sortDown(String s) throws RemoteException;
    String addElmt(String s,int value,int index)throws RemoteException;
    String delElmt(String s, int index)throws RemoteException;
    String searchElmt(String s)throws RemoteException;
}
