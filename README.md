# Sistema Distribuído de Reserva de Quartos de Hotel

 Este projeto implementa um sistema distribuído de reserva de quartos de hotel utilizando Java RMI. O sistema permite que os clientes consultem a disponibilidade de quartos, realizem reservas e as cancelem de forma segura e concorrente.


## Conteúdos

- [Tecnologias Utilizadas](#Tecnologias)
- [Funcionalidades](#Funcionalidades)
- [Arquitetura do Sistema](#Arquitetura)
- [Como Executar](#Como-Executar)
- [Licenças](#Licenças)

## Tecnologias

- Java RMI para comunicação entre cliente e servidor

- Swing para a interface do cliente

- JSON para armazenamento das reservas e quartos

- Biblioteca GSON para manipulação de dados

## Funcionalidades
✅ Consultar a disponibilidade de quartos

✅ Realizar reservas com um ID único

✅ Cancelar reservas apenas com o ID correto e ID do cliente

✅ Controle de concorrência para evitar reservas conflitantes

## Como Executar

1️⃣ Clonar o Repositório

2️⃣ Compilar e Iniciar o Servidor passando o IP da rede como args

3️⃣ Executar o Cliente e informar o IP do servidor para abrir a aplicação

## Arquitetura

O sistema é dividido nas seguintes camadas:

- Cliente: Interface gráfica e controle da comunicação com o servidor via RMI.

- Servidor: Regras de negócio, processamento das reservas, controle de concorrência e persistência dos dados.

- Modelo: Estruturas de dados das entidades (Cliente, Quarto, Reserva, etc.).

- Interface RMI: Interface remota para comunicação entre cliente e servidor.

## Licenças
Este código foi desenvolvido para fins acadêmicos e não está licenciado para uso ou distribuição.
