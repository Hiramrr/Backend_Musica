package miyu.smart.BackendMusica.entity;

import jakarta.persistence.*;

import java.beans.ConstructorProperties;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "duracion_segundos", nullable = false)
    private int duracion_segundos;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "calificacion_promedio")
    private Double calificacion;

    @Column(name = "total_canciones")
    private int totalCanciones;

    @Column(name = "fecha_salida")
    private int fechaSalida;

    @Column(name = "portada_url")
    private String portadaUrl;

    @ManyToMany
    @JoinTable(name = "album_artistas", joinColumns = @JoinColumn(name = "id_album"), inverseJoinColumns = @JoinColumn(name = "id_artista"))
    private List<Artista> artistas;

    @OneToMany(mappedBy = "album")
    private List<Cancion> canciones;

    public Album(UUID id, String nombre, int duracion_segundos, String descripcion, Double calificacion, int totalCanciones, String portadaUrl, int fechaSalida, List<Artista> artistas, List<Cancion> canciones) {
        this.id = id;
        this.nombre = nombre;
        this.duracion_segundos = duracion_segundos;
        this.descripcion = descripcion;
        this.calificacion = calificacion;
        this.totalCanciones = totalCanciones;
        this.portadaUrl = portadaUrl;
        this.fechaSalida = fechaSalida;
        this.artistas = artistas;
        this.canciones = canciones;
    }

    public Album() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion_segundos() {
        return duracion_segundos;
    }

    public void setDuracion_segundos(int duracion_segundos) {
        this.duracion_segundos = duracion_segundos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTotalCanciones() {
        return totalCanciones;
    }

    public void setTotalCanciones(int totalCanciones) {
        this.totalCanciones = totalCanciones;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public int getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(int fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getPortadaUrl() {
        return portadaUrl;
    }

    public void setPortadaUrl(String portadaUrl) {
        this.portadaUrl = portadaUrl;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }
}