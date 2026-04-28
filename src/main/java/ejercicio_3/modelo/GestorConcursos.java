package ejercicio_3.modelo;

import java.time.LocalDate;
import java.util.List;

public class GestorConcursos {

    private GestionPersistencia<Concurso> concursos;

    public GestorConcursos(GestionPersistencia<Concurso> concursos) throws IllegalArgumentException {

        validarGestionPersistencia(concursos);
        this.concursos = concursos;
    }

    public void guardarConcurso(int id, String nombre, LocalDate fechaInicio,LocalDate fechaFin) throws Exception {
        var concurso = new Concurso(id, nombre, fechaInicio, fechaFin);
        var existentes = concursos.listar();

        for (Concurso c : existentes) {
            if (c.mismoId(concurso)) {
                throw new RuntimeException("Ya existe un concurso con el mismo id");
            }
        }
        this.concursos.crear(concurso);
    }

    public List<Concurso> listarConcursos() {
        return this.concursos.listar();
    }


    public List<Concurso> listarConcursosActivos() {
        return this.concursos.listar().stream()
                .filter(Concurso::estaActivo)
                .toList();
    }

    private void validarGestionPersistencia(GestionPersistencia<Concurso> concursos) throws IllegalArgumentException{
        if(concursos ==null){
             throw new IllegalArgumentException("Gestion de Persistencia no puede ser nulo");
        }
    }

}
