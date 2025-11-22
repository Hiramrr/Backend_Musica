package miyu.smart.BackendMusica.repository;

import miyu.smart.BackendMusica.entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CancionRepository extends JpaRepository<Cancion, UUID> {
}
