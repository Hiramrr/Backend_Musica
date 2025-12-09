package miyu.smart.BackendMusica.controller;

import miyu.smart.BackendMusica.entity.Usuario;
import miyu.smart.BackendMusica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioControlador {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String correo = credenciales.get("correo");
        String password = credenciales.get("password");

        Usuario usuario = usuarioService.autenticar(correo, password);

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarPerfil(@PathVariable UUID id, @RequestBody Usuario usuarioDatos) {
        try {
            Usuario usuarioActualizado = usuarioService.actualizarDatosPerfil(id, usuarioDatos);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorID(@PathVariable UUID id){
        try {
            Usuario usuarioBuscado = usuarioService.obtenerUsuario(id);
            return ResponseEntity.ok(usuarioBuscado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarCuenta(@PathVariable UUID id) {

        if (usuarioService.obtenerUsuario(id) != null) {
            usuarioService.borrarPorID(id);
            return ResponseEntity.ok().body("se elimino");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}