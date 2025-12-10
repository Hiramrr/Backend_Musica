package miyu.smart.BackendMusica.dto;

import java.util.List;
import java.util.UUID;

//Esta clase funciona como un  Data Transfer Object Solo sirve para transportar datos al front.
public class AlbumDetalleDTO {
    private UUID id;
    private String nombre;
    private String nombreArtista; 
    private String descripcion;
    private Integer fechaSalida;
    private String portadaUrl;
    private List<CancionInfo> canciones; // Lista de canciones del al bum

    public AlbumDetalleDTO(UUID id, String nombre, String nombreArtista, String descripcion, Integer fechaSalida, String portadaUrl, List<CancionInfo> canciones) {
        this.id = id;
        this.nombre = nombre;
        this.nombreArtista = nombreArtista;
        this.descripcion = descripcion;
        this.fechaSalida = fechaSalida;
        this.portadaUrl = portadaUrl;
        this.canciones = canciones;
    }

    public UUID getId() { return id; }
    public String getNombre() { return nombre; }
    public String getNombreArtista() { return nombreArtista; }
    public String getDescripcion() { return descripcion; }
    public Integer getFechaSalida() { return fechaSalida; }
    public String getPortadaUrl() { return portadaUrl; }
    public List<CancionInfo> getCanciones() { return canciones; }

    // Clase interna pequeña para las canciones de la lista
    public static class CancionInfo {
        private UUID id;
        private String nombre;
        private String duracion;

        public CancionInfo(UUID id, String nombre, String duracion) {
            this.id = id;
            this.nombre = nombre;
            this.duracion = duracion;
        }

        public UUID getId() { return id; }
        public String getNombre() { return nombre; }
        public String getDuracion() { return duracion; }
    }
}