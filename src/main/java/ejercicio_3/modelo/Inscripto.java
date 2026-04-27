package ejercicio_3.modelo;

import javax.swing.*;

public class Inscripto {

    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private int idConcurso;

    public Inscripto(String nombre, String apellido, String telefono, String email, int idConcurso) {
        this.nombre = nombre;
        this.apellido = apellido;
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

}
