package miyu.smart.BackendMusica.dto;

import java.util.UUID;

//este dto es mas ligero que el de detalle, se creo para que consultar el perfil fuera mas ligero
public class AlbumDTO {
    private UUID id;
    private String nombre;
    private String portadaUrl;
    private String artista;

    public AlbumDTO(UUID id, String nombre, String portadaUrl, String artista) {
        this.id = id;
        this.nombre = nombre;
        this.portadaUrl = portadaUrl;
        this.artista = artista;
    }
    public UUID getId() { return id; }
    public String getNombre() { return nombre; }
    public String getPortadaUrl() { return portadaUrl; }
    public String getArtista() { return artista; }
}
