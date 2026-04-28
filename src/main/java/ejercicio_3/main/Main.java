package ejercicio_3.main;

import ejercicio_3.modelo.GestorConcursos;
import ejercicio_3.modelo.GestorInscripciones;
import ejercicio_3.persistencia.ArchivoConcursos;
import ejercicio_3.persistencia.ArchivoInscriptos;
import ejercicio_3.persistencia.ConcursoDAOJDBC;
import ejercicio_3.persistencia.InscriptosDAOJDBC;
import ejercicio_3.vista.RadioCompetition;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static final String RUTA_CONCURSOS = "src/main/resources/concursos.txt";
    public static final String RUTA_INSCRIPTOS = "src/main/resources/inscriptos.txt";


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Main().start();

                } catch (Exception e) {

                    System.out.println(e);
                }
            }
        });
    }
    private void start() {


       usarConArchivo();
        // usarConBaseDeDatos();

    }

    private static void usarConArchivo() {
        new SetUpArchivo(RUTA_CONCURSOS, RUTA_INSCRIPTOS).inicializar();
        var gestorConcurso = new GestorConcursos(new ArchivoConcursos(RUTA_CONCURSOS));
        var gestorInscriptos = new GestorInscripciones(new ArchivoInscriptos(RUTA_INSCRIPTOS));
        new RadioCompetition(gestorInscriptos, gestorConcurso);
    }
    private static void usarConBaseDeDatos() {

        new SetUpBD().inicializar();

        var gestorConcurso = new GestorConcursos(new ConcursoDAOJDBC());
        var gestorInscriptos = new GestorInscripciones(new InscriptosDAOJDBC());

        new RadioCompetition(gestorInscriptos, gestorConcurso);
    }
}

