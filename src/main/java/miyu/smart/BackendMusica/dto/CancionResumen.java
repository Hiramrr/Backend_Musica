package miyu.smart.BackendMusica.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface CancionResumen {
    UUID getId();
    String getNombre();
    String getDescripcion();
    @JsonProperty("duracion_segundos")
    int getDuracionSegundos();
    @JsonProperty("fecha_salida")
    int getFechaSalida();
    double getCalificacion();
    @JsonProperty("portada_url")
    String getPortadaUrl();

    @JsonProperty("nombre_album")
    String getNombreAlbum();
    
    @JsonProperty("nombre_artista")
    String getNombreArtista();
}