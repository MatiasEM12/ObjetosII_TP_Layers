package ejercicio_3.persistencia;

import ejercicio_3.modelo.Email;
import ejercicio_3.modelo.GestionArchivo;
import ejercicio_3.modelo.Inscripto;
import ejercicio_3.modelo.Telefono;

import java.io.*;
import java.util.ArrayList;

public class ArchivoInscriptos implements GestionArchivo<Inscripto> {

    private File archivo;

    public ArchivoInscriptos(String ruta) {
        this.archivo = new File(ruta);
    }

    @Override
    public void crear(Inscripto dato) {
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
    public java.util.ArrayList<Inscripto> listar() {
        ArrayList<Inscripto> lista = new ArrayList<>();

        if (!archivo.exists()) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");

                String apellido = partes[0];
                String nombre = partes[1];
                String dni = partes[2];
                Telefono telefono = new Telefono(partes[3]);
                Email email = new Email(partes[4]);
                int idConcurso = Integer.parseInt(partes[5]);

                Inscripto inscripto = new Inscripto(
                        nombre,
                        apellido,
                        dni,
                        telefono,
                        email,
                        idConcurso
                );

                lista.add(inscripto);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo", e);
        }

        return lista;
    }
}
