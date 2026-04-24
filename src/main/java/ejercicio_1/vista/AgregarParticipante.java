package ejercicio_1.vista;

import ejercicio_1.modelo.Participante;
import ejercicio_1.modelo.ParticipanteAPI;


import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class AgregarParticipante extends JFrame {

    private JTextField nombre;
    private JTextField telefono;
    private JTextField region;
    ParticipanteAPI api;

    public AgregarParticipante(ParticipanteAPI api) throws SQLException {
        setupUIComponents();
        this.api=api;
    }

    private void setupUIComponents() {
        setTitle("Add Participant");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.nombre = new JTextField(10);
        this.telefono = new JTextField(10);
        this.region = new JTextField(10);
        this.nombre.setText("");
        this.telefono.setText("");
        this.region.setText("China");
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new FlowLayout());
        contentPane.add(new JLabel("Nombre: "));
        contentPane.add(nombre);
        contentPane.add(new JLabel("Telefono: "));
        contentPane.add(telefono);
        contentPane.add(new JLabel("Region: "));
        contentPane.add(region);
        JButton botonCargar = new JButton("Cargar");
        botonCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onBotonCargar();
            }
        });
        contentPane.add(botonCargar);
        setContentPane(contentPane);
        contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pack();
        setVisible(true);
    }

    private void onBotonCargar() {
        try {
            Participante participante = new Participante(//eliminar
                    nombre.getText(),
                    telefono.getText(),
                    region.getText()
            );
            //pasar los string o record
            api.guardarParticipante(participante);
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

}

