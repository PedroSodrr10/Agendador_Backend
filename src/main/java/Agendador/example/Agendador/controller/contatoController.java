package Agendador.example.Agendador.controller;

import Agendador.example.Agendador.dto.ContatoRequestDTO;
import Agendador.example.Agendador.dto.ContatoResponseDTO;
import Agendador.example.Agendador.service.contatoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contatos")
public class contatoController {

    private final contatoService service;

    public contatoController(contatoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ContatoResponseDTO> criar(@Valid @RequestBody ContatoRequestDTO dto) {
        return ResponseEntity.status(201).body(service.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoResponseDTO> editar(@PathVariable Long id, @Valid @RequestBody ContatoRequestDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @GetMapping
    public List<ContatoResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContatoResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
