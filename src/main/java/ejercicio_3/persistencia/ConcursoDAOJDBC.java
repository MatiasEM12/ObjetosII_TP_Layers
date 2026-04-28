package ejercicio_3.persistencia;

import ejercicio_3.modelo.Concurso;

import java.sql.*;
import java.util.ArrayList;


public class ConcursoDAOJDBC implements ConcursoDAO {

    @Override
    public void truncateTabla() {
        final String SQL = "TRUNCATE TABLE concursos";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.disconnect();
        }
    }

    @Override
    public void crear(Concurso dato) {
        validarConcurso(dato);

        final String SQL = "INSERT INTO concursos (idconcurso, nombre, fechaInicioInscripcion, fechaFinInscripcion) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1, dato.id());
            st.setString(2, dato.nombre());
            st.setDate(3, Date.valueOf(dato.fechaInicio()));
            st.setDate(4, Date.valueOf(dato.fechaFin()));

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
    public ArrayList<Concurso> listar() {
        final String SQL = "SELECT idconcurso, nombre, fechaInicioInscripcion, fechaFinInscripcion FROM concursos";

        ArrayList<Concurso> concursos = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("idconcurso");
                String nombre = rs.getString("nombre");
                Date fechaInicio = rs.getDate("fechaInicioInscripcion");
                Date fechaFin = rs.getDate("fechaFinInscripcion");

                Concurso concurso = new Concurso(
                        id,
                        nombre,
                        fechaInicio.toLocalDate(),
                        fechaFin.toLocalDate()
                );

                concursos.add(concurso);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.disconnect();
        }

        return concursos;
    }

    private void validarConcurso(Concurso dato) {
        if (dato == null) {
            throw new IllegalArgumentException("El concurso no puede ser nulo");
        }
    }
}