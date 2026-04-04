package br.uniesp.si.escolarback.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data

@NoArgsConstructor

@AllArgsConstructor

public class AlunoDTO {

    private Long id;

    @NotBlank(message = "O nome do aluno é obrigatório")

    private String nome;

    private String cursos;

    private LocalDate datanasc;

    private int idade;

    private String cpf;

    private String email;

    private String endereco;

}
