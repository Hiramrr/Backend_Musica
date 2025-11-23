package miyu.smart.BackendMusica.service;

import miyu.smart.BackendMusica.entity.Album;
import miyu.smart.BackendMusica.entity.Artista;
import miyu.smart.BackendMusica.entity.Cancion;
import miyu.smart.BackendMusica.repository.AlbumRepository;
import miyu.smart.BackendMusica.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistaRepository artistaRepository;


    public Album guardar(Album album) {
        return albumRepository.save(album);
    }

    public List<Album> obtenerTodos() {
        return albumRepository.findAll();
    }

    public Album obtenerAlbum(UUID id){
        Optional<Album> contenedor = albumRepository.findById(id);

        return contenedor.orElse(null);
    }

    public void eliminar(UUID id) {
        albumRepository.deleteById(id);
    }

    public List<Artista> obtenerArtistasDeAlbum(UUID id) {
        return artistaRepository.findByAlbumsId(id);
    }

    public Optional<Album> obtenerAlbumPorArtista(UUID artistaId) {
        return albumRepository.findByArtistasId(artistaId);
    }

    public Album actualizarAlbum(Album album){
        if(album.getId() != null && albumRepository.existsById(album.getId())){
            return albumRepository.save(album);
        }
        return null;
    }
}