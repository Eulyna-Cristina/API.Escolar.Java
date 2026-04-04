package br.uniesp.si.escolarback.service;

import br.uniesp.si.escolarback.model.Cursos;
import br.uniesp.si.escolarback.repository.CursosRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CursosService {

    private final CursosRepository cursosRepository;

    public List<Cursos> listar() {
        log.info("Buscando todos os cursos cadastrados");
        try {
            List<Cursos> cursos = cursosRepository.findAll();
            log.debug("Total de cursos encontrados: {}", cursos.size());
            return cursos;
        } catch (Exception e) {
            log.error("Falha ao buscar cursos: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Cursos buscarPorId(Long id) {
        log.info("Buscando curso pelo ID: {}", id);
        return cursosRepository.findById(id)
                .map(curso -> {
                    log.debug("Curso encontrado: ID={}, Nome={}", curso.getId(), curso.getNome());
                    return curso;
                })
                .orElseThrow(() -> {
                    String mensagem = String.format("Curso não encontrado com o ID: %d", id);
                    log.warn(mensagem);
                    return new RuntimeException(mensagem);
                });
    }

    @Transactional
    public Cursos atualizar(Long id, Cursos cursoNovosDados) {
        log.info("Atualizando curso ID: {}", id);
        return cursosRepository.findById(id)
                .map(cursoExistente -> {
                    log.debug("Dados atuais do curso: {}", cursoExistente);
                    log.debug("Novos dados recebidos: {}", cursoNovosDados);

                    // Atualizando os campos da sua Model
                    cursoExistente.setNome(cursoNovosDados.getNome());
                    cursoExistente.setDataInicio(cursoNovosDados.getDataInicio());
                    cursoExistente.setDataFinal(cursoNovosDados.getDataFinal());
                    cursoExistente.setDuracaoPeriodo(cursoNovosDados.getDuracaoPeriodo());
                    cursoExistente.setFormaPagamento(cursoNovosDados.getFormaPagamento());

                    Cursos cursoAtualizado = cursosRepository.save(cursoExistente);
                    log.info("Curso ID: {} atualizado com sucesso. Novo nome: {}", id, cursoAtualizado.getNome());
                    return cursoAtualizado;
                })
                .orElseThrow(() -> {
                    String mensagem = String.format("Falha ao atualizar: curso não encontrado com o ID: %d", id);
                    log.warn(mensagem);
                    return new RuntimeException(mensagem);
                });
    }

    @Transactional
    public Cursos salvar(Cursos curso) {
        log.info("Salvando novo curso: {}", curso.getNome());
        try {
            Cursos cursoSalvo = cursosRepository.save(curso);
            log.info("Curso salvo com sucesso. ID: {}, Nome: {}", cursoSalvo.getId(), cursoSalvo.getNome());
            return cursoSalvo;
        } catch (Exception e) {
            log.error("Falha ao salvar curso '{}': {}", curso.getNome(), e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public void excluir(Long id) {
        log.info("Excluindo curso ID: {}", id);
        if (!cursosRepository.existsById(id)) {
            String mensagem = String.format("Falha ao excluir: curso não encontrado com o ID: %d", id);
            log.warn(mensagem);
            throw new RuntimeException(mensagem);
        }
        try {
            cursosRepository.deleteById(id);
            log.info("Curso ID: {} excluído com sucesso", id);
        } catch (Exception e) {
            log.error("Erro ao excluir curso ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }
}