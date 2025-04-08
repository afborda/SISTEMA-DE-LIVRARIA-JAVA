package main;
/*
O que deve ser feito?

Você deverá criar um loop de interação com o usuário, perguntando se ele gostaria de ver os livros disponíveis

Caso SIM: Ele deverá escolher o id do livro, e após isso inserir o nome, no final, uma mensagem deverá ser
gerada, informando que o livro foi emprestado com sucesso.

Caso NÃO: O programa deverá ser encerrado.

Funcionalidades do Sistema
- ADD model.Livro
- LISTAR Livros
- EMPRESTAR model.Livro
- DEVOLVER model.Livro
- REGISTRAR EMPRESTIMO DE LIVRO

Estrutura do Projeto

  LIVRO
    - **id**: Identificador único do livro
- **titulo**: Título do livro
- **autor**: Autor do livro (objeto do tipo Autor)
- **disponivel**: Indica se o livro está disponível para empréstimo
- dataCadastro: Data que o livro foi cadastrado
- dataAtualização: Data que o livro foi atualizado


### Autor

- **id**: Identificador único do autor
- **nome**: Nome do autor
- dataNascimento: Nascimento do autor

### service.Biblioteca

- **livros**: Lista de livros na biblioteca
- autores: Lista de autores da biblioteca
- emprestimos: Lista de empréstimos da biblioteca



Uma dica para extruturar a  classe:
private List<model.Livro> livros = new ArrayList<>();
private List<Autor> autores = new ArrayList<>();
private List<Emprestimo> emprestimos = new ArrayList<>();

### Gerenciamento de Livros

- Apenas livros marcados como disponíveis podem ser emprestados.
- O usuário deverá poder informar seu nome ao fazer empréstimo do livro

### Empréstimo de Livros

- Ao realizar um empréstimo, o livro escolhido deve ser marcado como indisponível e durante esta execução do programa NÃO
poderá fazer o empréstimo do mesmo livro novamente.


## Indo além

Algumas sugestões do que pode ser implementado:

- Cadastro de Clientes: crie uma classe model.Cliente para representar os usuários da biblioteca, com atributos como id, nome, dataNascimento, e email.

    Implemente uma funcionalidade para listar todos os clientes cadastrados.
    Adicione a capacidade de associar empréstimos aos clientes, permitindo que você veja quais livros um cliente específico emprestou e quando.

    Adicione uma funcionalidade para consultar o histórico de empréstimos de um livro ou cliente específico, mostrando datas de empréstimo e devolução.

- Mantenha um registro de todos os empréstimos, incluindo os devolvidos.
- Implemente funcionalidades para buscar livros por título ou autor.
Adicione filtros para listar apenas livros de determinados gêneros, ou livros que foram adicionados recentemente.
- Indo mais além, você pode adicionar um menu, que ao iniciar o sistema, pergunta ao usuário se ele quer cadastrar um
novo livro, porém para isso, deverá inserir
todos os parâmetros do livro, e após adicionar, o livro ficará disponível para empréstimo.


ESTRUTURA DO PROJETO




 */


import model.Autor;
import model.Cliente;
import model.Livro;
import service.Biblioteca;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        // Dados iniciais para teste
        biblioteca.adicionarAutor(1, "Machado de Assis", LocalDate.of(1839, 6, 21));
        biblioteca.adicionarAutor(2, "Clarice Lispector", LocalDate.of(1920, 12, 10));
        biblioteca.adicionarCliente(1, "João da Silva", LocalDate.of(1990, 5, 15), "joao.silva@verboconnectche.com.br");
        biblioteca.adicionarCliente(2, "Maria dos Pampas", LocalDate.of(1985, 3, 22), "maria.pampas@verboconnectche.com.br");
        biblioteca.adicionarLivro(1, "Dom Casmurro", 1);
        biblioteca.adicionarLivro(2, "A Paixão Segundo G.H.", 2);

        // Loop inicial: Pergunta se o usuário quer ver os livros disponíveis
        System.out.println("Bem-vindo ao Sistema de Gerenciamento de Biblioteca do Verbo Connect Chê 🧉");
        System.out.print("Gostaria de ver os livros disponíveis? (S/N): ");
        String resposta = scanner.nextLine().trim().toUpperCase();

        if (resposta.equals("S")) {
            int opcao = 0;
            while (opcao != 9) {
                System.out.println("\nMenu:");
                System.out.println("1. Adicionar Livro");
                System.out.println("2. Listar Livros");
                System.out.println("3. Listar Livros Disponíveis");
                System.out.println("4. Emprestar Livro");
                System.out.println("5. Devolver Livro");
                System.out.println("6. Registrar Empréstimo de Livro");
                System.out.println("7. Listar Clientes");
                System.out.println("8. Buscar Livro por Título");
                System.out.println("9. Histórico de Empréstimos de um Cliente");
                System.out.println("10. Sair");


                System.out.print("Digite a opção desejada: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer

                try {
                    switch (opcao) {
                        case 1: // Adicionar Livro
                            System.out.print("Digite o ID do livro: ");
                            int idLivro = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Digite o título do livro: ");
                            String titulo = scanner.nextLine();
                            System.out.print("Digite o ID do autor: ");
                            int idAutor = scanner.nextInt();
                            biblioteca.adicionarLivro(idLivro, titulo, idAutor);
                            break;

                        case 2: // Listar Livros
                            biblioteca.listarLivros();
                            break;

                        case 3: // Listar Livros Disponíveis
                            biblioteca.listarLivrosDisponiveis();
                            break;

                        case 4: // Emprestar Livro
                            biblioteca.listarLivrosDisponiveis();
                            System.out.print("Digite o ID do livro para emprestar: ");
                            int livroId = scanner.nextInt();
                            System.out.print("Digite o ID do cliente: ");
                            int clienteId = scanner.nextInt();
                            biblioteca.emprestarLivro(livroId, clienteId);
                            break;

                        case 5: // Devolver Livro
                            System.out.print("Digite o ID do livro para devolver: ");
                            int livroDevolucaoId = scanner.nextInt();
                            biblioteca.devolverLivro(livroDevolucaoId);
                            break;

                        case 6: // Registrar Empréstimo
                            biblioteca.listarLivros();
                            System.out.print("Digite o ID do livro para registrar o empréstimo: ");
                            int livroRegistroId = scanner.nextInt();
                            System.out.print("Digite o ID do cliente: ");
                            int clienteRegistroId = scanner.nextInt();
                            biblioteca.registrarEmprestimo(livroRegistroId, clienteRegistroId);
                            break;

                        case 7: // Listar Clientes
                            biblioteca.listarClientes();
                            break;

                        case 8: // Buscar Livro por Título
                            System.out.print("Digite o título (ou parte dele) para buscar: ");
                            String termo = scanner.nextLine();
                            biblioteca.buscarPorTitulo(termo);
                            break;

                        case 9: // Histórico de Empréstimos de um Cliente
                            System.out.print("Digite o ID do cliente para ver o histórico de empréstimos: ");
                            int clienteHistoricoId = scanner.nextInt();
                            biblioteca.historicoEmprestimosCliente(clienteHistoricoId);
                            break;

                        case 10: // Sair
                            System.out.println("Saindo do sistema. Até logo, tchê! 🧉");
                            break;



                        default:
                            System.out.println("Opção inválida! Escolha entre 1 e 9.");
                    }
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Programa encerrado. Até logo, tchê! 🧉");
        }

        scanner.close();
    }
}