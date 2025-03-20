package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;
import server.model.Reserva;

/**
 *
 * @author Pedro Henrique
 */
public interface ReservaHotel extends Remote {

    //Retorna uma List com todos os quartos disponiveis com base na Data que o usu[ario passar como parâmetro.
    List consultarQuartosDisponiveis(LocalDate dataEntrada, LocalDate dataSaida) throws RemoteException;

    //Retorna un objeto "RespostaReserva" onde a minha ideia è ele ter no minimo 2 atributos, que seriam um int IdReserva e um bool caso a 
    // operação seja um sucesso ou não.
    RespostaReserva realizarReserva(int idCliente, String nomeCliente, String tipoQuarto, LocalDate dataEntrada, LocalDate dataSaida) throws RemoteException;

    //Método onde passamos um idReserva e um idCliente e nos retorna un valor boolean dizendo se a operação foi um sucesso ou não.
    Boolean cancelarReserva (int idCliente, int idReserva) throws RemoteException;
    
    //Método que retorna o objeto "Reserva com base no idReserva e no IdCliente passado como parâmetro.
    Reserva consultarReserva (int idCliente, int idReserva) throws RemoteException;
}
