/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.model;

import java.io.Serializable;
import java.util.Date;

public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Date dataEntrada;
    private Date dataSaida;
    private String cpf;
    private int quarto;

    public Reserva(Long id, Date dataEntrada, Date dataSaida, String cpf, int quarto) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.cpf = cpf;
        this.quarto = quarto;
    }

    public Long getId() {
        return id;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public String getCpf() {
        return cpf;
    }

    public int getQuarto() {
        return quarto;
    }

    public String getCpfCliente() {
        return cpf;
    }

    @Override
    public String toString() {
        return "Reserva ID: " + id
                + ", CPF: " + cpf
                + ", Quarto: " + quarto
                + ", Data Entrada: " + dataEntrada
                + ", Data Saída: " + dataSaida;
    }
}
