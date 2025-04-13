/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Quarto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int numero;
    private BigDecimal valorDiaria;
    private int tipo;

    public Quarto(int numero, BigDecimal valorDiaria, int tipo) {
        this.numero = numero;
        this.valorDiaria = valorDiaria;
        this.tipo = tipo;
    }

    public int getNumero() {
        return numero;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public int getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Quarto #" + numero + " - R$" + valorDiaria + " - Tipo: " + tipo;
    }
}
