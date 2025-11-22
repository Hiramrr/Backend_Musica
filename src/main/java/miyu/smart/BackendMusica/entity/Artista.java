package miyu.smart.BackendMusica.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "artista")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="nombre", nullable = false)
    private String nombre_artista;

    @Column(name = "fecha_inicio", nullable = false)
    private int fecha_inicio; //las fechas son en año como por ejemplo 2005 para que sea mas facil jaja

    @Column(name = "fecha_fin") // si es null es porque sigue activo
    private int fecha_fin;

    @Column(name = "nacimiento")
    private LocalDate nacimiento;

    @Column(name = "bibibliografia", columnDefinition = "TEXT")
    private String bibliografia;

    @Column(name = "foto_url")
    private String foto_url;

    @ManyToMany(mappedBy = "artistas")
    private List<Cancion> canciones;

    @ManyToMany(mappedBy = "artistas")
    private List<Album> albums;

    public Artista(UUID id, String nombre_artista, int fecha_inicio, int fecha_fin, LocalDate nacimiento, String bibliografia, String foto_url, List<Cancion> canciones, List<Album> albums) {
        this.id = id;
        this.nombre_artista = nombre_artista;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.nacimiento = nacimiento;
        this.bibliografia = bibliografia;
        this.foto_url = foto_url;
        this.canciones = canciones;
        this.albums = albums;
    }

    public Artista() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre_artista() {
        return nombre_artista;
    }

    public void setNombre_artista(String nombre_artista) {
        this.nombre_artista = nombre_artista;
    }

    public int getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(int fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public int getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(int fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getBibliografia() {
        return bibliografia;
    }

    public void setBibliografia(String bibliografia) {
        this.bibliografia = bibliografia;
    }

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
