package miyu.smart.BackendMusica.dto;

import java.util.UUID;

//Creamos una interfaz para mapear los resultados de la consulta nativa
public interface AlbumResumen {
    UUID getId();
    String getNombre();
    String getNombreArtista();
    int getFechaSalida();
    String getDescripcion();
    String getPortadaUrl();
}