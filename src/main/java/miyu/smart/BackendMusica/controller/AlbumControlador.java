package miyu.smart.BackendMusica.controller;


import miyu.smart.BackendMusica.entity.Album;
import miyu.smart.BackendMusica.entity.Cancion;
import miyu.smart.BackendMusica.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/albums")
public class AlbumControlador {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums(){
        return ResponseEntity.ok(albumService.obtenerTodos());
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
}
