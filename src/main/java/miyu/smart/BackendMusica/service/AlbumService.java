package miyu.smart.BackendMusica.service;

import org.springframework.transaction.annotation.Transactional;
import miyu.smart.BackendMusica.dto.AlbumDetalleDTO;
import miyu.smart.BackendMusica.dto.AlbumResumen;
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
import java.util.stream.Collectors;

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

    public List<Album> obtenerPorArtista(UUID artistaId) {
        return albumRepository.findByArtistas_Id(artistaId);
    }

    public Album actualizarAlbum(Album albumDatosNuevos){
        if(albumDatosNuevos.getId() == null) {
            return null;
        }

        //Buscamos el álbum original en la BD 
        Optional<Album> albumExistenteOpt = albumRepository.findById(albumDatosNuevos.getId());

        if(albumExistenteOpt.isPresent()){ 
            Album albumExistente = albumExistenteOpt.get();

            //Actualizamos SOLO los datos informativos sin afectar la lista de canciones
            albumExistente.setNombre(albumDatosNuevos.getNombre());
            albumExistente.setFechaSalida(albumDatosNuevos.getFechaSalida());
            albumExistente.setDescripcion(albumDatosNuevos.getDescripcion());
            albumExistente.setPortadaUrl(albumDatosNuevos.getPortadaUrl());
            albumExistente.setTotalCanciones(albumDatosNuevos.getTotalCanciones());          
            albumExistente.setDuracion_segundos(albumDatosNuevos.getDuracion_segundos());

            //Si el artista cambio tambien se actualiza 
            if (albumDatosNuevos.getArtistas() != null && !albumDatosNuevos.getArtistas().isEmpty()) {
                albumExistente.setArtistas(albumDatosNuevos.getArtistas());
            }

            //Guardamos el objeto existente actualizado y como nunca tocamos las canciones, estas permanecen intactas
            return albumRepository.save(albumExistente);
        }
        
        return null;
    }

    public List<AlbumResumen> obtenerTodosPersonalizados() {
        return albumRepository.obtenerResumenesNativos();
    }

    @Transactional(readOnly = true) //Indicamos que es una transacción de solo lectura para mantener la conexion con la base de datos
    public AlbumDetalleDTO obtenerDetalleAlbum(UUID id) {
        
        // Aquí trae el Álbum junto con sus Artistas 
        Optional<Album> albumOpt = albumRepository.buscarPorIdConDetalles(id);

        if (albumOpt.isEmpty()) {
            return null;
        }

        Album album = albumOpt.get();

        String nombresArtistas = album.getArtistas().stream()
                .map(artista -> artista.getNombre())
                .collect(Collectors.joining(", "));
 
        //como estamos dentro de @Transactional,
        //podemos hacer una segunda consulta para traer la lista de canciones.
        List<AlbumDetalleDTO.CancionInfo> cancionesDTO = album.getCanciones().stream()
                .map(cancion -> new AlbumDetalleDTO.CancionInfo(
                        cancion.getId(),
                        cancion.getNombre(),
                        convertirSegundosAFormato(cancion.getDuracion_segundos())
                ))
                .collect(Collectors.toList());

        return new AlbumDetalleDTO(
                album.getId(),
                album.getNombre(),
                nombresArtistas,
                album.getDescripcion(),
                album.getFechaSalida(),
                album.getPortadaUrl(),
                cancionesDTO
        );
    }

    // Método auxiliar para convertir segundos a formato MM:SS y que el front no tenga que hacerlo
    private String convertirSegundosAFormato(int totalSegundos) {
        int minutos = totalSegundos / 60;
        int segundos = totalSegundos % 60;
        return String.format("%d:%02d", minutos, segundos);
    }
    

}