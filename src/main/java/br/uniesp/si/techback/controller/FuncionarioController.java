package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.FuncionarioDTO;
import br.uniesp.si.techback.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService service;

    @PostMapping
    public ResponseEntity<FuncionarioDTO> salvar(@Valid @RequestBody FuncionarioDTO dto) {
        log.info("Requisição para salvar funcionário: {}", dto.getNome());
        return ResponseEntity.ok(service.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> listar() {
        log.info("Requisição para listar funcionários");
        return ResponseEntity.ok(service.Listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> atualizar(@PathVariable Long id,
                                                    @Valid @RequestBody FuncionarioDTO dto) {
        try {
            log.info("Requisição para atualizar funcionário ID: {}", id);
            FuncionarioDTO atualizado = service.atualizar(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            log.error("Erro ao atualizar Funcionário: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            log.info("Requisição para excluir funcionário ID: {}", id);
            service.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Erro ao excluir Funcionário : {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}