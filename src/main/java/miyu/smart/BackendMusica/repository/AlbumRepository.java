package miyu.smart.BackendMusica.repository;

import miyu.smart.BackendMusica.dto.AlbumResumen;
import miyu.smart.BackendMusica.entity.Album;
import miyu.smart.BackendMusica.entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlbumRepository extends JpaRepository<Album, UUID> {
    Optional<Album> findByArtistasId(UUID artistaID);
    @Query(value = """
            SELECT 
                CAST(a.id AS varchar) AS id, 
                a.nombre AS nombre, 
                a.fecha_salida AS fechaSalida, 
                a.descripcion AS descripcion, 
                a.portada_url AS portadaUrl,
                STRING_AGG(ar.nombre, ', ') AS nombreArtista 
            FROM album a
            LEFT JOIN album_artistas rel ON a.id = rel.id_album
            LEFT JOIN artista ar ON rel.id_artista = ar.id
            GROUP BY a.id, a.nombre, a.fecha_salida, a.descripcion, a.portada_url
            """, nativeQuery = true)
    List<AlbumResumen> obtenerResumenesNativos(); 

    @Query("SELECT a FROM Album a " + 
           "LEFT JOIN FETCH a.artistas " + 
           "WHERE a.id = :id")
    Optional<Album> buscarPorIdConDetalles(@Param("id") UUID id);

    List<Album> findByArtistas_Id(UUID artistaId);
}
