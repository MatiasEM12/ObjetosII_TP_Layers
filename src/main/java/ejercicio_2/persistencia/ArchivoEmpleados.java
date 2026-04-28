package ejercicio_2.persistencia;

import ejercicio_2.modelo.Email;
import ejercicio_2.modelo.RegistroEmpleados;
import ejercicio_2.modelo.Empleado;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArchivoEmpleados implements RegistroEmpleados {


    private File archivo;


    public ArchivoEmpleados(String ruta) throws IllegalArgumentException {
        validarRuta(ruta);
        this.archivo = new File(ruta);
    }

    @Override
    public void guardar(Empleado empleado)throws IllegalArgumentException {

        validarEmpleado(empleado);
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            List<Empleado> existentes = empleados();
            if (existentes.contains(empleado)) {
                throw new RuntimeException("El empleado ya existe en el archivo");
            }

            FileWriter writer = new FileWriter(archivo, true);

            String linea = empleado.apellido() + ", "
                    + empleado.nombre() + ", "
                    + empleado.fechaNacimiento() + ", "
                    + empleado.email();

            writer.write(linea + System.lineSeparator());
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar empleado", e);
        }

    }

    @Override
    public List empleados() {
        List<Empleado> empleados = new ArrayList<>();

        if (!archivo.exists()) {
            return empleados;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(", ");

                String apellido = partes[0];
                String nombre = partes[1];
                LocalDate fecha = LocalDate.parse(partes[2]);
                String email = partes[3];

                empleados.add(new Empleado(nombre, apellido, fecha, new Email(email)));
            }

        } catch (IOException e) {
            throw new RuntimeException("Error al leer empleados", e);
        }

        return empleados;
    }

    @Override
    public void eliminarRegistro(String ruta) {
        validarRuta(ruta);
        File archivoAEliminar = new File(ruta);
        if (archivoAEliminar.exists()) {
            if (!archivoAEliminar.delete()) {
                throw new RuntimeException("No se pudo eliminar el archivo: " + ruta);
            }
        } else {
            throw new RuntimeException("El archivo no existe: " + ruta);
        }
    }

    private void validarRuta(String dato)throws IllegalArgumentException{
        if(dato==null || dato.isEmpty())throw new IllegalArgumentException("La ruta no puede ser nulo o vacío.");
    }

    private void validarEmpleado(Empleado empleado)throws IllegalArgumentException{
        if(empleado==null)throw new IllegalArgumentException("El empleado no puede ser null.");
    }
}
