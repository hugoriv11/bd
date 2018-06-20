/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.EstudianteDao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Estudiante;

/**
 *
 * @author HugoJr. <Hugo Rivera at 00161417@uca.edu.sv>
 */
public class Consulta extends JFrame {
    
    public JLabel lblCarnet, lblNombre, lblApellidos, lblUniversidad, lblEstado;

    public JTextField carnet, nombre, apellido;
    
    public JComboBox universidad;

    ButtonGroup estado = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    
    public JTable resultados;

    public JPanel table;

    public JButton buscar, eliminar, insertar, limpiar, actualizar;

    private static final int ANCHOC = 130, ALTOC = 30;

    DefaultTableModel tm;

    public Consulta() {
        super("Alumno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblCarnet);
        container.add(lblNombre);
        container.add(lblApellidos);
        container.add(lblUniversidad);
        container.add(lblEstado);
        container.add(carnet);
        container.add(nombre);
        container.add(apellido);
        container.add(si);
        container.add(no);
        container.add(universidad);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(650, 650);
        eventos();
    }

    public final void agregarLabels() {
        lblCarnet = new JLabel("Carnet:");
        lblNombre = new JLabel("Nombre:");
        lblUniversidad = new JLabel("Universidad:");
        lblEstado = new JLabel("Estado:");
        lblApellidos   = new JLabel("Apellidos:");
        lblCarnet.setBounds(10, 10, ANCHOC, ALTOC);
        lblNombre.setBounds(10, 60, ANCHOC, ALTOC);
        lblUniversidad.setBounds(10, 140, ANCHOC, ALTOC);
        lblEstado.setBounds(10, 180, ANCHOC, ALTOC);
        lblApellidos.setBounds(300, 50, ANCHOC, ALTOC);
    }

    public final void formulario() {
        carnet = new JTextField();
        universidad = new JComboBox();
        apellido = new JTextField();
        nombre = new JTextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no", false);
        
        resultados = new JTable();
        
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();
        universidad.addItem("UCA");
        universidad.addItem("UDB");
        universidad.addItem("UFG");
        universidad.addItem("UGB");
        
        estado = new ButtonGroup();
        estado.add(si);
        estado.add(no);

        carnet.setBounds(140, 10, ANCHOC, ALTOC);
        nombre.setBounds(140, 60, ANCHOC, ALTOC);
        universidad.setBounds(140, 140, 100, ALTOC);
        apellido.setBounds(400, 50, 100, ALTOC);
        si.setBounds(140, 180, 50, ALTOC);
        no.setBounds(210, 180, 50, ALTOC);

        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 300, ANCHOC, ALTOC);
        actualizar.setBounds(150, 300, ANCHOC, ALTOC);
        eliminar.setBounds(300, 300, ANCHOC, ALTOC);
        limpiar.setBounds(450, 300, ANCHOC, ALTOC);
        resultados = new JTable();
        
        resultados = new JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; 
            }
        };
        table.setBounds(10, 350, 600, 200);
        table.add(new JScrollPane(resultados));
    }

    public void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };

        tm.addColumn("Carnet");
        tm.addColumn("Nombres");
        tm.addColumn("Apellidos");
        tm.addColumn("Universidad");
        tm.addColumn("Estado");

        EstudianteDao ed = new EstudianteDao();
        ArrayList<Estudiante> filtros = ed.readAll();

        for (Estudiante estu : filtros) {
            tm.addRow(new Object[]{estu.getCarnet(), estu.getNombres(), estu.getApellidos(), estu.getUniversidad(), estu.getEstado()});
        }

        resultados.setModel(tm);
    }

    /**
     * ed = EstudianteDao objeto
     */
    public void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstudianteDao ed = new EstudianteDao();
                Estudiante estu = new Estudiante(Integer.parseInt(carnet.getText()), nombre.getText(), apellido.getText(), universidad.getSelectedItem().toString(), true);

                if (no.isSelected()) {
                    estu.setEstado(false);
                }

                if (ed.create(estu)) {
                    JOptionPane.showMessageDialog(null, "Estudiante registrado con existo");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de crear el estudiante");
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstudianteDao ed = new EstudianteDao();
                Estudiante estu = new Estudiante(Integer.parseInt(carnet.getText()), nombre.getText(), apellido.getText(), universidad.getSelectedItem().toString(), true);

                if (no.isSelected()) {
                    estu.setEstado(false);
                }

                if (ed.update(estu)) {//cambiar en metodo de Object Key a Generic g.
                    JOptionPane.showMessageDialog(null, "Estudiante modificado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar el estudiante");
                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstudianteDao ed = new EstudianteDao();
                if (ed.delete(Integer.parseInt(carnet.getText()))) {
                    JOptionPane.showMessageDialog(null, "Estudiante eliminado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar el estudiante");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstudianteDao ed = new EstudianteDao();
                Estudiante estu = ed.read(Integer.parseInt(carnet.getText()));
                if (estu == null) {
                    JOptionPane.showMessageDialog(null, "El estudiante buscado no se ha encontrado");
                } else {
                    carnet.setText(String.valueOf(estu.getCarnet()));
                    nombre.setText(estu.getNombres());
                    apellido.setText(estu.getApellidos());
                    universidad.setSelectedItem(estu.getUniversidad());

                    if (estu.getEstado()) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        
        resultados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() == 1) {
                    carnet.setText(resultados.getValueAt(resultados.getSelectedRow(), 0).toString());
                    universidad.setSelectedItem(resultados.getValueAt(resultados.getSelectedRow(), 3).toString());
                    nombre.setText(resultados.getValueAt(resultados.getSelectedRow(), 1).toString());
//                    edad.setText(resultados.getValueAt(resultados.getSelectedRow(), 3).toString()); 
                    apellido.setText(resultados.getValueAt(resultados.getSelectedRow(), 2).toString()); 
                    if (resultados.getValueAt(resultados.getSelectedRow(), 4).toString() == "false") {
                        no.setSelected(true);
                    } else {
                        si.setSelected(true);
                    }
                }
            }
        });
    }

    public void limpiarCampos() {
        carnet.setText("");
        universidad.setSelectedItem("UCA");
        nombre.setText("");
        apellido.setText("");
        si.setSelected(true);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }
        });
    }
}
