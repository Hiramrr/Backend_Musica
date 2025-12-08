package miyu.smart.BackendMusica.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface CancionResumen {
    // Datos directos de la tabla cancion
    String getNombre();
    String getDescripcion();
    @JsonProperty("duracion_segundos")
    int getDuracionSegundos();
    @JsonProperty("fecha_salida")
    int getFechaSalida();
    double getCalificacion();
    @JsonProperty("portada_url")
    String getPortadaUrl();

    // Datos traídos de otras tablas con JOIN
    @JsonProperty("nombre_album")
    String getNombreAlbum();
    
    @JsonProperty("nombre_artista")
    String getNombreArtista();
}