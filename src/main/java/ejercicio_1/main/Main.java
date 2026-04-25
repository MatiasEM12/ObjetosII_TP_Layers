package ejercicio_1.main;

import ejercicio_1.modelo.ConexionParticipante;

import ejercicio_1.modelo.GestorParticipante;
import ejercicio_1.persistencia.ParticipanteDAO;
import ejercicio_1.vista.AgregarParticipante;

import java.awt.*;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    private static final String URL = "jdbc:derby:participantes;create=true";


    public static void main(String[] args) throws SQLException {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    Connection connection =  SetUpDatabase.obtenerConexion(URL);
                    new AgregarParticipante(new GestorParticipante(new ConexionParticipante(new ParticipanteDAO(connection))));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

}
