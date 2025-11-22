package miyu.smart.BackendMusica.repository;

import miyu.smart.BackendMusica.entity.Reseña;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReseñaRepository extends JpaRepository<Reseña, UUID> {
}
