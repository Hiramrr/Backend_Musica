package miyu.smart.BackendMusica.controller;


import miyu.smart.BackendMusica.entity.Album;
import miyu.smart.BackendMusica.entity.Artista;
import miyu.smart.BackendMusica.entity.Cancion;
import miyu.smart.BackendMusica.service.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/artistas")
public class ArtistaControlador {

    @Autowired
    private ArtistaService artistaService;

    @GetMapping
    public ResponseEntity<List<Artista>> getAllArtistas(){
        return ResponseEntity.ok(artistaService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<Artista> saveArtista(@RequestBody Artista artista){
        try{
            Artista nuevoArtista = artistaService.subirArtista(artista);
            return ResponseEntity.created(new URI("/api/artista/" + nuevoArtista.getId())).body(nuevoArtista);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artista> updateArtista(@PathVariable UUID id, Artista artistaDatosNuevos){
        Artista artistaExistente = artistaService.obtenerArtista(id);

        if (artistaExistente == null){
            return ResponseEntity.notFound().build();
        }

        artistaDatosNuevos.setId(id);

        try {
            Artista artistaActualizado = artistaService.actualizarArtista(artistaDatosNuevos);
            return ResponseEntity.ok(artistaActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtista(@PathVariable UUID id){
        Artista artistaExistente = artistaService.obtenerArtista(id);
        if(artistaExistente != null){
            artistaService.eliminarArtista(artistaExistente.getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/canciones/{id}")
    public ResponseEntity<Optional<Cancion>> getCanciones(@PathVariable UUID id){
        Optional<Cancion> canciones = Optional.ofNullable(artistaService.obtenerCanciones(id));

        if (canciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(canciones);
    }

    @GetMapping("/albums/{id}")
    public ResponseEntity<Optional<Album>> getAlbums(@PathVariable UUID id){
        Optional<Album> canciones = Optional.ofNullable(artistaService.obtenerAlbums(id));

        if (canciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(canciones);
    }
}
