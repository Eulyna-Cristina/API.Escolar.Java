package br.uniesp.si.escolarback.repository;

import br.uniesp.si.escolarback.model.Aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository
        extends JpaRepository<Aluno, Long> {


}
