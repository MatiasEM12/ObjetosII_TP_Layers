package ejercicio_2.modelo;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Objects;


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

    public LocalDate fechaNacimiento() {
        return fechaNacimiento;
    }


    public String email() {
        return email;
    }

    public boolean esTuCumpleaños(MonthDay hoy){
        return from(this.fechaNacimiento).equals(hoy);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empleado)) return false;
        Empleado empleado = (Empleado) o;
        return email.equals(empleado.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
