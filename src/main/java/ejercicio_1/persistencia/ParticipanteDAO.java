package ejercicio_1.persistencia;
import ejercicio_1.modelo.Participante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParticipanteDAO {

    private Connection connection;

    public ParticipanteDAO(Connection connection) {
        this.connection = connection;
    }

    public void guardar(Participante participante) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "insert into participantes(nombre, telefono, region) values(?,?,?)"
        );

        try {
            st.setString(1, participante.nombre());
            st.setString(2, participante.telefono());
            st.setString(3, participante.region());
            st.executeUpdate();
        } finally {
            st.close();
        }
    }

}
