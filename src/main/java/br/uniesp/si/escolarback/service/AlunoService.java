package br.uniesp.si.escolarback.service;

import br.uniesp.si.escolarback.dto.AlunoDTO; // Alterado de CursosDTO para AlunoDTO
import br.uniesp.si.escolarback.mapper.AlunoMapper;
import br.uniesp.si.escolarback.model.Aluno;
import br.uniesp.si.escolarback.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlunoService {

    private final AlunoRepository repository;
    private final AlunoMapper mapper;

    @Transactional
    public AlunoDTO salvar(AlunoDTO dto) {
        log.info("Salvando novo aluno: {}", dto.getNome());
        Aluno entidade = mapper.toEntity(dto);
        Aluno salvo = repository.save(entidade);
        return mapper.toDTO(salvo);
    }

    public List<AlunoDTO> listar() { // 'l' minúsculo para seguir o padrão Java
        log.info("Buscando todos os alunos");
        List<Aluno> alunos = repository.findAll();
        return alunos.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    // ADICIONADO: Método necessário para o GET por ID
    public AlunoDTO buscarPorId(Long id) {
        log.info("Buscando aluno por ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    @Transactional
    public AlunoDTO atualizar(Long id, AlunoDTO dto) {
        log.info("Tentando atualizar aluno ID: {}", id);
        return repository.findById(id)
                .map(existente -> {
                    Aluno paraAtualizar = mapper.toEntity(dto);
                    paraAtualizar.setId(id);
                    Aluno atualizado = repository.save(paraAtualizar);
                    return mapper.toDTO(atualizado);
                })
                .orElseThrow(() -> new RuntimeException("Aluno inexistente"));
    }

    @Transactional
    public void excluir(Long id) {
        log.info("Excluindo aluno ID: {}", id);
        if (!repository.existsById(id)) {
            throw new RuntimeException("ID não encontrado");
        }
        repository.deleteById(id);
    }
}