package service;


import model.Autor;
import model.Cliente;
import model.Emprestimos;
import model.Livro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {

    private List<Livro> livros = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private List<Emprestimos> emprestimos = new ArrayList<>();


    public void adicionarAutor(int id, String nome, LocalDate dataNascimento) {
        autores.add(new Autor(id, nome, dataNascimento));
        System.out.println("Autor " + nome + " adicionado com sucesso!");
    }

    public void adicionarCliente(int id, String nome, LocalDate dataNascimento, String email) {
        clientes.add(new Cliente(id, nome, dataNascimento, email));
        System.out.println("Cliente " + nome + " adicionado com sucesso!");
    }

    public void adicionarLivro(int id, String titulo, int autorID) {
        Autor autor = autores.stream().filter(a -> a.id() == autorID).findFirst().orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));
        Livro livro = new Livro(id, titulo, autor, true, LocalDate.now(), LocalDate.now());
        livros.add(livro);
        System.out.println("Livro " + titulo + " adicionado com sucesso!");
    }

    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Não há livros cadastrados");
        } else {
            System.out.println("Lista de livros:");
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    public void listaLivrosDisponiveis() {
        List<Livro> disponiveis = livros.stream().filter(Livro::disponivel).collect(Collectors.toList());
        if (disponiveis.isEmpty()) {
            System.out.println("Não há livros disponíveis");
        } else {
            System.out.println("Lista de livros disponíveis:");
            for (Livro livro : disponiveis) {
                System.out.println(livro);
            }
        }
    }

    public void emprestarLivro(int livroID, int clienteID) {
        Livro livro = livros.stream()
                .filter(l -> l.id() == livroID)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));
        Cliente cliente = clientes.stream().filter(c -> c.id() == clienteID)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Livro livroAtualizado = new Livro(livro.id(), livro.titulo(), livro.autor(), false, livro.dataCadastro(), LocalDate.now());
        livros.remove(livro);
        livros.add(livroAtualizado);
        Emprestimos emprestimo = new Emprestimos(livro, cliente, LocalDate.now(), LocalDate.now().plusDays(7));
        emprestimos.add(emprestimo);
        System.out.println("Livro " + livro.titulo() + " emprestado com sucesso para " + cliente.nome());

    }

    public void devolverLivro(int livroID) {
        Livro livro = livros.stream()
                .filter(l -> l.id() == livroID)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));
        Livro livroAtualizado = new Livro(livro.id(), livro.titulo(), livro.autor(), true, livro.dataCadastro(), LocalDate.now());
        livros.remove(livro);
        livros.add(livroAtualizado);
        System.out.println("Livro " + livro.titulo() + " devolvido com sucesso!");
    }

    public void registrarEmprestimo(int livroID, int clienteID) {
        Livro livro = livros.stream()
                .filter(l -> l.id() == livroID)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));
        Cliente cliente = clientes.stream().filter(c -> c.id() == clienteID)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Emprestimos emprestimo = new Emprestimos(livro, cliente, LocalDate.now(), LocalDate.now().plusDays(7));
        emprestimos.add(emprestimo);
        System.out.println("Empréstimo registrado com sucesso!");
    }

    public void listarEmprestimos() {
        if (emprestimos.isEmpty()) {
            System.out.println("Não há empréstimos registrados.");
        } else {
            System.out.println("Lista de empréstimos:");
            for (Emprestimos emprestimo : emprestimos) {
                System.out.println(emprestimo);
            }
        }
    }

    public void listarLivrosDisponiveis() {
        List<Livro> livrosDisponiveis = livros.stream()
                .filter(Livro::disponivel)
                .collect(Collectors.toList());

        if (livrosDisponiveis.isEmpty()) {
            System.out.println("Não há livros disponíveis.");
        } else {
            System.out.println("Lista de livros disponíveis:");
            for (Livro livro : livrosDisponiveis) {
                System.out.println(livro);
            }
        }


    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("Lista de Clientes:");
            for (Cliente cliente : clientes) {
                System.out.println("ID: " + cliente.id() + " | Nome: " + cliente.nome() +
                        " | Email: " + cliente.email());
            }
        }
    }

    public void buscarPorTitulo(String titulo) {
        List<Livro> resultados = livros.stream()
                .filter(l -> l.titulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
        if (resultados.isEmpty()) {
            System.out.println("Nenhum livro encontrado com o título '" + titulo + "'.");
        } else {
            System.out.println("Livros encontrados:");
            for (Livro livro : resultados) {
                System.out.println("ID: " + livro.id() + " | Título: " + livro.titulo() +
                        " | Autor: " + livro.autor().nome());
            }
        }
    }

    public void historicoEmprestimosCliente(int clienteId) {
        Cliente cliente = clientes.stream()
                .filter(c -> c.id() == clienteId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado!"));

        List<Emprestimos> historico = emprestimos.stream()
                .filter(e -> e.cliente().id() == clienteId)
                .collect(Collectors.toList());

        if (historico.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado para " + cliente.nome() + ".");
        } else {
            System.out.println("Histórico de Empréstimos de " + cliente.nome() + ":");
            for (Emprestimos e : historico) {
                System.out.println("Livro: " + e.livro().titulo() +
                        " | Data Empréstimo: " + e.dataEmprestimo() +
                        " | Data Devolução: " + (e.dataDevolucao() != null ? e.dataDevolucao() : "Não devolvido"));
            }
        }
    }


}
