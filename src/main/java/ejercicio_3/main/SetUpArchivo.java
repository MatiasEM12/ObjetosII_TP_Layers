package ejercicio_3.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SetUpArchivo {

    private String rutaConcursos;
    private String rutaInscriptos;

    public SetUpArchivo(String rutaConcursos, String rutaInscriptos) {
        this.rutaConcursos = rutaConcursos;
        this.rutaInscriptos = rutaInscriptos;
    }

    public void inicializar() {
        try {
            inicializarConcursos();
            inicializarInscriptos();
        } catch (Exception e) {
            throw new RuntimeException("Error al inicializar archivos", e);
        }
    }

    private void inicializarConcursos() throws Exception {
        File archivo = new File(rutaConcursos);

        if (archivo.exists() && archivo.length() > 0) {
            return;
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
        File archivo = new File(rutaInscriptos);

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