package miyu.smart.BackendMusica.service;

import miyu.smart.BackendMusica.entity.Artista;
import miyu.smart.BackendMusica.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArtistaService{

    @Autowired
    private ArtistaRepository artistaRepository;

    public Artista subirArtista(Artista artista){
        return artistaRepository.save(artista);
    }

    public Artista actualizarArtista(Artista artista){
        if(artista.getId() != null && artistaRepository.existsById(artista.getId())){
            return artistaRepository.save(artista);
        }
        return null;
    }

    public void eliminarArtista(Artista artista){
        artistaRepository.delete(artista);
    }

    public List<Artista> obtenerTodos(){
        return artistaRepository.findAll();
    }

    public Artista obtenerArtista(UUID id){
        Optional<Artista> contenedor = artistaRepository.findById(id);

        return contenedor.orElse(null);
    }
}
