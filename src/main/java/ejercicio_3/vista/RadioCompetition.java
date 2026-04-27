package ejercicio_3.vista;


import ejercicio_3.modelo.GestorInscripciones;
import ejercicio_3.modelo.GestorConcursos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

    public class RadioCompetition {
        private JPanel contentPane;
        private JLabel lblName;
        private JTextField txtName;
        private JLabel lblLastName;
        private JTextField txtLastName;
        private JLabel lblId;
        private JTextField txtId;
        private JLabel lblPhone;
        private JTextField txtPhone;
        private JLabel lblEmail;
        private JTextField txtEmail;
        private JComboBox<String> comboBox;
        private JButton btnOk;
        private JLabel lblCompetition;

        private GestorConcursos gestorConcursos;
        private GestorInscripciones gestorInscripciones;


        public RadioCompetition(GestorInscripciones gestorInscripciones, GestorConcursos gestorConcurso) {
            this.gestorConcursos = gestorConcurso;
            this.gestorInscripciones = gestorInscripciones;

            var frame = new JFrame("Inscription to Competition");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBounds(100, 100, 451, 229);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            frame.setContentPane(contentPane);
            formElements();
            layout();
            frame.setVisible(true);
        }

        private void formElements() {
            lblName = new JLabel("Nombre:");
            txtName = new JTextField();
            txtName.setColumns(10);
            lblLastName = new JLabel("Apellido:");
            txtLastName = new JTextField();
            txtLastName.setColumns(10);
            lblId = new JLabel("Dni:");
            txtId = new JTextField();
            txtId.setColumns(10);
            lblPhone = new JLabel("Telefono:");
            txtPhone = new JTextField();
            txtPhone.setColumns(10);
            lblEmail = new JLabel("Email:");
            txtEmail = new JTextField();
            txtEmail.setColumns(10);
            btnOk = new JButton("Ok");
            btnOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnOk.setEnabled(false);
                    saveInscription();
                    btnOk.setEnabled(true);
                }
            });
            lblCompetition = new JLabel("Concurso:");
            comboBox = new JComboBox<String>();
            todosLosConcursos();
        }

        private void todosLosConcursos() {

            var concursos = gestorConcursos.listarConcursosActivos();
            for (var concurso : concursos) {
                comboBox.addItem(String.valueOf(concurso.id()));
            }
        }
        private void saveInscription() {
            try {
                String nombre = txtName.getText();
                String apellido = txtLastName.getText();
                String dni = txtId.getText();
                String telefono = txtPhone.getText();
                String email = txtEmail.getText();
                String idConcurso = (String) comboBox.getSelectedItem();

                gestorInscripciones.guardarInscripto(nombre, apellido, dni, telefono, email, idConcurso);
            } catch (Exception e) {

                System.err.println("Error al guardar la inscripción: " + e.getMessage());
            }
        }
        private void layout() {
            GroupLayout layout = new GroupLayout(contentPane);
            contentPane.setLayout(layout);

            layout.setHorizontalGroup(createHorizontalGroup(layout));
            layout.setVerticalGroup(createVerticalGroup(layout));
        }

        private GroupLayout.Group createHorizontalGroup(GroupLayout layout) {
            return layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                                    .addComponent(lblLastName)
                                                    .addComponent(lblId)
                                                    .addComponent(lblPhone)
                                                    .addComponent(lblEmail)
                                                    .addComponent(lblName)
                                                    .addComponent(lblCompetition))
                                            .addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                                                    .addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txtEmail, Alignment.TRAILING)
                                                    .addComponent(txtPhone, Alignment.TRAILING)
                                                    .addComponent(txtId, Alignment.TRAILING)
                                                    .addComponent(txtLastName, Alignment.TRAILING)
                                                    .addComponent(txtName, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)))
                                    .addComponent(btnOk, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap());
        }

        private GroupLayout.Group createVerticalGroup(GroupLayout layout) {
            return layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(txtName)
                            .addComponent(lblName))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(lblLastName)
                            .addComponent(txtLastName))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(lblId)
                            .addComponent(txtId))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblPhone)
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addComponent(lblEmail))
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtPhone)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(txtEmail)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                            .addComponent(comboBox)
                                            .addComponent(lblCompetition))))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnOk)
                    .addContainerGap(67, Short.MAX_VALUE);
        }

    }



