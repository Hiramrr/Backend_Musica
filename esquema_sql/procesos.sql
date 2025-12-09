CREATE OR REPLACE FUNCTION actualizar_promedio_reseña()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'DELETE' AND OLD.id_album IS NOT NULL) OR (TG_OP != 'DELETE' AND NEW.id_album IS NOT NULL) THEN
UPDATE album
SET calificacion_promedio = (
    SELECT COALESCE(AVG(calificacion), 0)
    FROM reseña
    WHERE id_album = COALESCE(NEW.id_album, OLD.id_album)
)
WHERE id = COALESCE(NEW.id_album, OLD.id_album);
END IF;

    IF (TG_OP = 'DELETE' AND OLD.id_cancion IS NOT NULL) OR (TG_OP != 'DELETE' AND NEW.id_cancion IS NOT NULL) THEN
UPDATE cancion
SET calificacion = (
    SELECT COALESCE(AVG(calificacion), 0)
    FROM reseña
    WHERE id_cancion = COALESCE(NEW.id_cancion, OLD.id_cancion)
)
WHERE id = COALESCE(NEW.id_cancion, OLD.id_cancion);
END IF;

RETURN NULL;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_actualizar_promedio ON reseña;

CREATE TRIGGER trg_actualizar_promedio
    AFTER INSERT OR UPDATE OR DELETE ON reseña
    FOR EACH ROW
    EXECUTE FUNCTION actualizar_promedio_reseña();

CREATE OR REPLACE PROCEDURE eliminar_cuenta_usuario(usuario_id UUID)
LANGUAGE plpgsql
AS $$
BEGIN
DELETE FROM usuario WHERE id = usuario_id;

END;
$$;