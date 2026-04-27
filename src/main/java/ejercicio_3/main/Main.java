package ejercicio_3.main;

import ejercicio_3.modelo.GestionArchivo;
import ejercicio_3.modelo.GestorConcursos;
import ejercicio_3.modelo.GestorInscripciones;
import ejercicio_3.persistencia.ArchivoConcursos;
import ejercicio_3.persistencia.ArchivoInscriptos;
import ejercicio_3.vista.RadioCompetition;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static final String RUTA_CONCURSOS = "concursos.txt";
    public static final String RUTA_INSCRIPTOS = "inscriptos.txt";


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
        inicializarArchivos();
        var gestorConcurso= new GestorConcursos(new ArchivoConcursos(RUTA_CONCURSOS));
        var gestorInscriptos= new GestorInscripciones(new ArchivoInscriptos(RUTA_INSCRIPTOS));

        new RadioCompetition( gestorInscriptos,gestorConcurso);
    }

    private void inicializarArchivos() {

        try {
            inicializarConcursos();
            inicializarInscriptos();
        } catch (Exception e) {
            throw new RuntimeException("Error al inicializar archivos", e);
        }
    }

    private void inicializarConcursos() throws Exception {
        File archivo = new File(RUTA_CONCURSOS);

        if (archivo.exists() && archivo.length() > 0) {
            return; // El archivo ya existe y no está vacío, no se necesita inicializar
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {

            bw.write("1,Concurso X,2020/06/01,2020/07/01");
            bw.newLine();
            bw.write("2,Concurso Y,2020/08/01,2020/09/01");
            bw.newLine();

            LocalDate inicio = LocalDate.now();
            LocalDate fin = LocalDate.now().plusDays(10);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            bw.write("3,Concurso Z," + inicio.format(formatter) + "," + fin.format(formatter));
            bw.newLine();

        }
    }

    private void inicializarInscriptos() throws Exception {
        File archivo = new File(RUTA_INSCRIPTOS);

        if (archivo.exists() && archivo.length() > 0) {
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {

            bw.write("Young,Angus,12345678,4444-898789,angus@acdc.com,1");
            bw.newLine();
            bw.write("Johnson,Brian,87654321,7789-658987,brian@acdc.com,2");
            bw.newLine();


        }
    }
}

