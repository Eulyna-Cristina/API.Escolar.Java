package br.uniesp.si.techback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Mapeando para o banco de dados.
    private Long id1;
    private Long id;
    private String nome;
    private String cargo;
    private int idade;
    private String cpf;
    private LocalDate datanasc;


}
