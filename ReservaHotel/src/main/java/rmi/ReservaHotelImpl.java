/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;

import com.google.gson.reflect.TypeToken;
import server.model.Cliente;
import server.model.Quarto;
import server.model.Reserva;
import server.util.JsonHelper;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    Path pathCliente = Paths.get("src/main/java/server/data/clientes.json");
    Path pathQuarto = Paths.get("src/main/java/server/data/quartos.json");
    Path pathReserva = Paths.get("src/main/java/server/data/reservas.json");

    public ReservaHotelImpl() throws RemoteException {
        super();
        clienteHelper = new JsonHelper<>(pathCliente + "", new TypeToken<List<Cliente>>() {
        }.getType());
        quartoHelper = new JsonHelper<>(pathQuarto + "", new TypeToken<List<Quarto>>() {
        }.getType());
        reservaHelper = new JsonHelper<>(pathReserva + "", new TypeToken<List<Reserva>>() {
        }.getType());

        clientes = clienteHelper.loadList();
        quartos = quartoHelper.loadList();
        reservas = reservaHelper.loadList();
    }

    @Override
    public String cadastrar(String nome, String cpf) throws RemoteException {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return "Erro: Já existe um cliente com este CPF.";
            }
        }
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

        boolean quartoExiste = quartos.stream().anyMatch(q -> q.getNumero() == numeroQuarto);
        if (!quartoExiste) {
            return "Quarto não encontrado.";
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
        // Criaa uma lista para guardar as reservas encontradas
        List<Reserva> reservasCliente = new ArrayList<>();

        //Procurar todas as reservas com o CPF
        for (Reserva r : reservas) {
            if (r.getCpf().equals(cpf)) {
                // ADICIONA RESERVA NA LISTA
                reservasCliente.add(r);
            }
        }

        // SE NAO ENCONTRAR, RETORNA
        if (reservasCliente.isEmpty()) {
            return "Nenhuma reserva encontrada para o CPF:" + cpf;
        }

        //SE ENCONTRAR, MONTA UMA STRING COM INFORMAÇÕES
        String resultado = "Reservas encontradas para o CPF" + cpf + ":\n";
        for (Reserva r : reservasCliente) {
            resultado += "ID:" + r.getId()
                    + ", Entrada:" + r.getDataEntrada()
                    + ", Saida:" + r.getDataSaida()
                    + ", Quarto:" + r.getQuarto() + "\n";
        }
        return resultado;
    }

    @Override
    public String cancelarReserva(Long id) throws RemoteException {
        // PROCURAR A RESERVA PELO ID

        Reserva reservaEncontrada = null;

        for (Reserva r : reservas) {
            if (r.getId().equals(id)) {
                reservaEncontrada = r;
                break;
            }
        }

        //Se não encontrar, mostra erro
        if (reservaEncontrada == null) {
            return "Reserva com ID" + id + "não encontrado.";
        }

        // Remover a reserva da lista
        reservas.remove(reservaEncontrada);

        // SALVAR A LISTA ATUALIZADA NO ARQUIVO
        reservaHelper.saveList(reservas);
        return "Reserva com ID" + id + "cancelada com sucesso.";
    }

    @Override
    public String cadastrarQuarto(int numeroQuarto, BigDecimal valorDiaria, int tipo) throws RemoteException {
        // VERIFICA SE JA EXISTE QUARTO COM O NUMERO
        for (Quarto q : quartos) {
            if (q.getNumero() == numeroQuarto) {
                return "Erro: Já existe quarto com este numero";
            }
        }

        //Cria um novo objeto quarto
        Quarto novoQuarto = new Quarto(numeroQuarto, valorDiaria, tipo);

        //Adc na lista de quartos
        quartos.add(novoQuarto);

        //SALVA A LISTA NO ARQUIVO JSON
        quartoHelper.saveList(quartos);

        return "Quarto numero" + numeroQuarto + "cadastrado com sucesso";
    }

    @Override
    public List<Reserva> listarReservas() throws RemoteException {
        //Devolve a lista 
        return reservas;
    }

}
