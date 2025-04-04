package Agendador.example.Agendador.repository;

import Agendador.example.Agendador.entidades.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface contatoRepository extends JpaRepository<Contato, Long> {
}
