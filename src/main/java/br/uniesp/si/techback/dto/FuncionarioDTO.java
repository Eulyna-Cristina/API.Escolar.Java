package br.uniesp.si.techback.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FuncionarioDTO {

    private Long id;

    @NotBlank(message = "O nome do funcionário é obrigatório")
    private String nome;

    @Positive(message = "A idade deve ser um valor positivo")
    private int idade;

    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    private LocalDate dataNascimento;

    @NotBlank(message = "O cargo é obrigatório")
    private String cargo;

}




