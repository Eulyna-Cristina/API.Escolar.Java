package br.uniesp.si.escolarback.mapper;

import br.uniesp.si.escolarback.dto.AlunoDTO;
import br.uniesp.si.escolarback.model.Cursos;
import org.springframework.stereotype.Component;

@Component
public class CursosMapper {

    public Cursos toEntity(AlunoDTO dto) {
        if (dto == null) {
            return null;
        }

        Cursos filme = new Cursos();
        filme.setId(dto.getId());
        filme.setTitulo(dto.getTitulo());
        filme.setSinopse(dto.getSinopse());
        filme.setDataLancamento(dto.getDataLancamento());
        filme.setGenero(dto.getGenero());
        filme.setDuracaoMinutos(dto.getDuracaoMinutos());
        filme.setClassificacaoIndicativa(dto.getClassificacaoIndicativa());

        return filme;
    }

    public AlunoDTO toDTO(Cursos entity) {
        if (entity == null) {
            return null;
        }

        AlunoDTO dto = new AlunoDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setSinopse(entity.getSinopse());
        dto.setDataLancamento(entity.getDataLancamento());
        dto.setGenero(entity.getGenero());
        dto.setDuracaoMinutos(entity.getDuracaoMinutos());
        dto.setClassificacaoIndicativa(entity.getClassificacaoIndicativa());

        return dto;
    }
}