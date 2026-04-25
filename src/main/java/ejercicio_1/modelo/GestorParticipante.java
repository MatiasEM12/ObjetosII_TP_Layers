package ejercicio_1.modelo;




import ejercicio_1.persistencia.ParticipanteDAO;

import java.sql.Connection;

public class GestorParticipante {


    Connection connection;
    ConexionParticipante servicio;

    public GestorParticipante(ConexionParticipante servicio ) throws Exception {

       this.servicio=servicio;
    }

    public void guardarParticipante(String nombre, String telefono, String region) throws Exception {

        servicio.agregarParticipante(new Participante(nombre, new Telefono(telefono), region));


    }
}