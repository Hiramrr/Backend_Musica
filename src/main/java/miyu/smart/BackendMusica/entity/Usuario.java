package miyu.smart.BackendMusica.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table (name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) //Esto hace que se generen las id automaticamente
    @Column(name = "id")
    private UUID id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name= "correo" , nullable = false, unique = true)
    private String correo;

    @Column(name= "foto_url")
    private String fotoUrl;

    @Column(name= "password", nullable = false)
    private String password;

    public Usuario() {
    }

    public Usuario(UUID id, String nombre, String correo, String fotoUrl, String password) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.fotoUrl = fotoUrl;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
