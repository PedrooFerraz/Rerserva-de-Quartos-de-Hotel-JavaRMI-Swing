/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package client;

import client.controller.ClientController;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import server.model.Quarto;


/**
 *
 * @author Pedro Henrique
 */
public class HotelClientMain {


    public static void main(String[] args) {
        String Ip = (args.length > 0) ? args[0] : "127.0.0.1";
        
        ClientController controller = new ClientController(Ip);
        
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        while (true) {
            System.out.println("\n--- Sistema de Reserva de Hotel ---");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Cadastrar quarto");
            System.out.println("3. Consultar quartos disponiveis em uma data");
            System.out.println("4. Fazer reserva");
            System.out.println("5. Buscar reserva por CPF");
            System.out.println("6. Cancelar reserva por ID");
            System.out.println("7. Listar todos os quartos");
            System.out.println("8. Listar todas as reservas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.println(controller.getService().cadastrar(nome, cpf));
                        break;
                    case 2:
                        System.out.print("Número do quarto: ");
                        int numero = scanner.nextInt();
                        System.out.print("Valor da diária: ");
                        BigDecimal valor = scanner.nextBigDecimal();
                        System.out.print("Tipo (1 = Standard, 2 = Luxo...): ");
                        int tipo = scanner.nextInt();
                        System.out.println(controller.getService().cadastrarQuarto(numero, valor, tipo));
                        break;
                    case 3:
                        System.out.print("Data (dd/MM/yyyy): ");
                        String dataStr = scanner.nextLine();
                        Date data = sdf.parse(dataStr);
                        List<Quarto> disponiveis = controller.getService().consultarQuartosDisponveis(data);
                        System.out.println("Quartos disponíveis:");
                        for (Quarto q : disponiveis) {
                            System.out.println(q);
                        }
                        break;
                    case 4:
                        System.out.print("CPF do cliente: ");
                        String cpfReserva = scanner.nextLine();
                        System.out.print("Data de entrada (dd/MM/yyyy): ");
                        Date entrada = sdf.parse(scanner.nextLine());
                        System.out.print("Data de saída (dd/MM/yyyy): ");
                        Date saida = sdf.parse(scanner.nextLine());
                        System.out.print("Número do quarto: ");
                        int numQuarto = scanner.nextInt();
                        System.out.println(controller.getService().fazerReserva(entrada, saida, cpfReserva, numQuarto));
                        break;
                    case 5:
                        System.out.print("CPF: ");
                        String cpfBusca = scanner.nextLine();
                        System.out.println(controller.getService().buscarReserva(cpfBusca));
                        break;
                    case 6:
                        System.out.print("ID da reserva: ");
                        Long id = scanner.nextLong();
                        System.out.println(controller.getService().cancelarReserva(id));
                        break;
                    case 7:
                        List<Quarto> quartos = controller.getService().listarQuartosDisponiveis();
                        System.out.println("Todos os quartos:");
                        for (Quarto q : quartos) {
                            System.out.println(q);
                        }
                        break;
                    case 8:
                        var reservas = controller.getService().listarReservas();
                        System.out.println("Todas as reservas:");
                        for (var r : reservas) {
                            System.out.println(r);
                        }
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        
        
    }
    
}
