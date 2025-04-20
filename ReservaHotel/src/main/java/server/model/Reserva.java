/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.model;

import java.util.Date;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Reserva implements Serializable {

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

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return "Reserva ID: " + id
                + " | Entrada: " + sdf.format(dataEntrada)
                + " | Saída: " + sdf.format(dataSaida)
                + " | CPF: " + cpf
                + " | Quarto Nº: " + quarto;
    }
}
