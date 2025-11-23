package miyu.smart.BackendMusica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reseña")
public class Reseña {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name="contenido",columnDefinition = "TEXT", nullable = false)
    private String contenido;

    @Column(name="calificacion", nullable = false)
    private Double calificacion;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_album", nullable = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Album album;

    @ManyToOne
    @JoinColumn(name = "id_cancion", nullable = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cancion cancion;

    public Reseña(UUID id, String contenido, Double calificacion, LocalDate fechaCreacion, Album album, Usuario usuario, Cancion cancion) {
        this.id = id;
        this.contenido = contenido;
        this.calificacion = calificacion;
        this.fechaCreacion = fechaCreacion;
        this.album = album;
        this.usuario = usuario;
        this.cancion = cancion;
    }

    public Reseña() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }
}