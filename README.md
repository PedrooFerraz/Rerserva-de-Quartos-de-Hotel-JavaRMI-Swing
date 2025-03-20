# Sistema Distribuído de Reserva de Quartos de Hotel

 Este projeto implementa um sistema distribuído de reserva de quartos de hotel utilizando Java RMI. O sistema permite que os clientes consultem a disponibilidade de quartos, realizem reservas e as cancelem de forma segura e concorrente.


## Conteúdos

- [Tecnologias Utilizadas](#Tecnologias)
- [Funcionalidades](#Funcionalidades)
- [Requisitos](#Requisitos)
- [Arquitetura do Sistema](#Arquitetura)
- [Licenças](#Licenças)

## Tecnologias

- Java RMI para comunicação entre cliente e servidor

- Swing ou JavaFX para a interface do cliente

- JSON/XML/TXT para armazenamento das reservas e quartos

- Java Collections para manipulação de dados temporária

## Funcionalidades
✅ Consultar a disponibilidade de quartos

✅ Realizar reservas com um ID único

✅ Cancelar reservas apenas com o ID correto e ID do cliente

✅ Controle de concorrência para evitar reservas conflitantes

## Como Executar

1️⃣ Clonar o Repositório

2️⃣ Compilar e Iniciar o Servidor

3️⃣ Executar o Cliente

## Arquitetura

O sistema é dividido nas seguintes camadas:

- Cliente: Interface gráfica e controle da comunicação com o servidor via RMI.

- Servidor: Regras de negócio, processamento das reservas, controle de concorrência e persistência dos dados.

- Modelo: Estruturas de dados das entidades (Cliente, Quarto, Reserva, etc.).

- Interface RMI: Interface remota para comunicação entre cliente e servidor.

## Licenças
Este código foi desenvolvido para fins acadêmicos e não está licenciado para uso ou distribuição.
