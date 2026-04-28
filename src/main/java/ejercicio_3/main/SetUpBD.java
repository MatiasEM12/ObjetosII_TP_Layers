package ejercicio_3.main;

import ejercicio_3.modelo.Concurso;
import ejercicio_3.modelo.Email;
import ejercicio_3.modelo.Inscripto;
import ejercicio_3.modelo.Telefono;
import ejercicio_3.persistencia.ConcursoDAO;
import ejercicio_3.persistencia.ConcursoDAOJDBC;
import ejercicio_3.persistencia.InscriptoDAO;
import ejercicio_3.persistencia.InscriptosDAOJDBC;

import java.time.LocalDate;

public class SetUpBD {

    private ConcursoDAO concursoDAO;
    private InscriptoDAO inscriptoDAO;

    public SetUpBD() {
        this.concursoDAO = new ConcursoDAOJDBC();
        this.inscriptoDAO = new InscriptosDAOJDBC();
    }

    public void inicializar() {
        try {
            inicializarConcursos();
            inicializarInscriptos();
        } catch (Exception e) {
            throw new RuntimeException("Error al inicializar BD"+ e);
        }
    }


    private void inicializarConcursos() {

        try {
            concursoDAO.truncateTabla();
        }catch (RuntimeException e){
            System.out.println("Error al limpiar tabla: " + e.getMessage()) ;
        }
        concursoDAO.crear(new Concurso(
                1,
                "Concurso X",
                LocalDate.of(2020, 6, 1),
                LocalDate.of(2020, 7, 1)
        ));

        concursoDAO.crear(new Concurso(
                2,
                "Concurso Y",
                LocalDate.of(2020, 8, 1),
                LocalDate.of(2020, 9, 1)
        ));

        concursoDAO.crear(new Concurso(
                3,
                "Concurso Z",
                LocalDate.now(),
                LocalDate.now().plusDays(10)
        ));
    }


    private void inicializarInscriptos() {

        try {
            inscriptoDAO.truncateTabla();
        }catch (RuntimeException e){
            System.out.println("Error al limpiar tabla: " + e.getMessage()) ;
        }
        inscriptoDAO.crear(new Inscripto(
                "Angus",
                "Young",
                "12345678",
                new Telefono("4444-898789"),
                new Email("angus@acdc.com"),
                1
        ));

        inscriptoDAO.crear(new Inscripto(
                "Brian",
                "Johnson",
                "87654321",
                new Telefono("7789-658987"),
                new Email("brian@acdc.com"),
                2
        ));
    }
}