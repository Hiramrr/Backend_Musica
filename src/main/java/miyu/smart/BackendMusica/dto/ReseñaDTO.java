package miyu.smart.BackendMusica.dto;

import java.time.LocalDate;
import java.util.UUID;

public class ReseñaDTO {
    private UUID id;
    private String contenido;
    private Double calificacion;
    private LocalDate fechaCreacion;
    private boolean esMia; //bandera para saber desde el front si la reseña pertenece al usuario logueado
    private AutorDTO autor; //datos de quien la escribió

    public ReseñaDTO(UUID id, String contenido, Double calificacion, LocalDate fechaCreacion, boolean esMia, AutorDTO autor) {
        this.id = id;
        this.contenido = contenido;
        this.calificacion = calificacion;
        this.fechaCreacion = fechaCreacion;
        this.esMia = esMia;
        this.autor = autor;
    }

    public UUID getId() { return id; }
    public String getContenido() { return contenido; }
    public Double getCalificacion() { return calificacion; }
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public boolean isEsMia() { return esMia; }
    public AutorDTO getAutor() { return autor; }

    //clase para manejar los datos del Usuario 
    public static class AutorDTO {
        private UUID id;
        private String nombre;
        private String fotoUrl;

        public AutorDTO(UUID id, String nombre, String fotoUrl) {
            this.id = id;
            this.nombre = nombre;
            this.fotoUrl = fotoUrl;
        }

        public UUID getId() { return id; }
        public String getNombre() { return nombre; }
        public String getFotoUrl() { return fotoUrl; }
    }
}