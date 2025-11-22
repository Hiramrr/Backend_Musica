package miyu.smart.BackendMusica.repository;

import miyu.smart.BackendMusica.entity.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtistaRepository extends JpaRepository<Artista, UUID> {
}
