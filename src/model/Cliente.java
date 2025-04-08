package model;

import java.time.LocalDate;

public  record Cliente(int id, String nome, LocalDate dataNascimento, String email) {
}