package ejercicio_3.persistencia;

import ejercicio_3.modelo.GestionPersistencia;
import ejercicio_3.modelo.Inscripto;

import java.util.List;

public interface InscriptoDAO extends GestionPersistencia <Inscripto>{


    public void truncateTabla();
}
