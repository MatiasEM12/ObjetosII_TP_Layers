package ejercicio_2.modelo;

import java.time.LocalDate;

import static com.sun.tools.javac.util.Constants.format;
import static java.time.MonthDay.from;

public class Empleado {

    private String nombre;
    private String apellido;
    private String email;
    private LocalDate fechaNacimiento;

    public Empleado(String nombre, String apellido,LocalDate fechaNacimiento,String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento=fechaNacimiento;
    }

    public String nombre() {
        return nombre;
    }


    public String apellido() {
        return apellido;
    }


    public String email() {
        return email;
    }

    public boolean esTuCumpleaños(LocalDate hoy){
        return from(this.fechaNacimiento).equals(hoy);
    }



}
