package miyu.smart.BackendMusica.service;

import miyu.smart.BackendMusica.entity.Reseña;
import miyu.smart.BackendMusica.repository.ReseñaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        return reseñaRepository.findByCancionId(cancionId);
    }

    public void eliminar(UUID id) {
        reseñaRepository.deleteById(id);
    }

    public List<Reseña> obtenerPorAlbum(UUID albumId) {
        return reseñaRepository.findByAlbumId(albumId);
    }
}
