package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.FuncionarioDTO;
import br.uniesp.si.techback.mapper.FuncionarioMapper;
import br.uniesp.si.techback.model.Funcionario;
import br.uniesp.si.techback.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FuncionarioService {

    private final FuncionarioRepository repository;
    private final FuncionarioMapper mapper;


    @Transactional
    public FuncionarioDTO salvar(FuncionarioDTO dto) {
        log.info("Salvando novo funcionário: {}", dto.getNome());


        Funcionario entidade = mapper.toEntity(dto);


        Funcionario salvo = repository.save(entidade);


        return mapper.toDTO(salvo);
    }


    public List<FuncionarioDTO> Listar() {
        log.info("Buscando todos os funcionários");
        List<Funcionario> funcionarios = repository.findAll();


        return funcionarios.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }


    @Transactional
    public FuncionarioDTO atualizar(Long id, FuncionarioDTO dto) {
        log.info("Tentando atualizar funcionário ID: {}", id);

        return repository.findById(id)
                .map(existente -> {
                    Funcionario paraAtualizar = mapper.toEntity(dto);
                    paraAtualizar.setId(id); // Garante que o ID não mude na atualização
                    Funcionario atualizado = repository.save(paraAtualizar);
                    return mapper.toDTO(atualizado);
                })
                .orElseThrow(() -> {
                    log.warn("Falha ao atualizar: ID {} não encontrado", id);
                    return new RuntimeException("Funcionário inexistente");
                });
    }


    @Transactional
    public void excluir(Long id) {
        log.info("Excluindo funcionário ID: {}", id);
        if (!repository.existsById(id)) {
            throw new RuntimeException("ID not found");
        }
        repository.deleteById(id);
    }
}