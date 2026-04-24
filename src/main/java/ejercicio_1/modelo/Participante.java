package ejercicio_1.modelo;

public class Participante {
    private String nombre;
    private String telefono;
    private String region;

    public Participante(String nombre, String telefono, String region) {
        validarNombre(nombre);
        validarTelefono(telefono);
        validarRegion(region);

        this.nombre = nombre;
        this.telefono = telefono;
        this.region = region;
    }
    private void validarNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new RuntimeException("Debe cargar un nombre");
        }
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

    private void validarRegion(String region) {
        if (!region.equals("China") && !region.equals("US") && !region.equals("Europa")) {
            throw new RuntimeException("Region desconocida");
        }
    }

    public String nombre() { return nombre; }
    public String telefono() { return telefono; }
    public String region() { return region; }
}


