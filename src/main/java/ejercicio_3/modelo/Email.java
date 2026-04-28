package ejercicio_3.modelo;

import javax.swing.*;

public class Email {
    private String email;


    public Email(String email) throws  IllegalArgumentException {
        validarEmail(email);
        this.email = email;

    }

    private void validarEmail(String email) throws IllegalArgumentException{

        if (!checkEmail(email)){
            throw new IllegalArgumentException("El email no es válido");

        }
    }

    private boolean checkEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public String email() {
        return email;
    }

}

