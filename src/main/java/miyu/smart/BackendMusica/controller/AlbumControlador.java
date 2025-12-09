package miyu.smart.BackendMusica.controller;
import miyu.smart.BackendMusica.dto.AlbumDetalleDTO;
import miyu.smart.BackendMusica.dto.AlbumResumen;
import miyu.smart.BackendMusica.entity.Album;
import miyu.smart.BackendMusica.entity.Cancion;
import miyu.smart.BackendMusica.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/albums")
public class AlbumControlador {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<AlbumResumen>> getAllAlbums(){
        return ResponseEntity.ok(albumService.obtenerTodosPersonalizados()); // Usamos el método que devuelve una respuesta con solo datos necesarios
    }
    
    @PostMapping
    public ResponseEntity<Album> saveAlbum(@RequestBody Album album){
        try{
            Album nuevoAlbum = albumService.guardar(album);
            return ResponseEntity.created(new URI("/api/albums/" + nuevoAlbum.getId())).body(nuevoAlbum);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable UUID id){
        Album albumExistente = albumService.obtenerAlbum(id);
        if(albumExistente != null){
            albumService.eliminar(albumExistente.getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable UUID id, Album album){
        Album albumExistente = albumService.obtenerAlbum(id);

        if(albumExistente == null){
            return ResponseEntity.notFound().build();
        }
        try {
            Album albumActualizado = albumService.actualizarAlbum(albumExistente);
            return ResponseEntity.ok(albumExistente);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/artista/{id}")
    public ResponseEntity<Optional<Album>> getAlbumsByArtista(@PathVariable UUID id) {
        Optional<Album> albums = albumService.obtenerAlbumPorArtista(id);

        if (albums.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(albums);
    }

    @GetMapping("/{id}") //Este enpoint devuelve los detalles del album con el ID que coincida con la lista de sus canciones
    public ResponseEntity<AlbumDetalleDTO> getAlbumDetalle(@PathVariable UUID id) {
        AlbumDetalleDTO album = albumService.obtenerDetalleAlbum(id);

        if (album == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(album);
    }
}
