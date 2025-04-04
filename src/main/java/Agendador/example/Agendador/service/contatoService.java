package Agendador.example.Agendador.service;

import Agendador.example.Agendador.dto.ContatoRequestDTO;
import Agendador.example.Agendador.dto.ContatoResponseDTO;
import Agendador.example.Agendador.entidades.Contato;
import Agendador.example.Agendador.repository.contatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class contatoService {

    private final contatoRepository repository;

    public contatoService(contatoRepository repository) {
        this.repository = repository;
    }

    public ContatoResponseDTO criar(ContatoRequestDTO dto) {
        Contato contato = dtoToEntity(dto);
        return new ContatoResponseDTO(repository.save(contato));
    }

    public ContatoResponseDTO editar(Long id, ContatoRequestDTO dto) {
        Contato contato = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado"));

        contato.setNome(dto.getNome());
        contato.setCpf(dto.getCpf());
        contato.setEmail(dto.getEmail());
        contato.setTelefone(dto.getTelefone());
        contato.setTipoPessoa(dto.getTipoPessoa());
        contato.setEndereco(dto.getEndereco().toEntity());

        return new ContatoResponseDTO(repository.save(contato));
    }

    public List<ContatoResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(ContatoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<ContatoResponseDTO> buscarPorId(Long id) {
        return repository.findById(id).map(ContatoResponseDTO::new);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    private Contato dtoToEntity(ContatoRequestDTO dto) {
        Contato contato = new Contato();
        contato.setNome(dto.getNome());
        contato.setCpf(dto.getCpf());
        contato.setEmail(dto.getEmail());
        contato.setTelefone(dto.getTelefone());
        contato.setTipoPessoa(dto.getTipoPessoa());
        contato.setEndereco(dto.getEndereco().toEntity());
        return contato;
    }
}

