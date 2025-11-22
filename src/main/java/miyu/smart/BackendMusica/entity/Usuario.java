package miyu.smart.BackendMusica.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table (name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) //Esto hace que se generen las id automaticamente
    @Column(name = "usuario_id")
    private UUID id;

    @Column(name = "nombre", nullable = false)
    private String usuario_nombre;

    @Column(name= "correo" , nullable = false, unique = true)
    private String usuario_correo;

    @Column(name= "foto_url")
    private String usuario_foto_url;

    @Column(name= "password", nullable = false)
    private String password;

    public Usuario(UUID id, String usuario_nombre, String usuario_correo, String usuario_foto_url, String password) {
        this.id = id;
        this.usuario_nombre = usuario_nombre;
        this.usuario_correo = usuario_correo;
        this.usuario_foto_url = usuario_foto_url;
        this.password = password;
    }

    public Usuario() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsuario_nombre() {
        return usuario_nombre;
    }

    public void setUsuario_nombre(String usuario_nombre) {
        this.usuario_nombre = usuario_nombre;
    }

    public String getUsuario_correo() {
        return usuario_correo;
    }

    public void setUsuario_correo(String usuario_correo) {
        this.usuario_correo = usuario_correo;
    }

    public String getUsuario_foto_url() {
        return usuario_foto_url;
    }

    public void setUsuario_foto_url(String usuario_foto_url) {
        this.usuario_foto_url = usuario_foto_url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
