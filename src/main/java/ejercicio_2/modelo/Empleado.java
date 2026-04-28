package ejercicio_2.modelo;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Objects;


import static java.time.MonthDay.from;

public class Empleado {

    private String nombre;
    private String apellido;
    private Email email;
    private LocalDate fechaNacimiento;

    public Empleado(String nombre, String apellido,LocalDate fechaNacimiento,Email email) throws IllegalArgumentException {
        validacionApellido(apellido);
        validacionNombre(nombre);
        validarFechaDeNacimiento(fechaNacimiento);
        validacionEmail(email);
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
        return email.email();
    }

    public boolean esTuCumpleaños(MonthDay hoy){
        validacionMonthDay(hoy);
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

    private void validacionNombre( String nombre) throws IllegalArgumentException{
        if(nombre.isEmpty()){
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if(nombre==null){
            throw new IllegalArgumentException("El nombre no puede ser null");
        }
    }
    private void validacionApellido(String apellido)throws IllegalArgumentException{
        if(apellido.isEmpty()){
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        if(apellido==null){
            throw new IllegalArgumentException("El apellido no puede ser null");
        }
    }
    private void validarFechaDeNacimiento(LocalDate fechaNacimiento) throws IllegalArgumentException{
        if(fechaNacimiento==null){
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser null");
        }
        if(fechaNacimiento.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser posterior a la fecha actual");
        }
        if(fechaNacimiento.isBefore(LocalDate.now().minusYears(100))){
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser anterior a hace 100 años");
        }
        if(fechaNacimiento.isEqual(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser igual a la fecha actual");
        }
        if(fechaNacimiento.isAfter(LocalDate.now().minusYears(18))){
            throw new IllegalArgumentException("El empleado debe ser mayor de edad");
        }
    }

    private void validacionEmail(Email email) throws IllegalArgumentException{
        if(email==null){
            throw new IllegalArgumentException("El email no puede ser null");
        }
    }

    private void validacionMonthDay(MonthDay monthDay) throws IllegalArgumentException{
        if(monthDay==null){
            throw new IllegalArgumentException("El mes y día no pueden ser null");
        }
    }
}
