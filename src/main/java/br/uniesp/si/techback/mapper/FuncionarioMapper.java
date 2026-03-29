package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.FuncionarioDTO;
import br.uniesp.si.techback.model.Funcionario;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioMapper {

    public Funcionario toEntity(FuncionarioDTO dto) {
        if (dto == null) {
            return null;

        }
        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.getId());
        funcionario.setNome(dto.getNome());
        funcionario.setIdade(dto.getIdade());
        funcionario.setCpf(dto.getCpf());
        funcionario.setDatanasc(dto.getDataNascimento());
        funcionario.setCargo(dto.getCargo());

        return funcionario;


    }

    public FuncionarioDTO toDTO(Funcionario entity) {
        if (entity == null) {

            return null;
        }

        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setIdade(entity.getIdade());
        dto.setCpf(entity.getCpf());
        dto.setDataNascimento(entity.getDatanasc());
        dto.setCargo(entity.getCargo());

        return dto;


    }

}

