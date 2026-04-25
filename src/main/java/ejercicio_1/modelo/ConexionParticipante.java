package ejercicio_1.modelo;

import ejercicio_1.persistencia.ParticipanteDAO;

import java.sql.SQLException;

public class ConexionParticipante {


    private ParticipanteDAO dao;

    public ConexionParticipante(ParticipanteDAO dao) {
        this.dao = dao;
    }


    public void agregarParticipante(Participante participante) throws SQLException {
        dao.guardar(participante);
    }
}