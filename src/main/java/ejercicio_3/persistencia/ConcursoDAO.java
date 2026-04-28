package ejercicio_3.persistencia;

import ejercicio_3.modelo.Concurso;
import ejercicio_3.modelo.GestionPersistencia;

import java.util.ArrayList;
import java.util.List;

public interface ConcursoDAO extends GestionPersistencia<Concurso> {


    public void truncateTabla();
}
