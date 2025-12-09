package miyu.smart.BackendMusica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cancion")
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "duracion_segundos", nullable = false)
    private int duracion_segundos;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "calificacion")
    private double calificacion;

    @ManyToMany
    @JoinTable(name = "cancion_artistas", joinColumns = @JoinColumn(name = "id_cancion"), inverseJoinColumns = @JoinColumn(name = "id_artista"))
    private List<Artista> artistas = new ArrayList<>() ;

    @Column(name = "fecha_salida")
    private int fecha_salida;   //aqui solo guardariamos el año como 2005, la fecha se la pondremos en la descripcion, es opcional jaja

    @Column(name = "portada_url")
    private String portada_url;

    @ManyToOne
    @JoinColumn(name = "id_album")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnoreProperties("canciones")
    //Usamos JsonIgnoreProperties para que no traiga la lista de canciones del álbum evita bucle infinito album tiene canciones y cada cancion tiene album
    private Album album;

    public Cancion(UUID id, String nombre, int duracion_segundos, double calificacion, String descripcion, List<Artista> artistas, int fecha_salida, String portada_url, Album album) {
        this.id = id;
        this.nombre = nombre;
        this.duracion_segundos = duracion_segundos;
        this.calificacion = calificacion;
        this.descripcion = descripcion;
        this.artistas = artistas;
        this.fecha_salida = fecha_salida;
        this.portada_url = portada_url;
        this.album = album;
    }

    public Cancion() {

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

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    public int getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(int fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getPortada_url() {
        return portada_url;
    }

    public void setPortada_url(String portada_url) {
        this.portada_url = portada_url;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public UUID getIdAlbum() {
        return (album != null) ? album.getId() : null;
    }

}
