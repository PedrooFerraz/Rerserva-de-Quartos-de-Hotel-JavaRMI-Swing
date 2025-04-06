/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;

import com.google.gson.reflect.TypeToken;
import rmi.ReservaHotel;
import server.model.Cliente;
import server.model.Quarto;
import server.model.Reserva;
import server.util.JsonHelper;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ReservaHotelImpl extends UnicastRemoteObject implements ReservaHotel {

    private JsonHelper<Cliente> clienteHelper;
    private JsonHelper<Quarto> quartoHelper;
    private JsonHelper<Reserva> reservaHelper;

    private List<Cliente> clientes;
    private List<Quarto> quartos;
    private List<Reserva> reservas;

    public ReservaHotelImpl() throws RemoteException {
        super();
        clienteHelper = new JsonHelper<>("clientes.json", new TypeToken<List<Cliente>>() {
        }.getType());
        quartoHelper = new JsonHelper<>("quartos.json", new TypeToken<List<Quarto>>() {
        }.getType());
        reservaHelper = new JsonHelper<>("reservas.json", new TypeToken<List<Reserva>>() {
        }.getType());

        clientes = clienteHelper.loadList();
        quartos = quartoHelper.loadList();
        reservas = reservaHelper.loadList();
    }

    @Override
    public String cadastrar(String nome, String cpf) throws RemoteException {
        Cliente cliente = new Cliente(nome, cpf);
        clientes.add(cliente);
        clienteHelper.saveList(clientes);
        return "Cliente cadastrado com sucesso: " + nome;
    }

    @Override
    public List<Quarto> consultarQuartosDisponveis(Date data) throws RemoteException {
        List<Quarto> disponiveis = new ArrayList<>(quartos);
        for (Reserva reserva : reservas) {
            if (!data.before(reserva.getDataEntrada()) && !data.after(reserva.getDataSaida())) {
                disponiveis.removeIf(q -> q.getNumero() == reserva.getQuarto());
            }
        }
        return disponiveis;
    }

    @Override
    public String fazerReserva(Date dataEntrada, Date dataSaida, String cpf, int numeroQuarto) throws RemoteException {
        Cliente cliente = clientes.stream().filter(c -> c.getCpf().equals(cpf)).findFirst().orElse(null);
        if (cliente == null) {
            return "Cliente não encontrado.";
        }

        for (Reserva r : reservas) {
            if (r.getQuarto() == numeroQuarto && !(dataSaida.before(r.getDataEntrada()) || dataEntrada.after(r.getDataSaida()))) {
                return "Quarto indisponível para esse período.";
            }
        }

        long id = System.currentTimeMillis();
        Reserva novaReserva = new Reserva(id, dataEntrada, dataSaida, cpf, numeroQuarto);
        reservas.add(novaReserva);
        reservaHelper.saveList(reservas);
        return "Reserva realizada com ID: " + id;
    }

    @Override
    public List<Quarto> listarQuartosDisponiveis() throws RemoteException {
        return quartos;
    }

    
    
    //PRECISA IMPLEMENTAR ESSE MÉTODOS ABAIXO
    
    @Override
    public String buscarReserva(String cpf) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String cancelarReserva(Long id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String cadastrarQuarto(int numeroQuarto, BigDecimal valorDiaria, int tipo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Reserva> listarReservas() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
