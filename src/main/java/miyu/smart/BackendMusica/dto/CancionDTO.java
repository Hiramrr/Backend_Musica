package miyu.smart.BackendMusica.dto;

import java.util.UUID;

public class CancionDTO {
    private UUID id;
    private String titulo;
    private String imagen;
    private String artista;

    public CancionDTO(UUID id, String titulo, String imagen, String artista) {
        this.id = id;
        this.titulo = titulo;
        this.imagen = imagen;
        this.artista = artista;
    }

    public UUID getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getImagen() { return imagen; }
    public String getArtista() { return artista; }
}
