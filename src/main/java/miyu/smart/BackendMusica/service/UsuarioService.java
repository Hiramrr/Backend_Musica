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

    public Usuario actualizarUsuario(Usuario usuario){
        if (usuario.getId() != null && usuarioRepository.existsById(usuario.getId())){
            if (usuario.getPassword() != null) {
                usuario.setPassword(codificadorContraseña.encode(usuario.getPassword()));
            }
            return usuarioRepository.save(usuario);
        }
        return null;
    }
}
