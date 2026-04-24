package ejercicio_1.main;

import ejercicio_1.modelo.ParticipanteAPI;
import ejercicio_1.vista.AgregarParticipante;

import java.awt.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AgregarParticipante(new ParticipanteAPI());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

}
