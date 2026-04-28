package ejercicio_2.modelo;

public class Email {
    private String email;


    public Email(String email) {
        validarEmail(email);
        this.email = email;

    }

    private void validarEmail(String email) {

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


