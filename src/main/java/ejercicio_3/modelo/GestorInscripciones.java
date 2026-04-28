package ejercicio_3.modelo;

public class GestorInscripciones{



    private GestionPersistencia<Inscripto> inscriptos;

    public GestorInscripciones(GestionPersistencia<Inscripto> inscriptos) {
        this.inscriptos = inscriptos;
    }

    public void guardarInscripto(String nombre, String apellido, String dni,String telefono, String email,String idConcurso) throws Exception {

        var inscripto = new Inscripto(nombre, apellido, dni, new Telefono(telefono), new Email(email), Integer.parseInt(idConcurso));

        var existentes = inscriptos.listar();

        for (Inscripto i : existentes) {
            if (i.mismaInscripcion(inscripto)) {
                throw new RuntimeException("El inscripto ya existe para este concurso");
            }
        }

        this.inscriptos.crear(inscripto);

    }

}
