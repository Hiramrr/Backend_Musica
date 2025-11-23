package miyu.smart.BackendMusica.service;

import miyu.smart.BackendMusica.entity.Artista;
import miyu.smart.BackendMusica.entity.Cancion;
import miyu.smart.BackendMusica.repository.ArtistaRepository;
import miyu.smart.BackendMusica.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CancionService{

    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private ArtistaRepository artistaRepository;


    public Cancion guardar(Cancion cancion) {
        return cancionRepository.save(cancion);
    }

    public List<Cancion> obtenerTodos() {
        return cancionRepository.findAll();
    }

    public Cancion obtenerCancion(UUID id){
        Optional<Cancion> contenedor = cancionRepository.findById(id);

        return contenedor.orElse(null);
    }

    public void eliminar(UUID id) {
        cancionRepository.deleteById(id);
    }

    public List<Artista> obtenerArtistasDeCancion(UUID id) {
        return artistaRepository.findByCancionesId(id);
    }
}
