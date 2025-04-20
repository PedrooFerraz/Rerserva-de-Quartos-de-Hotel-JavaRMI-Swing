/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.model;

import java.math.BigDecimal;
import java.io.Serializable;

public class Quarto implements Serializable {

    private int numero;
    private BigDecimal valorDiaria;
    private int tipo; // 1 - Simples, 2 - Duplo, etc.

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

    public String toString() {
        String tipoStr;

        switch (tipo) {
            case 1:
                tipoStr = "Simples";
                break;
            case 2:
                tipoStr = "Duplo";
                break;
            case 3:
                tipoStr = "Suíte";
                break;
            default:
                tipoStr = "Desconhecido";
                break;
        }

        return "Quarto Nº " + numero
                + " | Tipo: " + tipoStr
                + " | Diária: R$ " + valorDiaria;
    }
}
