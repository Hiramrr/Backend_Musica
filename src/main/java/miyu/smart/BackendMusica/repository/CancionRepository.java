package miyu.smart.BackendMusica.repository;

import miyu.smart.BackendMusica.dto.CancionResumen;
import miyu.smart.BackendMusica.entity.Cancion;
import miyu.smart.BackendMusica.entity.Reseña;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, UUID> {
    Optional<Cancion> findByArtistasId(UUID artistaID);
    @Query(value = """
        SELECT 
            c.nombre AS nombre, 
            c.descripcion AS descripcion,
            c.duracion_segundos AS duracionSegundos,
            c.fecha_salida AS fechaSalida,
            c.calificacion AS calificacion,
            c.portada_url AS portadaUrl,
            
            alb.nombre AS nombreAlbum,
            
            STRING_AGG(art.nombre, ', ') AS nombreArtista
        FROM cancion c
        LEFT JOIN album alb ON c.id_album = alb.id
        LEFT JOIN cancion_artistas rel ON c.id = rel.id_cancion
        LEFT JOIN artista art ON rel.id_artista = art.id
        GROUP BY c.id, alb.nombre
        """, nativeQuery = true)
    List<CancionResumen> obtenerResumenesNativos();
}
