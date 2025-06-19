package com.neontech.sistema_escolar.dto;

import com.neontech.sistema_escolar.model.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaDTO {
    private Long id;
    private PessoaDTO aluno;
    private CursoDTO curso;
    private LocalDate dataMatricula;
    private BigDecimal valorCobrado;
    private StatusPagamento statusPagamento;
    private LocalDate dataVencimento;
}
