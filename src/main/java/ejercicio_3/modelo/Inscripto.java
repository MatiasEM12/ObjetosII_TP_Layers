package ejercicio_3.modelo;

import javax.swing.*;

public class Inscripto {

    private String nombre;
    private String apellido;
    private String dni;
    private Telefono telefono;
    private Email email;
    private int idConcurso;

    public Inscripto(String nombre, String apellido,String dni ,Telefono telefono, Email email, int idConcurso) {
        validarNombre(nombre);
        validarApellido(apellido);
        validarDni(dni);
        validarIdconcurso(idConcurso);
        validarEmail(email);
        validarTelefono(telefono);

        this.nombre = nombre;
        this.apellido = apellido;
        this.dni=dni;
        this.telefono = telefono;
        this.email = email;
        this.idConcurso = idConcurso;
    }

    //validaciones
    private void validarNombre(String nombre){
      if (nombre.isEmpty()) {
        throw new IllegalArgumentException("El nombre no puede estar vacío");
      }
    }

    private void validarApellido(String apellido){
        if (apellido.isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
    }

    private void validarDni(String dni){
        if(dni.isEmpty()) {
            throw new IllegalArgumentException("El dni no puede estar vacío");
        }
    }

    private void validarIdconcurso(int idConcurso){
        if(idConcurso <= 0) {
            throw new IllegalArgumentException("Debe elegir un Concurso");
        }
    }

    private void validarEmail(Email email){
        if(email == null) {
            throw new IllegalArgumentException("Debe ingresar un email");
        }
    }

    private void validarTelefono(Telefono telefono){
        if(telefono == null) {
            throw new IllegalArgumentException("Debe ingresar un teléfono");
        }
    }
    public boolean mismaInscripcion(Inscripto otro) {
        return this.dni.equals(otro.dni) && this.idConcurso == otro.idConcurso;
    }

    public String toFile() {
        return apellido + "," + nombre + "," + dni + "," + telefono.numero() + "," + email.email() + "," + idConcurso;
    }
}
