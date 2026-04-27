package ejercicio_3.persistencia;

import ejercicio_3.modelo.Concurso;
import ejercicio_3.modelo.GestionArchivo;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ArchivoConcursos implements GestionArchivo<Concurso> {

    public static final int INDICE_ID = 0;
    private File archivo;

    public ArchivoConcursos(String ruta) {
        this.archivo = new File(ruta);
    }


    @Override
    public void crear(Concurso dato) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(dato.toFile());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir el archivo", e);
        }
    }



    @Override
    public void eliminarArchivo(String ruta) {
        File archivoEliminar = new File(ruta);
        if (archivoEliminar.exists()) {
            if (!archivoEliminar.delete()) {
                throw new RuntimeException("No se pudo eliminar el archivo");
            }
        }
    }

    @Override
    public ArrayList<Concurso> listar() {
        ArrayList<Concurso> lista = new ArrayList<>();

        if (!archivo.exists()) {
            return lista;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");

                int id = Integer.parseInt(partes[INDICE_ID]);
                String nombre = partes[1];
                LocalDate fechaInicio = LocalDate.parse(partes[2], formatter);
                LocalDate fechaFin = LocalDate.parse(partes[3], formatter);

                Concurso concurso = new Concurso(id, nombre, fechaInicio, fechaFin);
                lista.add(concurso);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo", e);
        }

        return lista;
    }
}
