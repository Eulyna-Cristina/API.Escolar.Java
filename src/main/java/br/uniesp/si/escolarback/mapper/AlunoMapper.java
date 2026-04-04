package br.uniesp.si.escolarback.mapper;

import br.uniesp.si.escolarback.dto.AlunoDTO;
import br.uniesp.si.escolarback.model.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public Aluno toEntity(AlunoDTO dto) {
        if (dto == null) {
            return null;

        }
        Aluno aluno = new Aluno();
        aluno.setId(dto.getId());
        aluno.setNome(dto.getNome());
        aluno.setCurso(dto.getCursos());
        aluno.setIdade(dto.getIdade());
        aluno.setCpf(dto.getCpf());
        aluno.setDatanasc(dto.getDatanasc());
        aluno.setEndereco(dto.getEndereco());
        aluno.setEmail(dto.getEmail());


        return aluno;


    }

    public AlunoDTO toDTO(Aluno entity) {
        if (entity == null) {

            return null;
        }

        AlunoDTO dto = new AlunoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCursos(entity.getCurso());
        dto.setIdade(entity.getIdade());
        dto.setCpf(entity.getCpf());
        dto.setDatanasc(entity.getDatanasc());
        dto.setEndereco(entity.getEndereco());
        dto.setEmail(entity.getEmail());

        return dto;


    }

}

