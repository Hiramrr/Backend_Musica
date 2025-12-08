package miyu.smart.BackendMusica.dto;

//Creamos una interfaz para mapear los resultados de la consulta nativa
public interface AlbumResumen {
    String getNombre();
    String getNombreArtista();
    int getFechaSalida();
    String getDescripcion();
    String getPortadaUrl();
}