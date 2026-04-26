package ejercicio_1.main;



import ejercicio_1.modelo.GestorParticipante;
import ejercicio_1.persistencia.ParticipanteDAO;
import ejercicio_1.vista.AgregarParticipante;

import java.awt.*;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    private static final String URL = "jdbc:derby:participantes;create=true";
    private static  Connection conn;

    public static void main(String[] args) throws SQLException {


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AgregarParticipante(new GestorParticipante(new ParticipanteDAO(conn)));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

   private static void inizializar(){
        try {
             conn = SetUpDatabase.obtenerConexion(URL);
        } catch (Exception e) {
            System.out.println(e);
        }
   }

}
