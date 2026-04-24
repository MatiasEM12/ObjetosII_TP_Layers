package ejercicio_1.modelo;

import ejercicio_1.persistencia.ConexionBD;
import ejercicio_1.persistencia.ParticipanteDAO;

import java.sql.Connection;

public class ParticipanteAPI {


    Connection connection;
    ParticipanteServicio servicio;

    public ParticipanteAPI() throws Exception {
        connection = ConexionBD.obtenerConexion();
        var dao = new ParticipanteDAO(connection);
        servicio = new ParticipanteServicio(dao);
    }

    public void guardarParticipante(Participante participante) throws Exception {

        servicio.agregarParticipante(participante);


    }
}
