package miyu.smart.BackendMusica.repository;

import miyu.smart.BackendMusica.entity.Reseña;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReseñaRepository extends JpaRepository<Reseña, UUID> {
    // Spring entiende: "Busca por la propiedad 'cancion', y dentro de ella por 'id'"
    List<Reseña> findByCancion_Id(UUID cancionId);
    // Spring entiende: "Busca por la propiedad 'album', y dentro de ella por 'id'"
    List<Reseña> findByAlbum_Id(UUID albumId);
}