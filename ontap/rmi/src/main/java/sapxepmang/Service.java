/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sapxepmang;

import kiemtra.folder.*;
import com.mycompany.rmi.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author truon
 */
public interface Service extends Remote{
    String SX(String s) throws RemoteException;
}
