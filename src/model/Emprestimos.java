package model;

import java.time.LocalDate;

public  record Emprestimos(Livro livro, Cliente cliente, LocalDate dataEmprestimo, LocalDate dataDevolucao) {

}