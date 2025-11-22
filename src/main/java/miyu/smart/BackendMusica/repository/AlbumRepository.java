package miyu.smart.BackendMusica.repository;

import miyu.smart.BackendMusica.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, UUID> {
}
