package ejercicio_1.persistencia;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD{

    private static final String URL = "jdbc:derby:participantes;create=true";
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}