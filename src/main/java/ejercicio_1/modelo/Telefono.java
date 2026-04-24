package ejercicio_1.modelo;

public class Telefono {

    private String  numero;

    public Telefono(String numero){
        validarTelefono(numero);
        this.numero=numero;
    }
    private void validarTelefono(String telefono) {
        if (telefono == null || telefono.isEmpty()) {
            throw new RuntimeException("Debe cargar un telefono");
        }

        String regex = "\\d{4}-\\d{6}";
        if (!telefono.matches(regex)) {
            throw new RuntimeException("Formato de telefono inválido (NNNN-NNNNNN)");
        }
    }

    public String numero(){
        return this.numero;
    }
}
