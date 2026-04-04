package br.uniesp.si.escolarback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cursos")
public class Cursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "data_Inicio")
    private LocalDate dataInicio;

    @Column(name = "data_Final")
    private LocalDate dataFinal;

    @Column(name = "duracao_Periodo")
    private Integer duracaoPeriodo;

    @Column(name = "Forma_Pagamento")
    private String formaPagamento;




}