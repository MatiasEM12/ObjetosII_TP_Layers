package ejercicio_1.modelo;

public class Participante {
    private String nombre;
    private Telefono telefono;
    private String region;

    public Participante(String nombre, Telefono telefono, String region) {
        validarNombre(nombre);

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



    private void validarRegion(String region) {
        if (!region.equals("China") && !region.equals("US") && !region.equals("Europa")) {
            throw new RuntimeException("Region desconocida");
        }
    }

    public String nombre() { return nombre; }
    public String telefono() { return telefono.numero(); }
    public String region() { return region; }
}


