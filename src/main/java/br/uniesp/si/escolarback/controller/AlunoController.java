package br.uniesp.si.escolarback.controller;

import br.uniesp.si.escolarback.dto.AlunoDTO; // Certifique-se que o nome do DTO é AlunoDTO
import br.uniesp.si.escolarback.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/alunos") // Mudado de /funcionario para /alunos
public class AlunoController {

    private final AlunoService service;

    @PostMapping
    public ResponseEntity<AlunoDTO> salvar(@Valid @RequestBody AlunoDTO dto) {
        log.info("Requisição para salvar aluno: {}", dto.getNome());
        return ResponseEntity.ok(service.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listar() {
        log.info("Requisição para listar alunos");
        return ResponseEntity.ok(service.listar()); // Cuidado com o 'L' maiúsculo/minúsculo
    }

    // Adicionei o GET por ID que estava faltando para completar os 5 endpoints!
    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> buscarPorId(@PathVariable Long id) {
        log.info("Requisição para buscar aluno ID: {}", id);
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> atualizar(@PathVariable Long id,
                                              @Valid @RequestBody AlunoDTO dto) {
        try {
            log.info("Requisição para atualizar aluno ID: {}", id);
            AlunoDTO atualizado = service.atualizar(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            log.error("Erro ao atualizar aluno: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            log.info("Requisição para excluir aluno ID: {}", id);
            service.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Erro ao excluir aluno: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}