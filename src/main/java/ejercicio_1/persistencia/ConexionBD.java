package ejercicio_1.persistencia;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD{
//se divide entre main o participanteDAO
    private static final String URL = "jdbc:derby:participantes;create=true";//main
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}