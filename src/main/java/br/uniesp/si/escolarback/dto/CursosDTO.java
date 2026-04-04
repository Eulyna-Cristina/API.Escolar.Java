package br.uniesp.si.escolarback.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CursosDTO {

    private Long id;

    @NotBlank(message = "O nome do curso é obrigatório")
    private String nome;

    @Positive(message = "A data de início do curso é obrigatória")
    private LocalDate dataInicio;

    @Positive(message = " A data final do curso é obrigatória")
    private LocalDate dataFinal;

    @Positive(message = "A duração do curso é obrigatória")
    private Integer duracaoPeriodo;

    @NotBlank(message = "Forma de pagamento é obrigatória")
    private String formaPagamento;

}




