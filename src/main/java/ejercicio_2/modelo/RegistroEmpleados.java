package ejercicio_2.modelo;

import java.util.ArrayList;
import java.util.List;

public interface RegistroEmpleados <T>{

    public  void guardar(Empleado empleado);

    public List<T> empleados();

    public  void eliminarRegistro(String ruta);
}
