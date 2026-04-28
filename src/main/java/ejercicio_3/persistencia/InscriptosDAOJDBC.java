package ejercicio_3.persistencia;

import ejercicio_3.modelo.Email;
import ejercicio_3.modelo.Inscripto;
import ejercicio_3.modelo.Telefono;

import java.sql.*;
import java.util.ArrayList;


public class InscriptosDAOJDBC implements InscriptoDAO {


    @Override
    public void truncateTabla() {
        final String SQL = "TRUNCATE TABLE inscriptos";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.disconnect();
        }
    }

    private void validarInscripto(Inscripto inscripto) {
        if (inscripto == null) {
            throw new IllegalArgumentException("Inscripto no puede ser null");
        }
    }

    @Override
    public void crear(Inscripto dato) {
        validarInscripto(dato);

        final String SQL = "INSERT INTO inscriptos (apellido, nombre, telefono, email, dni, id_concurso) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, dato.apellido());
            st.setString(2, dato.nombre());
            st.setString(3, dato.telefono());
            st.setString(4, dato.email());
            st.setString(5, dato.dni());
            st.setInt(6, dato.idConcurso());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.disconnect();
        }
    }

    @Override
    public void eliminarArchivo(String ruta) {
        throw new RuntimeException("Operación no soportada en esta implementación");
    }

    @Override
    public ArrayList<Inscripto> listar() {
        final String SQL = "SELECT apellido, nombre, telefono, email, dni, id_concurso FROM inscriptos";

        ArrayList<Inscripto> inscriptos = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                String apellido = rs.getString("apellido");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                String dni = rs.getString("dni");
                int idConcurso = rs.getInt("id_concurso");

                Inscripto inscripto = new Inscripto(
                        nombre,
                        apellido,
                        dni,
                        new Telefono(telefono),
                        new Email(email),
                        idConcurso
                );

                inscriptos.add(inscripto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.disconnect();
        }

        return inscriptos;
    }
}