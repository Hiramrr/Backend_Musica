package miyu.smart.BackendMusica.controller;

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

    @GetMapping
    public ResponseEntity<List<Reseña>> obtenerTodas() {
        return ResponseEntity.ok(reseñaService.obtenerTodas());
    }

    @GetMapping("/cancion/{id}")
    public ResponseEntity<List<Reseña>> obtenerPorCancion(@PathVariable UUID id) {
        List<Reseña> reseñas = reseñaService.obtenerPorCancion(id);
        if (reseñas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reseñas);
    }

    @GetMapping("/album/{id}")
    public ResponseEntity<List<Reseña>> obtenerPorAlbum(@PathVariable UUID id) {
        List<Reseña> reseñas = reseñaService.obtenerPorAlbum(id);
        if (reseñas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reseñas);
    }

    @PostMapping
    public ResponseEntity<Reseña> crearReseña(@RequestBody Reseña reseña) {
        try {
            // Nota: El JSON debe incluir el objeto 'usuario' con su ID, 
            // y O BIEN 'cancion' con ID O BIEN 'album' con ID (no ambos).
            Reseña nuevaReseña = reseñaService.guardar(reseña);
            return ResponseEntity.created(new URI("/api/resenas/" + nuevaReseña.getId())).body(nuevaReseña);
        } catch (Exception e) {
            // Esto capturará errores como violar el constraint de la BD (enviar album y cancion a la vez)
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