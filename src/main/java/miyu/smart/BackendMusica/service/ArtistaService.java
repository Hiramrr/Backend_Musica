package miyu.smart.BackendMusica.service;

import miyu.smart.BackendMusica.entity.Album;
import miyu.smart.BackendMusica.entity.Artista;
import miyu.smart.BackendMusica.entity.Cancion;
import miyu.smart.BackendMusica.repository.AlbumRepository;
import miyu.smart.BackendMusica.repository.ArtistaRepository;
import miyu.smart.BackendMusica.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArtistaService{

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private AlbumRepository albumRepository;

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

    public Cancion obtenerCanciones(UUID id){
        Optional<Cancion> contenedor = cancionRepository.findByArtistasId(id);

        return contenedor.orElse(null);
    }

    public Album obtenerAlbums(UUID id){
        Optional<Album> contenedor = albumRepository.findByArtistasId(id);

        return contenedor.orElse(null);
    }
}
