package miyu.smart.BackendMusica.repository;

import miyu.smart.BackendMusica.entity.Reseña;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReseñaRepository extends JpaRepository<Reseña, UUID> {
    List<Reseña> findByCancionId(UUID cancionId);
    
    List<Reseña> findByAlbumId(UUID albumId);
}