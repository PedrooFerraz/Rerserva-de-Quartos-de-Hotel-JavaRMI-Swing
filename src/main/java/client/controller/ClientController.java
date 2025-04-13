/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
            this.service = (ReservaHotel) Naming.lookup("rmi://" + Ip + "/HotelService");
        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            System.out.println("Erro ao conectar ao serviço RMI: " + e.getMessage());
        }
    }

    public ReservaHotel getService() {
        return service;
    }
}
