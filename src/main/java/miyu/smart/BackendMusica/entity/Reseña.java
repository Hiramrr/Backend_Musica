package miyu.smart.BackendMusica.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reseña")
@Data
@NoArgsConstructor
public class Reseña {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "resena_id")
    private UUID id;

    @Column(name="contenido",columnDefinition = "TEXT", nullable = false)
    private String contenido;

    @Column(name="calificacion", nullable = false)
    private Double calificacion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = true)
    private Album album;

    @ManyToOne
    @JoinColumn(name = "cancion_id", nullable = true)
    private Cancion cancion;
}