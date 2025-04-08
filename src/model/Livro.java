package model;

import java.time.LocalDate;

public  record Livro(int id, String titulo, Autor autor, boolean disponivel, LocalDate dataCadastro, LocalDate dataAtualização){

}