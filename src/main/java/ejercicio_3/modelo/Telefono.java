package ejercicio_3.modelo;

import javax.swing.*;

public class Telefono {
    private String numero;

    public Telefono(String numero) {
        validarNumero(numero);
        this.numero = numero;
    }

    private void validarNumero(String numero) {
        if (!checkPhone(numero)) {

            throw new IllegalArgumentException("El teléfono debe ingresarse de la siguiente forma: NNNN-NNNNNN");
        }
    }


    private boolean checkPhone(String telefono) {
        String regex = "\\d{4}-\\d{6}";
        return telefono.matches(regex);
    }

    public String numero() {
        return numero;
    }
}
