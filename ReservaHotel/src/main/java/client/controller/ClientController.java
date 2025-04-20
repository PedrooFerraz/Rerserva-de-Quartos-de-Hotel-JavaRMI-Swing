package client.controller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import rmi.ReservaHotel;

/**
 *
 * @author Pedro Henrique
 */
public class ClientController {
    
    private ReservaHotel service;

    public ClientController(String Ip) {

        try {
            service = (ReservaHotel) Naming.lookup("rmi://" + Ip + "/HotelService");

        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            System.out.println(e);
        }
    }

    public ReservaHotel getService() {
        return service;
    }
}
