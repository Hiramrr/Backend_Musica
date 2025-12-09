package miyu.smart.BackendMusica.controller;

import miyu.smart.BackendMusica.dto.ReseñaDTO;
import miyu.smart.BackendMusica.entity.Reseña;
import miyu.smart.BackendMusica.service.ReseñaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/resenas")
public class ReseñaControlador {

    @Autowired
    private ReseñaService reseñaService;


    //Obtener reseñas de un ÁLBUM 
    @GetMapping("/album/{id}")
    public ResponseEntity<List<ReseñaDTO>> getReseñasPorAlbum(
            @PathVariable("id") UUID albumId,
            @RequestParam(required = false) UUID usuarioId) { //para usuarios no logeados puede ser null
        
        List<ReseñaDTO> reseñas = reseñaService.obtenerReseñasDeAlbum(albumId, usuarioId); //agrega la bandera esMia para saber si la reseña pertenece al usuario logueado

        return ResponseEntity.ok(reseñas);
    }

    //btener reseñas de una CANCIÓN 
    @GetMapping("/cancion/{id}")
    public ResponseEntity<List<ReseñaDTO>> getReseñasPorCancion(
            @PathVariable("id") UUID cancionId,
            @RequestParam(required = false) UUID usuarioId) {//para usuarios no logeados puede ser null
        
        List<ReseñaDTO> reseñas = reseñaService.obtenerReseñasDeCancion(cancionId, usuarioId);//agrega la bandera esMia para saber si la reseña pertenece al usuario logueado

        return ResponseEntity.ok(reseñas);
    }

    //Obtener TODAS las reseñas sin filtro
    @GetMapping
    public ResponseEntity<List<Reseña>> obtenerTodas() {
        return ResponseEntity.ok(reseñaService.obtenerTodas());
    }

    @PostMapping
    public ResponseEntity<Reseña> crearReseña(@RequestBody Reseña reseña) {
        try {
            Reseña nuevaReseña = reseñaService.guardar(reseña);
            return ResponseEntity.created(new URI("/api/resenas/" + nuevaReseña.getId())).body(nuevaReseña);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReseña(@PathVariable UUID id) {
        if (reseñaService.obtenerPorId(id).isPresent()) {
            reseñaService.eliminar(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}