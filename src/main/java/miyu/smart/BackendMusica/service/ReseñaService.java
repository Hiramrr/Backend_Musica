package miyu.smart.BackendMusica.service;

import miyu.smart.BackendMusica.dto.ReseñaDTO;
import miyu.smart.BackendMusica.entity.Reseña;
import miyu.smart.BackendMusica.entity.Usuario;
import miyu.smart.BackendMusica.repository.ReseñaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReseñaService {

    @Autowired
    private ReseñaRepository reseñaRepository;

    public Reseña guardar(Reseña reseña) {
        return reseñaRepository.save(reseña);
    }

    public List<Reseña> obtenerTodas() {
        return reseñaRepository.findAll();
    }

    public Optional<Reseña> obtenerPorId(UUID id) {
        return reseñaRepository.findById(id);
    }


    public List<Reseña> obtenerPorCancion(UUID cancionId) {
        return reseñaRepository.findByCancion_Id(cancionId);
    }

    public void eliminar(UUID id) {
        reseñaRepository.deleteById(id);
    }

    public List<Reseña> obtenerPorAlbum(UUID albumId) {
        return reseñaRepository.findByAlbum_Id(albumId);
    }

    @Transactional(readOnly = true)
    //@Transactional le dice a los metodos que no cierre la conexion de la base de datos hasta que termine todo el metodo
    //de lo contrario por defalt cierra la conexion al terminar la primera consulta
    //lo dejamos en solo lectura porque no vamos a modificar nada en la base de datos
    public List<ReseñaDTO> obtenerReseñasDeAlbum(UUID albumId, UUID usuarioVisitanteId) { 
        // Nota el guion bajo aquí también
        List<Reseña> reseñas = reseñaRepository.findByAlbum_Id(albumId); 
        return convertirLista(reseñas, usuarioVisitanteId);
    }

    @Transactional(readOnly = true)
    public List<ReseñaDTO> obtenerReseñasDeCancion(UUID cancionId, UUID usuarioVisitanteId) {
        // Nota el guion bajo aquí también
        List<Reseña> reseñas = reseñaRepository.findByCancion_Id(cancionId);
        return convertirLista(reseñas, usuarioVisitanteId);
    }
    // Recibe cualquier lista de reseñas y el id del usuario para agregar una bandera si la reseña es suya
    private List<ReseñaDTO> convertirLista(List<Reseña> reseñas, UUID usuarioVisitanteId) {
        return reseñas.stream()
                .map(reseña -> {
                    Usuario autor = reseña.getUsuario();
                    
                    // Verificamos si el visitante es el dueño de la reseña
                    boolean esMia = usuarioVisitanteId != null && usuarioVisitanteId.equals(autor.getId());

                    ReseñaDTO.AutorDTO autorDTO = new ReseñaDTO.AutorDTO(
                            autor.getId(),
                            autor.getNombre(),
                            autor.getFotoUrl()
                    );

                    return new ReseñaDTO(
                            reseña.getId(),
                            reseña.getContenido(),
                            reseña.getCalificacion(),
                            reseña.getFechaCreacion(),
                            esMia,
                            autorDTO
                    );
                })
                .collect(Collectors.toList());
    }

    public Reseña editar(UUID reseñaId, Reseña datosNuevos, UUID usuarioId) {
        Reseña reseñaExistente = reseñaRepository.findById(reseñaId)
                .orElse(null);

        if (reseñaExistente == null) {
            throw new IllegalArgumentException("La reseña no existe");
        }

        //Verificamos que el usuario que intenta editar sea el dueño
        if (!reseñaExistente.getUsuario().getId().equals(usuarioId)) {
            throw new SecurityException("No tienes permiso para editar esta reseña");
        }

        // Actualizamos contenido y calificación
        reseñaExistente.setContenido(datosNuevos.getContenido());
        reseñaExistente.setCalificacion(datosNuevos.getCalificacion());
        
        //Actualizar la fecha a hoy
        reseñaExistente.setFechaCreacion(java.time.LocalDate.now());
        return reseñaRepository.save(reseñaExistente);
    }


}
