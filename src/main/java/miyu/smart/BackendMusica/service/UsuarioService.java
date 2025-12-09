package miyu.smart.BackendMusica.service;

import miyu.smart.BackendMusica.entity.Usuario;
import miyu.smart.BackendMusica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder codificadorContraseña;

    public Usuario guardarUsuario(Usuario usuario){
        String passwordPlana = usuario.getPassword();
        String hash = codificadorContraseña.encode(passwordPlana);
        usuario.setPassword(hash);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuario(UUID id){
        Optional<Usuario> contenedor = usuarioRepository.findById(id);

        return contenedor.orElse(null);
    }

    public void eliminarUsuario (UUID id){
        usuarioRepository.deleteById(id);
    }

    public Usuario actualizarDatosPerfil(UUID id, Usuario datosNuevos) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioExistente.setNombre(datosNuevos.getNombre());
        usuarioExistente.setCorreo(datosNuevos.getCorreo());

        usuarioExistente.setFotoUrl(datosNuevos.getFotoUrl());

        return usuarioRepository.save(usuarioExistente);
    }

    public Usuario autenticar(String correo, String password) {
        Optional<Usuario> contenedor = usuarioRepository.findByCorreo(correo);

        if (contenedor.isPresent()) {
            Usuario usuario = contenedor.get();
            if (codificadorContraseña.matches(password, usuario.getPassword())) {
                return usuario;
            }
        }
        return null;
    }
}
