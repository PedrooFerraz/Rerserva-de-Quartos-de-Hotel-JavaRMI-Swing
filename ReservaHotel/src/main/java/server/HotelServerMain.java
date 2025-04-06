/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server;

import rmi.ReservaHotel;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi.ReservaHotelImpl;

/**
 *
 * @author Pedro Henrique
 */
public class HotelServerMain {
    
    public HotelServerMain(String host){
        System.setProperty("java.rmi.server.hostname", host);
        try{
            ReservaHotel hotel = new ReservaHotelImpl();
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("HotelService", hotel);

        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
    public static void main(String[] args) {
         String Ip = (args.length > 0) ? args[0] : "127.0.0.1";
         new HotelServerMain(Ip);
    }
    
}
