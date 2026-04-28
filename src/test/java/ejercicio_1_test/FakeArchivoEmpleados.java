package ejercicio_1_test;

import ejercicio_2.modelo.Empleado;
import ejercicio_2.modelo.RegistroEmpleados;


import java.util.ArrayList;
import java.util.List;

public class FakeArchivoEmpleados implements RegistroEmpleados {


    List<Empleado> empleados;

    public FakeArchivoEmpleados(){
        this.empleados= new ArrayList<>();
    }


    @Override
    public void guardar(Empleado empleado) throws IllegalArgumentException {
        validacionEmpleado(empleado);
        empleados.add(empleado);
    }

    @Override
    public List empleados() {
        return this.empleados;
    }

    @Override
    public void eliminarRegistro(String ruta) {
        this.empleados.clear();
    }

    private void validacionEmpleado(Empleado empleado) throws IllegalArgumentException{
        if(empleado==null){
            throw new IllegalArgumentException("El empleado no puede ser null");
        }
        if(empleados.contains(empleado)){
            throw new RuntimeException("El empleado ya existe en el archivo");
        }
    }
}
