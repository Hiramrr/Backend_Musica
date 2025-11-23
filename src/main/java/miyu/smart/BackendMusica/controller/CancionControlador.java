package miyu.smart.BackendMusica.controller;

import miyu.smart.BackendMusica.entity.Cancion;
import miyu.smart.BackendMusica.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
}
