package miyu.smart.BackendMusica.repository;

import miyu.smart.BackendMusica.entity.Cancion;
import miyu.smart.BackendMusica.entity.Reseña;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, UUID> {
    Optional<Cancion> findByArtistasID(UUID artistaID);
}
