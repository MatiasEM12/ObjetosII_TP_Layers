package ejercicio_1.modelo;

import ejercicio_1.persistencia.ConexionBD;
import ejercicio_1.persistencia.ParticipanteDAO;

import java.sql.Connection;

public class ParticipanteAPI {


    Connection connection;
    ParticipanteServicio servicio;

    public ParticipanteAPI() throws Exception {
        connection = ConexionBD.obtenerConexion();
        var dao = new ParticipanteDAO(connection);//inyectado
        servicio = new ParticipanteServicio(dao);//inyectado
    }
//las clases que conectan las capas se inyectan en main
    public void guardarParticipante(Participante participante) throws Exception {
//recibo 3 string       Telefono tel= new Telefono( string inyectado);
        servicio.agregarParticipante(participante);


    }
}
