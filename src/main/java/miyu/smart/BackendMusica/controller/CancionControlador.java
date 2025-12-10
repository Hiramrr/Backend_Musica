package miyu.smart.BackendMusica.controller;

import miyu.smart.BackendMusica.dto.CancionResumen;
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

/**definimos que esta clase actuara como un restcontroller, manejador solicitudes HTTP
    en la direccion /api/canciones
    proporciona endpoints para CRUD
 */
@RestController
@RequestMapping("/api/canciones")
public class CancionControlador {

    @Autowired
    private CancionService cancionService;

    @GetMapping
        public ResponseEntity<List<CancionResumen>> getAllCanciones(){
            return ResponseEntity.ok(cancionService.obtenerTodosPersonalizados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cancion> getCancionById(@PathVariable UUID id) {
        Cancion cancion = cancionService.obtenerCancion(id);
        if (cancion != null) {
            return ResponseEntity.ok(cancion);
        }
        return ResponseEntity.notFound().build();
    }


    /**
    * Guarda una nueva cancion en la base de datos
     *
     * @param cancion, el obtejo de cancion contiene todos los datos necesarios, se espera que venga en el cuerpo de la peticion
     * @return un response entity con la cancion creada y el estado 201 (creado)
     * Si algo sale mal devuelve 400, bad request
    */
    @PostMapping
    public ResponseEntity<Cancion> saveCancion(@RequestBody Cancion cancion){
        try {
            Cancion nuevaCancion = cancionService.guardar(cancion);
            return ResponseEntity.created(new URI("/api/canciones/" + nuevaCancion.getId())).body(nuevaCancion);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Actualiza una cancion por su id
     * @param id es la id de la cancion, viene en la url
     * @param cancionDatosNuevos es el objeto nuevo de la cancion, contiene los datos, igual viene en el cuerpo de la peticion
     * @return un response entity con la cancion actualizada y un codigo 200 de ok
     * puede devolver 404 si no encuentra una cancion con esa id
     * o tambien 400 si algo fallo durante la actualizacion
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cancion> updateCancion(@PathVariable UUID id, @RequestBody Cancion cancionDatosNuevos){
        Cancion cancionExistente = cancionService.obtenerCancion(id);

        if (cancionExistente == null){
            return ResponseEntity.notFound().build();
        }

        cancionDatosNuevos.setId(id);

        try {
            Cancion cancionActualizada = cancionService.actializarCancion(cancionDatosNuevos);
            return ResponseEntity.ok(cancionActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Borra una cancion de la base de datos
     * @param id espera que en la url se le pase el id de la cancion que se eliminara
     * @return un response entity vacio con un 200 ok de que si se elimino
     * 404 si no existe la cancion con esa id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCancion(@PathVariable UUID id){
        Cancion cancionExistente = cancionService.obtenerCancion(id);
        if(cancionExistente != null){
            cancionService.eliminar(cancionExistente.getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Obtiene las canciones asociadas a un artista en especifico
     * @param id del artista, viene en la url
     * @return un response entity que con tiene un optional con las canciones y un codigo 200 ok
     * devuelve 204 si no hay contenido, osea que el artista no tiene canciones o no existe
     */
    @GetMapping("/artista/{id}")
    public ResponseEntity<Optional<Cancion>> getCancionesByArtista(@PathVariable UUID id) {
        Optional<Cancion> canciones = cancionService.obtenerCancionesPorArtista(id);

        if (canciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(canciones);
    }
}
