package miyu.smart.BackendMusica.controller;

import miyu.smart.BackendMusica.entity.Cancion;
import miyu.smart.BackendMusica.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/canciones")
public class CancionControlador {

    @Autowired
    private CancionService cancionService;

    @GetMapping
    public ResponseEntity<List<Cancion>> getAllCanciones(){
        return ResponseEntity.ok(cancionService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<Cancion> saveCancion(@RequestBody Cancion cancion){
        try {
            Cancion nueva_cancion = cancionService.guardar(cancion);
            return ResponseEntity.created(new URI("/api/canciones/" + nueva_cancion.getId())).body(nueva_cancion);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cancion> updateCancion(@PathVariable UUID id, @RequestBody Cancion cancionDatosNuevos){
        Cancion cancionExistente = cancionService.obtenerCancion(id);

        if (cancionExistente == null){
            return ResponseEntity.notFound().build();
        }

        cancionDatosNuevos.setId(id);

        try {
            Cancion cancionActualizada = cancionService.guardar(cancionDatosNuevos);
            return ResponseEntity.ok(cancionActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<Void> deleteCancion(@PathVariable UUID id){
        Cancion cancionExistente = cancionService.obtenerCancion(id);
        if(cancionExistente != null){
            cancionService.eliminar(cancionExistente.getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/artista/{id}")
    public ResponseEntity<Optional<Cancion>> getCancionesByArtista(@PathVariable UUID id) {
        Optional<Cancion> canciones = cancionService.obtenerCancionesPorArtista(id);

        if (canciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(canciones);
    }

}
