package miyu.smart.BackendMusica.repository;

import miyu.smart.BackendMusica.entity.Album;
import miyu.smart.BackendMusica.entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlbumRepository extends JpaRepository<Album, UUID> {
    Optional<Album> findByArtistasId(UUID artistaID);
}
