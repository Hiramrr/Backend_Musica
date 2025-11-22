package miyu.smart.BackendMusica.repository;

import miyu.smart.BackendMusica.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

}
