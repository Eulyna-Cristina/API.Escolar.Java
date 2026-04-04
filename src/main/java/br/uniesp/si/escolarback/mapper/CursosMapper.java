package br.uniesp.si.escolarback.mapper;

import br.uniesp.si.escolarback.dto.CursosDTO;
import br.uniesp.si.escolarback.model.Cursos;
import org.springframework.stereotype.Component;

@Component
public class CursosMapper {


    public Cursos toEntity(CursosDTO dto) {
        if (dto == null) {
            return null;
        }

        Cursos cursos = new Cursos();
        cursos.setId(dto.getId());
        cursos.setNome(dto.getNome());
        cursos.setDataInicio(dto.getDataInicio());
        cursos.setDataFinal(dto.getDataFinal());
        cursos.setDuracaoPeriodo(dto.getDuracaoPeriodo());
        cursos.setFormaPagamento(dto.getFormaPagamento());

        return cursos;
    }


    public CursosDTO toDTO(Cursos entity) {
        if (entity == null) {
            return null;
        }

        CursosDTO dto = new CursosDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDataInicio(entity.getDataInicio());
        dto.setDataFinal(entity.getDataFinal());
        dto.setDuracaoPeriodo(entity.getDuracaoPeriodo());
        dto.setFormaPagamento(entity.getFormaPagamento());

        return dto;
    }
}