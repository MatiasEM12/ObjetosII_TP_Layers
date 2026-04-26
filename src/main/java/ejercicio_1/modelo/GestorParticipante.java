package ejercicio_1.modelo;


public class GestorParticipante {


 ;
    RegistroParticipante registroParticipante;

    public GestorParticipante(RegistroParticipante registro ) throws Exception {

       this.registroParticipante=registro;
    }

    public void guardarParticipante(String nombre, String telefono, String region) throws Exception {
        var participante = new Participante(nombre, new Telefono(telefono), region);
        this.registroParticipante.guardar(participante);
    }
}