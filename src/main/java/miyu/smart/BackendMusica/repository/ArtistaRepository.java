package miyu.smart.BackendMusica.repository;

import miyu.smart.BackendMusica.entity.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, UUID> {
    List<Artista> findByCancionesId(UUID cancionId);

    List<Artista> findByAlbumsId(UUID albumsId);

}
