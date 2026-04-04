package br.uniesp.si.escolarback.controller;

import br.uniesp.si.escolarback.model.Cursos;
import br.uniesp.si.escolarback.service.CursosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cursos") // Alterado de /filmes para /cursos
@RequiredArgsConstructor
@Slf4j
public class CursosController { // Alterado de AlunoController para CursoController

    private final CursosService cursosService; // Nome da variável corrigido

    @GetMapping
    public List<Cursos> listar() {
        log.info("Listando todos os cursos");
        return cursosService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cursos> buscarPorId(@PathVariable Long id) {
        try {
            Cursos curso = cursosService.buscarPorId(id);
            return ResponseEntity.ok(curso);
        } catch (Exception e) {
            log.error("Erro ao buscar curso com ID {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cursos> criar(@Valid @RequestBody Cursos curso) {
        log.info("Criando novo curso: {}", curso.getNome()); // Supondo que Cursos tem getNome()
        try {
            Cursos cursoSalvo = cursosService.salvar(curso);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(cursoSalvo.getId())
                    .toUri();

            return ResponseEntity.created(location).body(cursoSalvo);
        } catch (Exception e) {
            log.error("Erro ao criar curso: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cursos> atualizar(@PathVariable Long id, @Valid @RequestBody Cursos curso) {
        log.info("Atualizando curso com ID {}", id);
        try {
            Cursos cursoAtualizado = cursosService.atualizar(id, curso);
            return ResponseEntity.ok(cursoAtualizado);
        } catch (Exception e) {
            log.error("Erro ao atualizar curso ID {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        log.info("Excluindo curso com ID: {}", id);
        try {
            cursosService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Erro ao excluir curso com ID {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}